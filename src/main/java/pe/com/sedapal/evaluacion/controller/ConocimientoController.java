package pe.com.sedapal.evaluacion.controller;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import pe.com.sedapal.evaluacion.dao.IUtilDAO;
import pe.com.sedapal.evaluacion.service.INotificaciones;
import pe.com.sedapal.evaluacion.service.impl.NoficacionesImpl;


@SpringBootApplication(scanBasePackages = {"pe.com.sedapal.evaluacion"})
public class ConocimientoController {

	@Autowired
	 private INotificaciones service;

	public static void main(String[] args) {
		 SpringApplication.run(ConocimientoController.class, args);
	}
	
	@PostConstruct
	public void enviar() {
		try {
			 //INotificaciones service = new NoficacionesImpl();  
			System.out.println("okkk");
			 service.notificacionConocimiento("Jose", "jaaspillaga@indracompany.com","2025");
			 System.out.println("bye");
			 System.exit(0);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
