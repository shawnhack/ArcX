package com.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.appengine.api.utils.SystemProperty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:war/WEB-INF/spring-servlet.xml",
		"file:war/WEB-INF/spring-security.xml" })
public abstract class AbstractTest {
	@Before
	public void init() {
		SystemProperty.environment
				.set(SystemProperty.Environment.Value.Development);

	}
}
