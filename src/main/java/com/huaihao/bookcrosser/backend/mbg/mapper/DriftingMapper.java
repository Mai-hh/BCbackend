package com.huaihao.bookcrosser.backend.mbg.mapper;

import com.huaihao.bookcrosser.backend.mbg.model.DriftingRecord;
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

    DriftingRecord selectDriftingRequestById(@Param("id") Long id);

    List<DriftingRecord> selectByRequesterId(@Param("requesterId") Long requesterId);

    List<DriftingRecord> selectByOwnerId(@Param("ownerId") Long ownerId);

    boolean update(DriftingRecord record);

    boolean deleteById(@Param("id") Long id);

    DriftingRecord selectByBookRequesterId(@Param("bookId") Long bookId, @Param("requesterId") Long requesterId);

    List<DriftingRecord> selectByBookOwnerId(@Param("bookId") Long bookId, @Param("ownerId") Long ownerId);
    List<DriftingRecord> selectByBookId(@Param("bookId") Long bookId);

    List<DriftingRecord> selectByBookUploaderId(@Param("bookId") Long bookId, @Param("uploaderId") Long uploaderId);

    boolean deleteByBookUploaderId(@Param("bookId") Long bookId, @Param("uploaderId") Long uploaderId);

    boolean deleteByBookId(@Param("bookId") Long bookId);
}
