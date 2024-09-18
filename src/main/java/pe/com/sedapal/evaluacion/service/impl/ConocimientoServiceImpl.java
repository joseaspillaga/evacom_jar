package pe.com.sedapal.evaluacion.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.evaluacion.dao.IConocimientoDAO;
import pe.com.sedapal.evaluacion.dao.IUtilDAO;
import pe.com.sedapal.evaluacion.model.Calendario;
import pe.com.sedapal.evaluacion.model.Trabajador;
import pe.com.sedapal.evaluacion.service.IConocimientoService;
import pe.com.sedapal.evaluacion.service.INotificaciones;

@Service
public class ConocimientoServiceImpl implements IConocimientoService{
	
	@Autowired
	INotificaciones notificacionService;
		
	@Autowired
	IConocimientoDAO dao;
	
	@Autowired
	IUtilDAO daoUtil;
	
	@Override
	public List<Trabajador> obtenerCorreosEvaluado(String calendario) throws SQLException {
		return dao.obtenerCorreosEvaluado(calendario);
	}
	
	
	@Override
	public void enviarCorreoRecordatorioEvaluadoConocimiento(String codCalendario) throws SQLException {
		
		List<Trabajador> evaluados = this.obtenerCorreosEvaluado(codCalendario);
		Calendario calendario = daoUtil.calendario(codCalendario);
		
		for (Trabajador evaluado : evaluados) {
			if(evaluado.getCorreo()!= null && !evaluado.getCorreo().trim().isEmpty() ) {
				notificacionService.notificacionConocimiento(
						evaluado.getApellidosNombres(), "fsvargas@indracompany.com", calendario.getVNombre());
			}
		}
		
	}
	
	
	
}
