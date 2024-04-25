package com.huaihao.bookcrosser.backend.service.impl;

import com.huaihao.bookcrosser.backend.mbg.mapper.BookMapper;
import com.huaihao.bookcrosser.backend.mbg.mapper.DriftingMapper;
import com.huaihao.bookcrosser.backend.mbg.model.Book;
import com.huaihao.bookcrosser.backend.mbg.model.BookStatus;
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
    public Result request(Long bookId, Long requesterId, RequestStatus status) {
        Book book = bookMapper.selectById(bookId);
        if (book == null) {
            return Result.failed(404, "请求图书不存在");
        }

        if (Objects.equals(book.getOwnerId(), requesterId)) {
            return Result.failed(400, "不能请求拥有的图书");
        }

        try {
            boolean val = driftingMapper.request(
                    bookId,
                    requesterId,
                    book.getOwnerId(),
                    book.getUploaderId(),
                    status.getStatusString(),
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );
            book.setStatus(BookStatus.REQUESTED.getStatusString());
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

        Long newOwnerId = driftingRecord.getRequesterId();
        book.setOwnerId(newOwnerId);
        book.setUpdatedAt(LocalDateTime.now());
        if (!bookMapper.update(book)) {
            return Result.failed(500, "服务器错误");
        }

        // 更新 / 删除？
        if (!driftingMapper.deleteById(requestId)) {
            return Result.failed(500, "服务器错误");
        }


        List<DriftingRecord> remainingRecords = driftingMapper.selectByBookId(book.getId());
        remainingRecords.forEach(record -> {
            record.setOwnerId(newOwnerId);
            driftingMapper.update(record);
        });

        return Result.success("起漂成功");

    }

    // 收漂：删除book相关的driftingRecord, 新增一个driftingRecord的requester为自己，设置状态为finished
    @Override
    public Result finish(Long bookId, Long uploaderId) {

        Book book = bookMapper.selectById(bookId);
        if (!Objects.equals(book.getUploaderId(), uploaderId)) {
            return Result.failed(403, "非上传者无权限收漂");
        }

        boolean deleteComplete = driftingMapper.deleteByBookId(bookId);
        if (!deleteComplete) {
            System.out.println("没有另外的对这本书的请求");
        }

        return request(bookId, uploaderId, RequestStatus.FINISHED);
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
