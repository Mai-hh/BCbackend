package com.huaihao.bookcrosser.backend.mbg.mapper;

import com.huaihao.bookcrosser.backend.mbg.model.DriftingRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DriftingMapper {
    boolean request(
            @Param("bookId") Long bookId,
            @Param("requesterId") Long requesterId,
            @Param("ownerId") Long ownerId,
            @Param("uploaderId") Long uploaderId,
            @Param("status") String status,
            @Param("createdAt") java.time.LocalDateTime createdAt,
            @Param("updatedAt") java.time.LocalDateTime updatedAt
    );

    List<DriftingRequest> selectByRequesterId(@Param("requesterId") Long requesterId);

    List<DriftingRequest> selectByOwnerId(@Param("ownerId") Long ownerId);
}
