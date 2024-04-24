package com.huaihao.bookcrosser.backend.service;

import com.huaihao.bookcrosser.backend.mbg.model.DriftingRecord;
import com.huaihao.bookcrosser.backend.mbg.model.RequestStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DriftingService {
    Result request(@Param("bookId") Long bookId, @Param("requesterId") Long requesterId, RequestStatus status);

    List<DriftingRecord> selectByRequesterId(@Param("requesterId") Long requesterId);

    List<DriftingRecord> selectByOwnerId(@Param("ownerId") Long ownerId);

    Result drift(@Param("requestId") Long requestId, @Param("userId") Long ownerId);

    Result finish(@Param("bookId") Long bookId, @Param("userId") Long uploaderId);

    Result reject(@Param("requestId") Long requestId, @Param("userId") Long ownerId);
}
