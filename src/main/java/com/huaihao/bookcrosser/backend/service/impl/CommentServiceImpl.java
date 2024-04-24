package com.huaihao.bookcrosser.backend.service.impl;

import com.huaihao.bookcrosser.backend.dto.CommentDTO;
import com.huaihao.bookcrosser.backend.mbg.mapper.BookMapper;
import com.huaihao.bookcrosser.backend.mbg.mapper.CommentMapper;
import com.huaihao.bookcrosser.backend.mbg.mapper.UserMapper;
import com.huaihao.bookcrosser.backend.mbg.model.Book;
import com.huaihao.bookcrosser.backend.mbg.model.Comment;
import com.huaihao.bookcrosser.backend.mbg.model.User;
import com.huaihao.bookcrosser.backend.service.CommentService;
import com.huaihao.bookcrosser.backend.service.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<CommentDTO> selectAll() {
        List<Comment> comments = commentMapper.selectAll();
        return getCommentDTOS(comments);
    }

    @Override
    public List<CommentDTO> selectByUserId(Long userId) {
        List<Comment> comments = commentMapper.selectByUserId(userId);
        return getCommentDTOS(comments);
    }

    private List<CommentDTO> getCommentDTOS(List<Comment> comments) {
        List<CommentDTO> commentResults = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setComment(comment);
            User sender = userMapper.selectById(comment.getUserId());
            commentDTO.setSender(sender);

            Book book = bookMapper.selectById(comment.getBookId());
            commentDTO.setBook(book);
            commentResults.add(commentDTO);
        }
        return commentResults;
    }

    @Override
    public boolean saveComment(Comment comment) {
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        return commentMapper.saveComment(comment);
    }

    @Override
    public boolean deleteComment(Long id) {
        return commentMapper.deleteComment(id);
    }

    @Override
    public Comment selectById(Long id) {
        return commentMapper.selectById(id);
    }

    @Override
    public boolean updateComment(Comment comment) {
        comment.setUpdatedAt(LocalDateTime.now());
        return commentMapper.updateComment(comment);
    }

    @Override
    public Result selectByBookId(Long bookId) {
        List<Comment> comments = commentMapper.selectByBookId(bookId);
        if (comments != null) {
            return Result.success(comments);
        } else {
            return Result.failed("没有评论");
        }
    }


}
