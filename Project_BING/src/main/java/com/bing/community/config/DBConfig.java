package com.bing.community.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.bing.community.model.dao")
public class DBConfig {

}