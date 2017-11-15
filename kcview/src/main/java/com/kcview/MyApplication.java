package com.kcview;


import java.io.IOException;

//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "com.kcview")
public class MyApplication {  
	
	public static void main(String[] args) throws IOException {
		SpringApplication.run(MyApplication.class, args);
	}	
		
}            