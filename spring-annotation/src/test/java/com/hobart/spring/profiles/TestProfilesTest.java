package com.hobart.spring.profiles;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestProfilesTest {

	@Test
	public void testProfiles() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestProfiles.class);
		
		
		
		context.close();
	}

}
