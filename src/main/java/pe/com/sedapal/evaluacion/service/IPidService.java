package pe.com.sedapal.evaluacion.service;

import java.sql.SQLException;
import java.util.List;

import pe.com.sedapal.evaluacion.model.Pid;
import pe.com.sedapal.evaluacion.model.Trabajador;

public interface IPidService {

	public List<Pid> obtenerCorreosEvaluadosRegistroPendiente(String codCalendario) throws SQLException;
	public List<Pid> obtenerCorreosEvaluadosCumplimientoPendiente(String codCalendario) throws SQLException;
	public List<Pid> obtenerCorreosEvaluadoresRegistroPendiente(String codCalendario) throws SQLException;
	public List<Pid> obtenerCorreosEvaluadoresCumplimientoPendiente(String codCalendario) throws SQLException;
	public void enviarCorreoRecordatorioEvaluadoRegistroPendiente(String codCalendario) throws SQLException;
	public void enviarCorreoRecordatorioEvaluadoCumplimientoPendiente(String codCalendario) throws SQLException;
	public void enviarCorreoRecordatorioEvaluadorRegistroPendiente(String codCalendario) throws SQLException;
	public void enviarCorreoRecordatorioEvaluadorCumplimientoPendiente(String codCalendario) throws SQLException;
}
