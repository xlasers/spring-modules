package com.xlasers.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type Application.
 *
 * @package: com.xlasers.spring
 * @author: Elijah.D
 * @time: CreateAt 2018/9/27 && 21:24
 * @description: App
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@SpringBootApplication
public class Application {
	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {

		new SpringApplication(Application.class).run(args);

	}
}
