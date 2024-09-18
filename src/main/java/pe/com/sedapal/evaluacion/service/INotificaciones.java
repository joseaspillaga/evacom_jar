package pe.com.sedapal.evaluacion.service;

import java.sql.SQLException;
import java.util.Date;

public interface INotificaciones {
	Boolean notificacionConocimiento(String nombre, String correo, String calendario) throws SQLException;

	Boolean notificacionAutoevaluacion(String nombre, String correo, String calendario) throws SQLException;

	Boolean notificacionHechoResaltante(String nombre, String correo, String calendario) throws SQLException;

	Boolean notificacionEvaluacion(String nombre, String correo, String calendario) throws SQLException;

	Boolean notificacionCalibracion(String nombre, String correo, String calendario, Date fecha) throws SQLException;

	Boolean notificacionRetroalimentacion(String nombre, String correo, String tipoPersona, String calendario)
			throws SQLException;

	Boolean notificacionPid(String nombre, String correo, String calendario, String accion, String tipoRegistro)
			throws SQLException;	
}
