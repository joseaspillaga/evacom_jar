package pe.com.sedapal.evaluacion.service;

import java.sql.SQLException;

public interface INotificaciones {
	Boolean notificacionConocimiento(String nombre, String correo, String calendario) throws SQLException;	
}
