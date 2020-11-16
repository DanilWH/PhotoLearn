package com.example.PhotoLearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.Collections;

@SpringBootApplication
public class PhotoLearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoLearnApplication.class, args);
	}

	public PhotoLearnApplication(FreeMarkerConfigurer freemarkerConfigurer) {
		freemarkerConfigurer.getTaglibFactory()
							.setClasspathTlds(Collections.singletonList("/META-INF/security.tld"));
	}

}
