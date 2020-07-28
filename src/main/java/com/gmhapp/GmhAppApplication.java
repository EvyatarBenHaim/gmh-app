package com.gmhapp;

import com.gmhapp.services.FilesStorageService;
import com.gmhapp.services.ProductService;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;


@SpringBootApplication
public class GmhAppApplication implements CommandLineRunner {

	@Resource
    FilesStorageService storageService;
	private final static Logger logger = Logger.getLogger(GmhAppApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(GmhAppApplication.class, args);
		BasicConfigurator.configure();
		logger.info("app is running...");

	}

	@Override
	public void run(String... arg) throws Exception {
		//storageService.deleteAll();
		storageService.init();
	}

}
