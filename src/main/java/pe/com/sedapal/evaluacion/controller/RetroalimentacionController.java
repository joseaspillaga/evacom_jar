package pe.com.sedapal.evaluacion.controller;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pe.com.sedapal.evaluacion.service.IRetroalimentacionService;

@SpringBootApplication(scanBasePackages = {"pe.com.sedapal.evaluacion"})
public class RetroalimentacionController {

	@Autowired
	private IRetroalimentacionService service;
	
	
	public static void main(String[] args) {
		 SpringApplication.run(RetroalimentacionController.class, args);
	}
	
	@PostConstruct
	public void init() {
		envioCorreos();
		//prueba();
	}
	
	public void envioCorreos() {
		try {
			System.out.println("inicio");
			service.enviarCorreoRecordatorioEvaluadoRetroalimentacionPendiente("C003");
			service.enviarCorreoRecordatorioEvaluadorRetroalimentacionPendiente("C003");
			 System.out.println("fin");
			 System.exit(0);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
