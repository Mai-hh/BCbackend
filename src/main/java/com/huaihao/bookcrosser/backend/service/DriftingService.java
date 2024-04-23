package com.huaihao.bookcrosser.backend.service;

import com.huaihao.bookcrosser.backend.mbg.model.DriftingRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface DriftingService {
    Result request(@Param("bookId") Long bookId, @Param("userId") Long userId);

    List<DriftingRecord> selectByRequesterId(@Param("requesterId") Long requesterId);

    List<DriftingRecord> selectByOwnerId(@Param("ownerId") Long ownerId);

    Result drift(@Param("requestId") Long requestId, @Param("userId") Long ownerId);

    Result reject(@Param("requestId") Long requestId, @Param("userId") Long ownerId);
}
