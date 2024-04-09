package com.huaihao.bookcrosser.backend.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.huaihao.bookcrosser.backend.mbg.mapper")
public class MyBatisConfig {
}
