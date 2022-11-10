package com.dh.C23EjProfe;

import com.dh.C23EjProfe.dao.BD;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Clase23Application {

	public static void main(String[] args) {
		BD.crearTablas();
		SpringApplication.run(Clase23Application.class, args);
	}

}
