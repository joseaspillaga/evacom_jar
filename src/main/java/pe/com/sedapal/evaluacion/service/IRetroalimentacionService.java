package pe.com.sedapal.evaluacion.service;

import java.sql.SQLException;
import java.util.List;

import pe.com.sedapal.evaluacion.model.Trabajador;

public interface IRetroalimentacionService {

	public List<Trabajador> obtenerCorreosEvaluadosRetroalimentacionPendiente(String codCalendario) throws SQLException;
	public List<Trabajador> obtenerCorreosEvaluadoresRetroalimentacionPendiente(String codCalendario) throws SQLException;
	void enviarCorreoRecordatorioEvaluadoRetroalimentacionPendiente(String codCalendario) throws SQLException;
	void enviarCorreoRecordatorioEvaluadorRetroalimentacionPendiente(String codCalendario) throws SQLException;
}
