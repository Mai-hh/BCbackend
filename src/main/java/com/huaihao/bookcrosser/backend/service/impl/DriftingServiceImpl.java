package com.huaihao.bookcrosser.backend.service.impl;

import com.huaihao.bookcrosser.backend.mbg.mapper.BookMapper;
import com.huaihao.bookcrosser.backend.mbg.mapper.DriftingMapper;
import com.huaihao.bookcrosser.backend.mbg.model.Book;
import com.huaihao.bookcrosser.backend.mbg.model.DriftingRecord;
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

        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failed(500, "服务器错误");
        }
    }

    @Override
    public List<DriftingRecord> selectByRequesterId(Long requesterId) {
        return driftingMapper.selectByRequesterId(requesterId);
    }

    @Override
    public List<DriftingRecord> selectByOwnerId(Long ownerId) {
        return driftingMapper.selectByOwnerId(ownerId);
    }

    @Override
    public Result drift(Long requestId, Long ownerId) {
        DriftingRecord driftingRecord = driftingMapper.selectDriftingRequestById(requestId);
        Book book = bookMapper.selectById(driftingRecord.getBookId());
        book.setOwnerId(driftingRecord.getRequesterId());
        book.setUpdatedAt(LocalDateTime.now());
        if (!bookMapper.update(book)) {
            return Result.failed(500, "服务器错误");
        }

        driftingRecord.setStatus(RequestStatus.DRIFTING.getStatusString());
        driftingRecord.setOwnerId(driftingRecord.getRequesterId());
        driftingRecord.setUpdatedAt(LocalDateTime.now().toString());

        if (driftingMapper.update(driftingRecord)) {
            return Result.success("起漂成功");
        }

        return Result.failed(500, "服务器错误");

    }

    @Override
    public Result reject(Long requestId, Long ownerId) {
        DriftingRecord driftingRecord = driftingMapper.selectDriftingRequestById(requestId);
        Book book = bookMapper.selectById(driftingRecord.getBookId());
        if (!Objects.equals(book.getUploaderId(), ownerId)) {
            return Result.failed(403, "非书主无权限拒绝请求");
        }

        if (driftingMapper.deleteById(requestId)) {
            return Result.success("拒绝请求成功");
        }

        return Result.failed(500, "服务器错误");
    }
}
