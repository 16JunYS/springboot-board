package com.jam2in.arcus.board.controller;

import com.jam2in.arcus.board.model.Comment;
import com.jam2in.arcus.board.model.Pagination;
import com.jam2in.arcus.board.model.Post;
import com.jam2in.arcus.board.service.BoardService;
import com.jam2in.arcus.board.service.CommentService;
import com.jam2in.arcus.board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private CommentService commentService;

    /*  새로운 게시글 작성 화면 */
    @RequestMapping(value = "/new-post", method = RequestMethod.GET)
    public String write(@RequestParam("board_id") String board_id, Model model) {
        model.addAttribute("board_id", board_id);
        return "write";
    }

    /*  새로운 게시글 등록  */
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String upload(@ModelAttribute Post post) {
        postService.create(post);
        return "redirect:/board/info?id="+post.getBoard_id();
    }

    /*  게시글 수정 화면  */
    @RequestMapping(value = "/post/edit", method = RequestMethod.GET)
    public String edit(@ModelAttribute Post post ,Model model) {
        model.addAttribute(model);
        return "postEdit";
    }

    /*  게시글 수정  */
    @RequestMapping(value = "/post", method = RequestMethod.PUT)
    public String update(@ModelAttribute Post post) {
        postService.update(post);
        return "redirect:/post/detail?id="+post.getId()+"&board_id="+post.getBoard_id();
    }

    /*  게시글 삭제  */
    @RequestMapping(value = "/post", method = RequestMethod.DELETE)
    public String delete(@RequestParam int id, @RequestParam int board_id) {
        postService.delete(id, board_id);
        return "redirect:/board/info?id=" + board_id;
    }

    /*  게시글 상세 조회   */
    @RequestMapping("/post/detail")
    public String detail(@RequestParam int id, @RequestParam int board_id, Model model) {
        Post post = postService.get(id, board_id);

        //Comment List Pagination
        Pagination pagination = new Pagination();
        //pagination.setPageSize(20);
        pagination.setGroupSize(10);
        pagination.setListCnt(commentService.countCmt(post.getId()));
        pagination.pageInfo(1, 1, pagination.getListCnt());

        model.addAttribute("comments", new Comment());
        model.addAttribute("post", post);
        model.addAttribute("pagination", pagination);

        return "detail";
    }

    @RequestMapping("/post/comment")
    public String comment() {
        return "redirect:/detail";
    }

}
