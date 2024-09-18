package pe.com.sedapal.evaluacion.service;

import java.sql.SQLException;
import java.util.List;

import pe.com.sedapal.evaluacion.model.Trabajador;

public interface IEvaluacionService {

	public List<Trabajador> obtenerCorreosAutoevaluacionPendiente(String codCalendario) throws SQLException;
	public List<Trabajador> obtenerCorreosEvaluacionPendiente(String codCalendario) throws SQLException;
	void enviarCorreoRecordatorioAutoevaluacionPendiente(String codCalendario) throws SQLException;
	void enviarCorreoRecordatorioEvaluacionPendiente(String codCalendario) throws SQLException;
}
