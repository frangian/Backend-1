package com.dh.clase23;

import com.dh.clase23.dao.BD;
import com.dh.clase23.model.Odontologo;
import com.dh.clase23.service.OdontologoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Clase23ApplicationTests {

	@Test
	public void listarOdontologosTest(){
		OdontologoService odontologoService= new OdontologoService();
		BD.crearTablas();
		List<Odontologo> listaEncontrada=odontologoService.listarOdontologos();
		Assertions.assertEquals(1,listaEncontrada.size());
	}
	@Test
	void contextLoads() {
	}

}
