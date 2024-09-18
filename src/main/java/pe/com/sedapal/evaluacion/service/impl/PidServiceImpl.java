package pe.com.sedapal.evaluacion.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.evaluacion.dao.IPidDAO;
import pe.com.sedapal.evaluacion.dao.IUtilDAO;
import pe.com.sedapal.evaluacion.model.Calendario;
import pe.com.sedapal.evaluacion.model.Pid;
import pe.com.sedapal.evaluacion.service.INotificaciones;
import pe.com.sedapal.evaluacion.service.IPidService;

@Service
public class PidServiceImpl implements IPidService{

	@Autowired
	INotificaciones notificacionService;
		
	@Autowired
	IPidDAO dao;
	
	@Autowired
	IUtilDAO daoUtil;
	
	@Override
	public List<Pid> obtenerCorreosEvaluadosRegistroPendiente(String calendario) throws SQLException {
		return dao.obtenerCorreosEvaluadosRegistroPendiente(calendario);
	}
	
	@Override
	public List<Pid> obtenerCorreosEvaluadosCumplimientoPendiente(String calendario) throws SQLException {
		return dao.obtenerCorreosEvaluadosCumplimientoPendiente(calendario);
	}
	
	@Override
	public List<Pid> obtenerCorreosEvaluadoresRegistroPendiente(String calendario) throws SQLException {
		return dao.obtenerCorreosEvaluadoresRegistroPendiente(calendario);
	}
	
	@Override
	public List<Pid> obtenerCorreosEvaluadoresCumplimientoPendiente(String calendario) throws SQLException {
		return dao.obtenerCorreosEvaluadoresCumplimientoPendiente(calendario);
	}
	
	@Override
	public void enviarCorreoRecordatorioEvaluadoRegistroPendiente(String codCalendario) throws SQLException {
		
		List<Pid> evaluados = this.obtenerCorreosEvaluadosRegistroPendiente(codCalendario);
		Calendario calendario = daoUtil.calendario(codCalendario);
		
		for (Pid evaluado : evaluados) {
			
			String accion = evaluado.isVeredictoRegistro() ? "aprobado" : "rechazado";
			String tipoRegistro = "registro";

			if(evaluado.getCorreo()!= null && !evaluado.getCorreo().trim().isEmpty() ) {
				notificacionService.notificacionPid(evaluado.getApellidosNombres(), 
						"fsvargas@indracompany.com", calendario.getVNombre(),accion, tipoRegistro );
			}
		}
		
	}
	
	@Override
	public void enviarCorreoRecordatorioEvaluadoCumplimientoPendiente(String codCalendario) throws SQLException {
		
		List<Pid> evaluados = this.obtenerCorreosEvaluadosRegistroPendiente(codCalendario);
		Calendario calendario = daoUtil.calendario(codCalendario);
		
		for (Pid evaluado : evaluados) {
			
			String accion = evaluado.isVeredictoCumplimiento()? "aprobado" : "rechazado";
			String tipoRegistro = "registro cumplimiento";

			if(evaluado.getCorreo()!= null && !evaluado.getCorreo().trim().isEmpty() ) {
				notificacionService.notificacionPid(evaluado.getApellidosNombres(), 
						"fsvargas@indracompany.com", calendario.getVNombre(),accion, tipoRegistro );
			}
		}
		
	}

	@Override
	public void enviarCorreoRecordatorioEvaluadorRegistroPendiente(String codCalendario) throws SQLException {
		
		List<Pid> evaluadores = this.obtenerCorreosEvaluadosRegistroPendiente(codCalendario);
		Calendario calendario = daoUtil.calendario(codCalendario);
		
		for (Pid evaluador : evaluadores) {
			
			String accion = "realizado";
			String tipoRegistro = "registro";

			if(evaluador.getCorreo()!= null && !evaluador.getCorreo().trim().isEmpty() ) {
				notificacionService.notificacionPid(evaluador.getApellidosNombres(), 
						"fsvargas@indracompany.com", calendario.getVNombre(),accion, tipoRegistro );
			}
		}
		
	}
	
	@Override
	public void enviarCorreoRecordatorioEvaluadorCumplimientoPendiente(String codCalendario) throws SQLException {
		
		List<Pid> evaluadores = this.obtenerCorreosEvaluadosRegistroPendiente(codCalendario);
		Calendario calendario = daoUtil.calendario(codCalendario);
		
		for (Pid evaluador : evaluadores) {
			
			String accion = "realizado";
			String tipoRegistro = "registro cumplimiento";

			if(evaluador.getCorreo()!= null && !evaluador.getCorreo().trim().isEmpty() ) {
				notificacionService.notificacionPid(evaluador.getApellidosNombres(), 
						"fsvargas@indracompany.com", calendario.getVNombre(),accion, tipoRegistro );
			}
		}
		
	}
	
	
}
