package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


/**
 * 
 * @author Administrator
 *
 */
@SpringBootApplication
@ServletComponentScan//使用servlet时，需要此注解
@MapperScan("com.drl.mapper")
public class Boot1Application {

	public static void main(String[] args) {
		SpringApplication.run(Boot1Application.class, args);
	}
}
