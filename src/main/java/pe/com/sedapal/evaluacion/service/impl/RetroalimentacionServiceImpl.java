package pe.com.sedapal.evaluacion.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.evaluacion.dao.IRetroalimentacionDAO;
import pe.com.sedapal.evaluacion.dao.IUtilDAO;
import pe.com.sedapal.evaluacion.model.Calendario;
import pe.com.sedapal.evaluacion.model.Trabajador;
import pe.com.sedapal.evaluacion.service.INotificaciones;
import pe.com.sedapal.evaluacion.service.IRetroalimentacionService;

@Service
public class RetroalimentacionServiceImpl implements IRetroalimentacionService{

	@Autowired
	INotificaciones notificacionService;
		
	@Autowired
	IRetroalimentacionDAO dao;
	
	@Autowired
	IUtilDAO daoUtil;
	
	@Override
	public List<Trabajador> obtenerCorreosEvaluadosRetroalimentacionPendiente(String calendario) throws SQLException {
		return dao.obtenerCorreosEvaluadosRetroalimentacionPendiente(calendario);
	}
	
	@Override
	public List<Trabajador> obtenerCorreosEvaluadoresRetroalimentacionPendiente(String calendario) throws SQLException {
		return dao.obtenerCorreosEvaluadoresRetroalimentacionPendiente(calendario);
	}

	@Override
	public void enviarCorreoRecordatorioEvaluadoRetroalimentacionPendiente(String codCalendario) throws SQLException {
		
		List<Trabajador> evaluados = this.obtenerCorreosEvaluadosRetroalimentacionPendiente(codCalendario);
		Calendario calendario = daoUtil.calendario(codCalendario);
		
		for (Trabajador evaluado : evaluados) {
			if(evaluado.getCorreo()!= null && !evaluado.getCorreo().trim().isEmpty() ) {
				String tipoPersona = "su evaluador";
				String accion = "ha realizado";
				notificacionService.notificacionRetroalimentacion(
						evaluado.getApellidosNombres(), "fsvargas@indracompany.com", tipoPersona,accion, calendario.getVNombre());
			}
		}
		
	}

	@Override
	public void enviarCorreoRecordatorioEvaluadorRetroalimentacionPendiente(String codCalendario) throws SQLException {
		
		List<Trabajador> evaluadores = this.obtenerCorreosEvaluadoresRetroalimentacionPendiente(codCalendario);
		Calendario calendario = daoUtil.calendario(codCalendario);
		
		for (Trabajador evaluador : evaluadores) {
			if(evaluador.getCorreo()!= null && !evaluador.getCorreo().trim().isEmpty() ) {
				String tipoPersona = "su evaluado";
				String accion = "ha realizado";
				
				notificacionService.notificacionRetroalimentacion(
						evaluador.getApellidosNombres(), "fsvargas@indracompany.com", tipoPersona, accion, calendario.getVNombre());
			}
		}
		
	}
	
	
}
