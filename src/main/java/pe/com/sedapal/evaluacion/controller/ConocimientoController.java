package pe.com.sedapal.evaluacion.controller;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pe.com.sedapal.evaluacion.service.IConocimientoService;
import pe.com.sedapal.evaluacion.service.INotificaciones;


@SpringBootApplication(scanBasePackages = {"pe.com.sedapal.evaluacion"})
public class ConocimientoController {

	@Autowired
	private INotificaciones serviceCorreo;

	@Autowired
	private IConocimientoService service;
	
	
	public static void main(String[] args) {
		 SpringApplication.run(ConocimientoController.class, args);
	}
	
	@PostConstruct
	public void init() {
		envioCorreos();
		//prueba();
	}
	
	public void envioCorreos() {
		try {
			System.out.println("okkk");
			service.enviarCorreoRecordatorioEvaluadoConocimiento("C003");
			 System.out.println("bye");
			 System.exit(0);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void prueba() {
		try {
			 //INotificaciones service = new NoficacionesImpl();  
			System.out.println("okkk");
			serviceCorreo.notificacionConocimiento("Felix", "fsvargas@indracompany.com","2025");
			 System.out.println("bye");
			 System.exit(0);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
}
