package pe.com.sedapal.evaluacion.dao;

import java.sql.SQLException;
import java.util.List;

import pe.com.sedapal.evaluacion.model.Trabajador;

public interface IRetroalimentacionDAO {

	public List<Trabajador> obtenerCorreosEvaluadosRetroalimentacionPendiente(String codCalendario) throws SQLException;
	public List<Trabajador> obtenerCorreosEvaluadoresRetroalimentacionPendiente(String codCalendario) throws SQLException;
}
