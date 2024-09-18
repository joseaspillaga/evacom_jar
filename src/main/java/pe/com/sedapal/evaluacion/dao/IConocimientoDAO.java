package pe.com.sedapal.evaluacion.dao;

import java.sql.SQLException;
import java.util.List;

import pe.com.sedapal.evaluacion.model.Trabajador;

public interface IConocimientoDAO {

	public List<Trabajador> obtenerCorreosEvaluado(String codCalendario) throws SQLException ;
}
