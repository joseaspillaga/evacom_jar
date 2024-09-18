package pe.com.sedapal.evaluacion.service;

import java.sql.SQLException;
import java.util.List;

import pe.com.sedapal.evaluacion.model.Trabajador;

public interface IConocimientoService {

	public List<Trabajador> obtenerCorreosEvaluado(String codCalendario) throws SQLException ;

	public void enviarCorreoRecordatorioEvaluadoConocimiento(String codCalendario) throws SQLException;
}
