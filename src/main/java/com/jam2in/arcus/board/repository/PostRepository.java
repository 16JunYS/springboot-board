package com.jam2in.arcus.board.repository;

import com.jam2in.arcus.board.model.Pagination;
import com.jam2in.arcus.board.model.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PostRepository {

    int insert(Post post);

    int update(Post post);

    int delete(int id);

    Post selectOne(int id);

    List<Post> selectPage(@Param("board_id")int board_id, @Param("startList")int startList, @Param("pageSize")int pageSize);

    List<Post> selectAll(int board_id);

    int increaseViews(int id);

    int countPost(int id);
}
