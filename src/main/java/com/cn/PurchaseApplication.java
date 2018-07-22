package com.cn;

import com.cn.listener.MyApplicationListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;

@SpringBootApplication
public class PurchaseApplication {

	private static final Logger logger = LoggerFactory.getLogger(PurchaseApplication.class);
	public static void main(String[] args) {
		logger.info("======================== Spring Boot 正在启动 =======================");
		SpringApplication app = new SpringApplication(PurchaseApplication.class);
		app.addListeners(new MyApplicationListener());
		app.run(args);
		logger.info("======================== Spring Boot 启动完成 =======================");



	}


//	@Bean
//
//	public SpringContextHolder springContextHolder() {
//		return new SpringContextHolder();
//	}

}
