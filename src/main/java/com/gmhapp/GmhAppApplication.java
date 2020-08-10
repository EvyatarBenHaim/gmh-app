package com.gmhapp;

import com.gmhapp.repositories.ProductRepository;
import com.gmhapp.services.FilesStorageService;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;


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
