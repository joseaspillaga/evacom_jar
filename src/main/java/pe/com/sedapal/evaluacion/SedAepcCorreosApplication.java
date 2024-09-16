package pe.com.sedapal.evaluacion;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import pe.com.sedapal.evaluacion.service.INotificaciones;
import pe.com.sedapal.evaluacion.service.impl.NoficacionesImpl;

@SpringBootApplication
@ComponentScan(basePackages = "pe.com.sedapal.evaluacion")
public class SedAepcCorreosApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(SedAepcCorreosApplication.class, args);
		enviar();
	}
	
	static void enviar() {
		try {
			 INotificaciones service = new NoficacionesImpl(); 
			 service.notificacionConocimiento("Jose", "jaaspillaga@indracompany.com","2025");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
