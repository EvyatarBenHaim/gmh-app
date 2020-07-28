package com.gmhapp;

import com.gmhapp.services.FilesStorageService;
import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;


@SpringBootApplication
public class GmhAppApplication implements CommandLineRunner {

	@Resource
    FilesStorageService storageService;



	public static void main(String[] args) {
		SpringApplication.run(GmhAppApplication.class, args);
		BasicConfigurator.configure();

	}

	@Override
	public void run(String... arg) throws Exception {
		//storageService.deleteAll();
		storageService.init();
	}

}
