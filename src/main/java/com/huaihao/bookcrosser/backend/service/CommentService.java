package com.huaihao.bookcrosser.backend.service;

import com.huaihao.bookcrosser.backend.dto.CommentDTO;
import com.huaihao.bookcrosser.backend.mbg.model.Comment;

import java.util.List;

public interface CommentService {
    List<CommentDTO> selectAll();

    boolean saveComment(Comment comment);

    boolean deleteComment(Long id);

    Comment selectById(Long id);

    boolean updateComment(Comment comment);

    Result selectByBookId(Long bookId);

    List<CommentDTO> selectByUserId(Long userId);
}
