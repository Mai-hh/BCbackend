package com.huaihao.bookcrosser.backend.service;

import org.apache.ibatis.annotations.Param;

public interface DriftingService {
    Result request(@Param("bookId") Long bookId, @Param("userId") Long userId);
}
