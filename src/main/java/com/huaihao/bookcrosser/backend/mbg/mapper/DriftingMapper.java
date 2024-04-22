package com.huaihao.bookcrosser.backend.mbg.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DriftingMapper {
    boolean request(
            @Param("bookId") Long bookId,
            @Param("requesterId") Long requesterId,
            @Param("ownerId") Long ownerId,
            @Param("status") String status,
            @Param("createdAt") java.time.LocalDateTime createdAt,
            @Param("updatedAt") java.time.LocalDateTime updatedAt
    );
}
