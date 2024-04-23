package com.huaihao.bookcrosser.backend.service;

import com.huaihao.bookcrosser.backend.mbg.model.DriftingRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DriftingService {
    Result request(@Param("bookId") Long bookId, @Param("userId") Long userId);

    List<DriftingRequest> selectByRequesterId(@Param("requesterId") Long requesterId);

    List<DriftingRequest> selectByOwnerId(@Param("ownerId") Long ownerId);
}
