package com.JefersonBLuz.literalura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.JefersonBLuz.literalura.views.Menu;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	private final Menu menu;

	public LiteraluraApplication(Menu menu) {
		this.menu = menu;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		menu.iniciar();
	}
}
