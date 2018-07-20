package com.cn.listener;

import com.cn.framework.common.spring.ext.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

public class MyApplicationListener implements ApplicationListener<ApplicationPreparedEvent>{

	private static final Logger logger = LoggerFactory.getLogger(MyApplicationListener.class);

	@Override
	public void onApplicationEvent(ApplicationPreparedEvent event) {
		ConfigurableApplicationContext cac = event.getApplicationContext();
		SpringContextHolder.setApplicationContextTest(cac);
	}
}
