package com.apireserva;

import org.junit.jupiter.api.Test; //Es la anotación de JUnit 5 (también llamado JUnit Jupiter). Marca un metodo como prueba unitaria.
import org.springframework.boot.test.context.SpringBootTest;

//arranca la aplicacion como si fuera la original
@SpringBootTest
class ApiReservaApplicationTests {

	@Test //indica que es una prueba
	void contextLoads() {
	}

}
