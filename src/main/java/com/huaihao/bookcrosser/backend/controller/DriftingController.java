package com.huaihao.bookcrosser.backend.controller;

import com.huaihao.bookcrosser.backend.service.BookService;
import com.huaihao.bookcrosser.backend.service.DriftingService;
import com.huaihao.bookcrosser.backend.service.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drifting")
public class DriftingController {

    @Autowired
    private DriftingService driftingService;
    
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
}
