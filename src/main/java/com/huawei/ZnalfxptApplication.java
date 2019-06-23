package com.huawei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.huawei.mapper")
@SpringBootApplication
public class ZnalfxptApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZnalfxptApplication.class, args);
	}

}
