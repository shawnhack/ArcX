package com.bitmind.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListenerBean implements
		ApplicationListener<ContextRefreshedEvent> {

	private static Logger log = LoggerFactory
			.getLogger(ApplicationListenerBean.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
	}

}
