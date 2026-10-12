package io.github.misael.support_assistant;

import org.springframework.boot.SpringApplication;

public class TestSupportAssistantApplication {

	public static void main(String[] args) {
		SpringApplication.from(SupportAssistantApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
