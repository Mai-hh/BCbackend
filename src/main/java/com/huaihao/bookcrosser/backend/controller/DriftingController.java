package com.huaihao.bookcrosser.backend.controller;

import com.huaihao.bookcrosser.backend.mbg.model.DriftingRequest;
import com.huaihao.bookcrosser.backend.service.BookService;
import com.huaihao.bookcrosser.backend.service.DriftingService;
import com.huaihao.bookcrosser.backend.service.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drifting")
public class DriftingController {

    @Autowired
    private DriftingService driftingService;

    // 求漂
    @PostMapping("/request")
    public ResponseEntity<Result> request(
            @RequestParam("bookId") Long bookId,
            @RequestAttribute("userId") Long requesterId
    ) {
        Result result = driftingService.request(bookId, requesterId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    // 查询我的求漂
    @GetMapping("/selectMyRequest")
    public List<DriftingRequest> selectMyRequest(@RequestAttribute("userId") Long requesterId) {
        return driftingService.selectByRequesterId(requesterId);
    }


    // 查询给我的请求
    @GetMapping("/selectDriftingToMe")
    public List<DriftingRequest> selectDriftingToMe(@RequestAttribute("userId") Long ownerId) {
        return driftingService.selectByOwnerId(ownerId);
    }

    // 接受请求
    // 起漂

    // 拒绝请求
    // 释放图书
}
