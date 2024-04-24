package com.huaihao.bookcrosser.backend.controller;


import com.huaihao.bookcrosser.backend.dto.CommentDTO;
import com.huaihao.bookcrosser.backend.mbg.model.Comment;
import com.huaihao.bookcrosser.backend.service.CommentService;
import com.huaihao.bookcrosser.backend.service.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/selectAll")
    public ResponseEntity<List<CommentDTO>> selectAll() {
        try {
            List<CommentDTO> result = commentService.selectAll();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }


    }

    @GetMapping("/selectMyComments")
    public ResponseEntity<List<CommentDTO>> selectMyComments(@RequestAttribute("userId") Long userId) {
        try {
            List<CommentDTO> result = commentService.selectByUserId(userId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/selectBookComments")
    public ResponseEntity<Result> selectBookComments(Long bookId) {
        Result result = commentService.selectByBookId(bookId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PostMapping("/post")
    public ResponseEntity<Result> postComment(
            @RequestAttribute("userId") Long userId,
            @Param("bookId") Long bookId,
            @Param("content") String content
    ) {
        if (content == null || content.isEmpty()) {
            return ResponseEntity.badRequest().body(Result.failed("评论内容不能为空"));
        }

        if (commentService.saveComment(new Comment(userId, bookId, content))) {
            Result result = Result.success("评论成功");
            return ResponseEntity.ok(result);
        } else {
            Result result = Result.failed("评论失败");
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Result> updateComment(
            @Param("id") Long id,
            @Param("content") String content
    ) {
        if (content == null || content.isEmpty()) {
            return ResponseEntity.badRequest().body(Result.failed("评论内容不能为空"));
        }

        Comment comment = commentService.selectById(id);
        if (comment == null) {
            return ResponseEntity.badRequest().body(Result.failed("评论不存在"));
        }

        comment.setContent(content);
        if (commentService.updateComment(comment)) {
            Result result = Result.success("更新成功");
            return ResponseEntity.ok(result);
        } else {
            Result result = Result.failed("更新失败");
            return ResponseEntity.badRequest().body(result);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> deleteComment(
            @Param("id") Long id,
            @RequestAttribute("userId") Long userId
    ) {
        Comment comment = commentService.selectById(id);
        if (comment == null) {
            return ResponseEntity.badRequest().body(Result.failed("评论不存在"));
        }

        if (!comment.getUserId().equals(userId)) {
            return ResponseEntity.badRequest().body(Result.failed("不是你的评论不能删除"));
        }

        if (commentService.deleteComment(id)) {
            Result result = Result.success("删除成功");
            return ResponseEntity.ok(result);
        } else {
            Result result = Result.failed("删除失败");
            return ResponseEntity.badRequest().body(result);
        }
    }
}
