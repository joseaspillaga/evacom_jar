package pe.com.sedapal.evaluacion.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.evaluacion.dao.IEvaluacionDAO;
import pe.com.sedapal.evaluacion.dao.IUtilDAO;
import pe.com.sedapal.evaluacion.model.Calendario;
import pe.com.sedapal.evaluacion.model.Trabajador;
import pe.com.sedapal.evaluacion.service.IEvaluacionService;
import pe.com.sedapal.evaluacion.service.INotificaciones;

@Service
public class EvaluacionServiceImpl implements IEvaluacionService{

	@Autowired
	INotificaciones notificacionService;
		
	@Autowired
	IEvaluacionDAO dao;
	
	@Autowired
	IUtilDAO daoUtil;
	
	@Override
	public List<Trabajador> obtenerCorreosAutoevaluacionPendiente(String calendario) throws SQLException {
		return dao.obtenerCorreosAutoevaluacionPendiente(calendario);
	}
	
	@Override
	public List<Trabajador> obtenerCorreosEvaluacionPendiente(String calendario) throws SQLException {
		return dao.obtenerCorreosEvaluacionPendiente(calendario);
	}
	
	
	@Override
	public void enviarCorreoRecordatorioAutoevaluacionPendiente(String codCalendario) throws SQLException {
		
		List<Trabajador> evaluados = this.obtenerCorreosAutoevaluacionPendiente(codCalendario);
		Calendario calendario = daoUtil.calendario(codCalendario);
		
		for (Trabajador evaluado : evaluados) {
			if(evaluado.getCorreo()!= null && !evaluado.getCorreo().trim().isEmpty() ) {
				notificacionService.notificacionAutoevaluacion(
						evaluado.getApellidosNombres(), "fsvargas@indracompany.com", calendario.getVNombre());
			}
		}
		
	}
	
	@Override
	public void enviarCorreoRecordatorioEvaluacionPendiente(String codCalendario) throws SQLException {
		
		List<Trabajador> evaluadores = this.obtenerCorreosEvaluacionPendiente(codCalendario);
		Calendario calendario = daoUtil.calendario(codCalendario);
		
		for (Trabajador evaluador : evaluadores) {
			if(evaluador.getCorreo()!= null && !evaluador.getCorreo().trim().isEmpty() ) {
				notificacionService.notificacionEvaluacion(
						evaluador.getApellidosNombres(), "fsvargas@indracompany.com", calendario.getVNombre());
			}
		}
		
	}
	
	
	
	
}
