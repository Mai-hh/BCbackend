package com.huaihao.bookcrosser.backend.service.impl;

import com.huaihao.bookcrosser.backend.mbg.mapper.BookMapper;
import com.huaihao.bookcrosser.backend.mbg.mapper.DriftingMapper;
import com.huaihao.bookcrosser.backend.mbg.model.Book;
import com.huaihao.bookcrosser.backend.mbg.model.DriftingRequest;
import com.huaihao.bookcrosser.backend.mbg.model.RequestStatus;
import com.huaihao.bookcrosser.backend.service.DriftingService;
import com.huaihao.bookcrosser.backend.service.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class DriftingServiceImpl implements DriftingService {
    @Autowired
    private DriftingMapper driftingMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Result request(Long bookId, Long userId) {
        Book book = bookMapper.selectById(bookId);
        if (book == null) {
            return Result.failed(404, "请求图书不存在");
        }

        if (Objects.equals(book.getOwnerId(), userId)) {
            return Result.failed(400, "不能请求自己的图书");
        }

        boolean val = driftingMapper.request(
                bookId,
                userId,
                book.getOwnerId(),
                book.getUploaderId(),
                RequestStatus.REQUESTING.getStatusString(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        return Result.success("求漂成功", val);
    }

    @Override
    public List<DriftingRequest> selectByRequesterId(Long requesterId) {
        return driftingMapper.selectByRequesterId(requesterId);
    }

    @Override
    public List<DriftingRequest> selectByOwnerId(Long ownerId) {
        return driftingMapper.selectByOwnerId(ownerId);
    }
}
