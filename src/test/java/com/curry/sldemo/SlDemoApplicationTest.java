package com.curry.sldemo;

import com.curry.sldemo.controller.PeopleController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SlDemoApplicationTest {

	@Autowired
	private PeopleController peopleController;

	@Test
	void contextLoads() {
		assertThat(peopleController).isNotNull();
	}

}
