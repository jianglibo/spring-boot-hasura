package me.resp.scaffold;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ScaffoldApplicationTests {

	@Autowired
	private Environment environment;
	@Autowired
	private ScaffoldProperties properties;

	@Autowired
	public void getActiveProfiles() {
		for (String profileName : environment.getActiveProfiles()) {
			System.out.println("Currently active profile - " + profileName);
		}
	}

	@Test
	void contextLoads() {
		assertThat(environment.getActiveProfiles()).contains("test");
		assertThat(properties.getTestValue()).isEqualTo("test");
	}

}
