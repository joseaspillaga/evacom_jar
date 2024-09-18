package pe.com.sedapal.evaluacion.dao;

import java.sql.SQLException;
import java.util.List;

import pe.com.sedapal.evaluacion.model.Pid;
import pe.com.sedapal.evaluacion.model.Trabajador;

public interface IPidDAO {

	public List<Pid> obtenerCorreosEvaluadosRegistroPendiente(String codCalendario) throws SQLException;
	public List<Pid> obtenerCorreosEvaluadosCumplimientoPendiente(String codCalendario) throws SQLException;
	public List<Pid> obtenerCorreosEvaluadoresRegistroPendiente(String codCalendario) throws SQLException;
	public List<Pid> obtenerCorreosEvaluadoresCumplimientoPendiente(String codCalendario) throws SQLException;
	
}
