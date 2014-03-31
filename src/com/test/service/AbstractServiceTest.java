package com.test.service;

import org.junit.After;
import org.junit.Before;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.test.AbstractTest;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
public abstract class AbstractServiceTest extends AbstractTest {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig()
					.setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

	@Before
	public void initService() {
		helper.setUp();
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}

}
