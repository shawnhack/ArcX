package com.bitmind.app;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.bitmind.dao.entity.Address;
import com.bitmind.dao.entity.Portfolio;
import com.bitmind.dao.entity.User;
import com.googlecode.objectify.ObjectifyService;

@Component
public class ApplicationListenerBean implements
		ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		ObjectifyService.register(User.class);
		ObjectifyService.register(Address.class);
		ObjectifyService.register(Portfolio.class);
	}

}
