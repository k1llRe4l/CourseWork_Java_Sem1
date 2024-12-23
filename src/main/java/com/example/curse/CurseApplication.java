package com.example.curse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс приложения, который запускает Spring Boot приложение.
 */
@SpringBootApplication
public class CurseApplication {

	/**
	 * Точка входа в приложение. Запускает Spring Boot приложение.
	 *
	 * @param args аргументы командной строки
	 */
	public static void main(String[] args) {
		SpringApplication.run(CurseApplication.class, args);
	}
}
