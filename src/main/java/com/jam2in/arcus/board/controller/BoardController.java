package com.jam2in.arcus.board.controller;

import com.jam2in.arcus.board.model.Board;
import com.jam2in.arcus.board.model.Pagination;
import com.jam2in.arcus.board.model.Post;
import com.jam2in.arcus.board.service.BoardService;
import com.jam2in.arcus.board.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// @Component - bean 생성 (class)
// @PostConstruct - method bean 생성되면 callback

@Controller
public class BoardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    private BoardService boardService;
    @Autowired
    private PostService postService;

    //************************************************************************************************
    // REST API (application/json)
    //************************************************************************************************
    /*
    @RequestMapping(path = "/board", method = RequestMethod.POST)
    @ResponseBody
    public Board createRestAPI(@RequestBody Board board) {
        if (boardService.create(board) == 0) {
            // TODO: Response HTTP Error (CONFLICT)
        }

        return board;
    }

    @RequestMapping(path = "/board/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Board updateRestAPI(@PathVariable int id, @RequestBody Board board) {
        if (boardService.update(id, board) == 0) {
            // TODO: Response HTTP Error (NOT_FOUND)
        }

        return board;
    }

    @RequestMapping(path = "/board/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Board getRestAPI(@PathVariable int id) {
        Board board = boardService.get(id);
        if (board == null) {
            // TODO: Response HTTP Error (NOT_FOUND)
        }

        return board;
    }

    @RequestMapping(path = "/board", method = RequestMethod.GET)
    @ResponseBody
    public List<Board> getAllRestAPI() {
        return boardService.getAll();
    }
    */


    //************************************************************************************************
    // FORM (form-data, x-www-form-urlencoded)
    //************************************************************************************************
    /*  새로운 게시판 생성  */
    @RequestMapping(path = "/board", method = RequestMethod.POST)
    public String create(@ModelAttribute Board board) {
        boardService.create(board);
        return "redirect:/board";
    }

    /*  게시판 수정   */
    @RequestMapping(path = "/board", method = RequestMethod.PUT)
    public String update(@ModelAttribute Board board) {
        boardService.update(board);
        return "board";
    }

    /*  게시판 삭제  */
    @RequestMapping(path = "/board", method = RequestMethod.DELETE)
    public String delete(@ModelAttribute Board board) {
        boardService.remove(board);
        return "redirect:/board";
    }

    /*  상세 게시판 조회  */
    @RequestMapping(path = "/board/info", method = RequestMethod.GET)
    public String get(@RequestParam int id,
                      @RequestParam(required = false, defaultValue = "1") int pageIndex,
                      @RequestParam(required = false, defaultValue = "1") int groupIndex,
                      Model model){
        int listCnt = postService.countPost(id);

        //Test test = new Test();
        //test.uploadPost();

        Pagination pagination = new Pagination();
        //pagination.setPageSize(20);
        pagination.setGroupSize(10);
        pagination.pageInfo(groupIndex, pageIndex, listCnt);
        model.addAttribute("board_id", id);
        model.addAttribute("board_name", boardService.get(id).getName());
        //model.addAttribute("board_name", "(board_name)");
        model.addAttribute("posts", postService.getPage(id, pagination.getStartList()-1, pagination.getPageSize()));
       // LOGGER.info("Board #{}, page#{} : {}", id, pagination.getGroupIndex(), pagination.getPageIndex());
        model.addAttribute("pagination", pagination);
        return "list";
    }

    /*  전체 게시판 조회   */
    @RequestMapping(path = "/board", method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("boards", boardService.getAll());
        return "board";
    }

}
