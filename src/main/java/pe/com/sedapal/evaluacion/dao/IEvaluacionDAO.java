package pe.com.sedapal.evaluacion.dao;

import java.sql.SQLException;
import java.util.List;

import pe.com.sedapal.evaluacion.model.Trabajador;

public interface IEvaluacionDAO {

	public List<Trabajador> obtenerCorreosAutoevaluacionPendiente(String codCalendario) throws SQLException;
	public List<Trabajador> obtenerCorreosEvaluacionPendiente(String codCalendario) throws SQLException;
	
}
