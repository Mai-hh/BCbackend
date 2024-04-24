package com.huaihao.bookcrosser.backend.controller;

import com.huaihao.bookcrosser.backend.dto.DriftingRequest;
import com.huaihao.bookcrosser.backend.mbg.model.*;
import com.huaihao.bookcrosser.backend.service.BookService;
import com.huaihao.bookcrosser.backend.service.DriftingService;
import com.huaihao.bookcrosser.backend.service.Result;
import com.huaihao.bookcrosser.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/drifting")
public class DriftingController {

    @Autowired
    private DriftingService driftingService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    // 求漂
    @PostMapping("/request")
    public ResponseEntity<Result> request(
            @RequestParam("bookId") Long bookId,
            @RequestAttribute("userId") Long requesterId
    ) {
        Result result = driftingService.request(bookId, requesterId, RequestStatus.REQUESTING);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    // 查询我的求漂
    @GetMapping("/selectMyRequest")
    public List<DriftingRecord> selectMyRequest(@RequestAttribute("userId") Long requesterId) {
        return driftingService.selectByRequesterId(requesterId);
    }

    // 查询我的求漂
    @GetMapping("/selectMyBookRequests")
    public List<Book> selectMyBookRequest(@RequestAttribute("userId") Long requesterId) {
        List<DriftingRecord> requests = driftingService.selectByRequesterId(requesterId);
        List<Book> result = new ArrayList<>();
        for (DriftingRecord request : requests) {
            Long bookId = request.getBookId();
            result.add(bookService.selectById(bookId));
        }
        return result;
    }


    // 查询给我的请求
    @GetMapping("/selectDriftingToMe")
    public List<DriftingRequest> selectDriftingToMe(@RequestAttribute("userId") Long ownerId) {
        List<DriftingRecord> records = driftingService.selectByOwnerId(ownerId);
        List<DriftingRequest> driftingRequest = new ArrayList<>();
        for (DriftingRecord record : records) {
            DriftingRequest request = new DriftingRequest();
            request.setId(record.getId());

            User requester = userService.selectById(record.getRequesterId());
            request.setRequester(requester);

            Book book = bookService.selectById(record.getBookId());
            request.setBook(book);

            // 如果请求者和拥有者是同一个人，说明漂流请求已经被接受
            if (record.getOwnerId().equals(record.getRequesterId())) {
                continue;
            }

            driftingRequest.add(request);
        }

        return driftingRequest;
    }

    @PostMapping("/drift")
    public ResponseEntity<Result> drift(
            @RequestParam("requestId") Long requestId,
            @RequestAttribute("userId") Long ownerId
    ) {
        Result result = driftingService.drift(requestId, ownerId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PostMapping("/reject")
    public ResponseEntity<Result> reject(
            @RequestParam("requestId") Long requestId,
            @RequestAttribute("userId") Long ownerId
    ) {
        Result result = driftingService.reject(requestId, ownerId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    // uploader收漂，会将requesterId设置为uploaderId，并且修改状态为Finished
    @PostMapping("/finish")
    public ResponseEntity<Result> finish(
            @RequestParam("bookId") Long bookId,
            @RequestAttribute("userId") Long uploaderId
    ) {
        Result result = driftingService.finish(bookId, uploaderId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
}
