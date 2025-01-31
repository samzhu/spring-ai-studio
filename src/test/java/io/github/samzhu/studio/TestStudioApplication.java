package io.github.samzhu.studio;

import org.springframework.boot.SpringApplication;

public class TestStudioApplication {

	public static void main(String[] args) {
		SpringApplication.from(StudioApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
