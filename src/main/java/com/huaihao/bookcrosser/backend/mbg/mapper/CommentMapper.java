package com.huaihao.bookcrosser.backend.mbg.mapper;

import com.huaihao.bookcrosser.backend.mbg.model.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    boolean saveComment(Comment comment);

    List<Comment> selectAll();

    boolean deleteComment(Long id);

    Comment selectById(Long id);

    boolean updateComment(Comment comment);

    List<Comment> selectByBookId(Long bookId);

    List<Comment> selectByUserId(Long userId);

}
