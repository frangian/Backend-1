package com.dh.ClinicaOdontologica;

import com.dh.ClinicaOdontologica.dao.BD;
import com.dh.ClinicaOdontologica.model.Odontologo;
import com.dh.ClinicaOdontologica.service.OdontologoService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ClinicaOdontologicaApplicationTests {

	@Test
	public void listarOdontologosTest() {
		OdontologoService odontologoService = new OdontologoService();
		BD.crearTablas();
		List<Odontologo> listaEncontrada = odontologoService.listarOdontologos();
		Assertions.assertEquals(1,listaEncontrada.size());
	}
	@Test
	void contextLoads() {
	}

}
