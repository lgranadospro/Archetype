package es.profile.parameter;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Test
	public void contextLoads() throws Exception {
		Assertions.assertThat(this.context).isNotNull();
	}
}
