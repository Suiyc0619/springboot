package com.sui.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.sui.study.mapper")
@EnableCaching
@EnableRabbit
@EnableAsync//异步方法
@EnableScheduling//定时任务
public class StudyApplication {

	public static void main(String[] args) {
		/*防止elasticsearch启动netty冲突报错*/
//		System.setProperty("es.set.netty.runtime.available.processors", "false");
		SpringApplication.run(StudyApplication.class, args);
	}

}

