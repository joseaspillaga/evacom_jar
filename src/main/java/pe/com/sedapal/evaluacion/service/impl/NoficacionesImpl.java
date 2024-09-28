package pe.com.sedapal.evaluacion.service.impl;

import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.com.sedapal.evaluacion.dao.IUtilDAO;
import pe.com.sedapal.evaluacion.dao.impl.UtilDaoImpl;
import pe.com.sedapal.evaluacion.model.Constantes;
import pe.com.sedapal.evaluacion.model.Correo;
import pe.com.sedapal.evaluacion.model.CorreoCabecera;
import pe.com.sedapal.evaluacion.model.CorreoResponse;
import pe.com.sedapal.evaluacion.model.FormatoHTML;
import pe.com.sedapal.evaluacion.service.ICorreoService;
import pe.com.sedapal.evaluacion.service.INotificaciones;
import pe.com.sedapal.evaluacion.util.DBConstants;
import pe.com.sedapal.evaluacion.util.Util;


@Service

public class NoficacionesImpl implements INotificaciones{

	@Autowired
	private  IUtilDAO utilDao;
	
 

	@Autowired
	private  ICorreoService correoService;
	
	 
	@Override
	public Boolean notificacionConocimiento(String nombre, String correo, String calendario) throws SQLException {
		String cuerpoCorreo = "";
		 Correo correoObj = new Correo();
	     CorreoCabecera correoCabecera = new CorreoCabecera();
	     CorreoResponse respuesta = new CorreoResponse();
	     List<Constantes> constantes = new ArrayList<>();
	    // utilDao = new UtilDaoImpl();
	     constantes = utilDao.obtenerConstantes(DBConstants.CONSTANTES_CORREO);
	     
	     correoCabecera.setNombreRemiente(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_REMITENTE_NOMBRE).getValor() );	     
	     correoCabecera.setCorreoRemitente(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_REMITENTE_CUENTA).getValor());
	     correoCabecera.setClaveRemitente(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_REMITENTE_PASSWORD).getValor());
	     

	     FormatoHTML formato =  utilDao.obtenerFormatoBlob(DBConstants.CONSTANTES_CORREO_FORMATO_CONOCIMIENTO);
	     String plantillaString = "";
	     if (formato != null) {
	            Blob plantillaBlob = formato.getFormatoHtml();
	            plantillaString = new String(plantillaBlob.getBytes(1l, (int) plantillaBlob.length()));	         
	      }
	     cuerpoCorreo = this.updateBodyConocimiento(plantillaString, nombre, calendario, Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_URL).getValor());
	     correoObj.setMensaje(cuerpoCorreo);
	     correoObj.setAsunto(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_ASUNTO_CONOCIMIENTO).getValor());
	     
	     List<String> destinatarios = new ArrayList<String>();
	     destinatarios.add(correo);
	     correoCabecera.setCorreoDestino(destinatarios);
	     correoObj.setCorreoCabecera(correoCabecera);
	     try {
			respuesta = correoService.enviarCorreoHTMLFlat(correoObj);
			System.out.println("enviado");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	        if (respuesta == null) {
	            throw new RuntimeException("Ocurrio un error en el envio del correo.");
	        }
		return true;
	}
	
	private String updateBodyConocimiento(String cuerpoCorreo, String nombre, String periodo, String enlace) throws SQLException {
        cuerpoCorreo = cuerpoCorreo.replaceAll("%NOM_EVALUADO%", nombre);
        cuerpoCorreo = cuerpoCorreo.replaceAll("%NOM_PERIODO%", periodo);
        cuerpoCorreo = cuerpoCorreo.replaceAll("%SIS_LINK%", enlace);
        return cuerpoCorreo;
    }

	
	
	@Override
	public Boolean notificacionAutoevaluacion(String nombre, String correo, String calendario) throws SQLException {
		String cuerpoCorreo = "";
		 Correo correoObj = new Correo();
	     CorreoCabecera correoCabecera = new CorreoCabecera();
	     CorreoResponse respuesta = new CorreoResponse();
	     List<Constantes> constantes = new ArrayList<>();
	     constantes = utilDao.obtenerConstantes(DBConstants.CONSTANTES_CORREO);
	     
	     correoCabecera.setNombreRemiente(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_REMITENTE_NOMBRE).getValor() );	     
	     correoCabecera.setCorreoRemitente(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_REMITENTE_CUENTA).getValor());
	     correoCabecera.setClaveRemitente(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_REMITENTE_PASSWORD).getValor());
	     
	     
		     
	     
	     
	     FormatoHTML formato =  utilDao.obtenerFormatoBlob(DBConstants.CONSTANTES_CORREO_FORMATO_AUTOEVALUACION);
	     String plantillaString = "";
	     if (formato != null) {
	            Blob plantillaBlob = formato.getFormatoHtml();
	            plantillaString = new String(plantillaBlob.getBytes(1l, (int) plantillaBlob.length()));	         
	      }
	     cuerpoCorreo = this.updateBodyConocimiento(plantillaString, nombre, calendario, Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_URL).getValor());
	     correoObj.setMensaje(cuerpoCorreo);
	     correoObj.setAsunto(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_ASUNTO_AUTOEVALUACION).getValor());
	     
	     List<String> destinatarios = new ArrayList<String>();
	     destinatarios.add(correo);
	     correoCabecera.setCorreoDestino(destinatarios);
	     correoObj.setCorreoCabecera(correoCabecera);
	     try {
			respuesta = correoService.enviarCorreoHTMLFlat(correoObj);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	        if (respuesta == null) {
	            throw new RuntimeException("Ocurrio un error en el envio del correo.");
	        }
		return true;
	}
	
	@Override
	public Boolean notificacionHechoResaltante(String nombre, String correo, String calendario) throws SQLException {
		String cuerpoCorreo = "";
		 Correo correoObj = new Correo();
	     CorreoCabecera correoCabecera = new CorreoCabecera();
	     CorreoResponse respuesta = new CorreoResponse();
	     List<Constantes> constantes = new ArrayList<>();
	     constantes = utilDao.obtenerConstantes(DBConstants.CONSTANTES_CORREO);
	     
	     correoCabecera.setNombreRemiente(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_REMITENTE_NOMBRE).getValor() );	     
	     correoCabecera.setCorreoRemitente(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_REMITENTE_CUENTA).getValor());
	     correoCabecera.setClaveRemitente(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_REMITENTE_PASSWORD).getValor());
	     
	     
		     
	     
	     
	     FormatoHTML formato =  utilDao.obtenerFormatoBlob(DBConstants.CONSTANTES_CORREO_FORMATO_HECHORESALTANTE);
	     String plantillaString = "";
	     if (formato != null) {
	            Blob plantillaBlob = formato.getFormatoHtml();
	            plantillaString = new String(plantillaBlob.getBytes(1l, (int) plantillaBlob.length()));	         
	      }
	     cuerpoCorreo = this.updateBodyConocimiento(plantillaString, nombre, calendario, Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_URL).getValor());
	     correoObj.setMensaje(cuerpoCorreo);
	     correoObj.setAsunto(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_ASUNTO_HECHOSRESALTANTES).getValor());
	     
	     List<String> destinatarios = new ArrayList<String>();
	     destinatarios.add(correo);
	     correoCabecera.setCorreoDestino(destinatarios);
	     correoObj.setCorreoCabecera(correoCabecera);
	     try {
			respuesta = correoService.enviarCorreoHTMLFlat(correoObj);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	        if (respuesta == null) {
	            throw new RuntimeException("Ocurrio un error en el envio del correo.");
	        }
		return true;
	}
	
	@Override
	public Boolean notificacionEvaluacion(String nombre, String correo, String calendario) throws SQLException {
		String cuerpoCorreo = "";
		 Correo correoObj = new Correo();
	     CorreoCabecera correoCabecera = new CorreoCabecera();
	     CorreoResponse respuesta = new CorreoResponse();
	     List<Constantes> constantes = new ArrayList<>();
	     constantes = utilDao.obtenerConstantes(DBConstants.CONSTANTES_CORREO);
	     
	     correoCabecera.setNombreRemiente(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_REMITENTE_NOMBRE).getValor() );	     
	     correoCabecera.setCorreoRemitente(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_REMITENTE_CUENTA).getValor());
	     correoCabecera.setClaveRemitente(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_REMITENTE_PASSWORD).getValor());
	     
	     
		     
	     
	     
	     FormatoHTML formato =  utilDao.obtenerFormatoBlob(DBConstants.CONSTANTES_CORREO_FORMATO_EVALUACION);
	     String plantillaString = "";
	     if (formato != null) {
	            Blob plantillaBlob = formato.getFormatoHtml();
	            plantillaString = new String(plantillaBlob.getBytes(1l, (int) plantillaBlob.length()));	         
	      }
	     cuerpoCorreo = this.updateBodyConocimiento(plantillaString, nombre, calendario, Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_URL).getValor());
	     correoObj.setMensaje(cuerpoCorreo);
	     correoObj.setAsunto(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_ASUNTO_EVALUACION).getValor());
	     
	     List<String> destinatarios = new ArrayList<String>();
	     destinatarios.add(correo);
	     correoCabecera.setCorreoDestino(destinatarios);
	     correoObj.setCorreoCabecera(correoCabecera);
	     try {
			respuesta = correoService.enviarCorreoHTMLFlat(correoObj);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	        if (respuesta == null) {
	            throw new RuntimeException("Ocurrio un error en el envio del correo.");
	        }
		return true;
	}
	
	@Override
	public Boolean notificacionCalibracion(String nombre, String correo, String calendario, Date fecha) throws SQLException {
		String cuerpoCorreo = "";
		 Correo correoObj = new Correo();
	     CorreoCabecera correoCabecera = new CorreoCabecera();
	     CorreoResponse respuesta = new CorreoResponse();
	     List<Constantes> constantes = new ArrayList<>();
	     constantes = utilDao.obtenerConstantes(DBConstants.CONSTANTES_CORREO);
	     
	     correoCabecera.setNombreRemiente(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_REMITENTE_NOMBRE).getValor() );	     
	     correoCabecera.setCorreoRemitente(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_REMITENTE_CUENTA).getValor());
	     correoCabecera.setClaveRemitente(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_REMITENTE_PASSWORD).getValor());
	     
	     
		     
	     
	     
	     FormatoHTML formato =  utilDao.obtenerFormatoBlob(DBConstants.CONSTANTES_CORREO_FORMATO_CALIBRACION);
	     String plantillaString = "";
	     if (formato != null) {
	            Blob plantillaBlob = formato.getFormatoHtml();
	            plantillaString = new String(plantillaBlob.getBytes(1l, (int) plantillaBlob.length()));	         
	      }
	     cuerpoCorreo = this.updateBodyCalibracion(plantillaString, nombre, calendario, Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_URL).getValor(), fecha);
	     correoObj.setMensaje(cuerpoCorreo);
	     correoObj.setAsunto(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_ASUNTO_CALIBRACION).getValor());
	     
	     List<String> destinatarios = new ArrayList<String>();
	     destinatarios.add(correo);
	     correoCabecera.setCorreoDestino(destinatarios);
	     correoObj.setCorreoCabecera(correoCabecera);
	     try {
			respuesta = correoService.enviarCorreoHTMLFlat(correoObj);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	        if (respuesta == null) {
	            throw new RuntimeException("Ocurrio un error en el envio del correo.");
	        }
		return true;
	}
	
	
	private String updateBodyCalibracion(String cuerpoCorreo, String nombre, String periodo, String enlace, Date fechaPlazo) throws SQLException {
		 
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		// Convertir Date a String
		String dateString = formatter.format(fechaPlazo);
		
		cuerpoCorreo = cuerpoCorreo.replaceAll("%NOM_EVALUADO%", nombre);
		cuerpoCorreo = cuerpoCorreo.replaceAll("%NOM_PERIODO%", periodo);
		cuerpoCorreo = cuerpoCorreo.replaceAll("%SIS_LINK%", enlace);
		cuerpoCorreo = cuerpoCorreo.replaceAll("%PLAZO%", dateString);
		return cuerpoCorreo;
		}
	
	
	
	@Override
	public Boolean notificacionRetroalimentacion(String nombre, String correo, String tipoPersona, String calendario) throws SQLException {
		
		String cuerpoCorreo = "";
		 Correo correoObj = new Correo();
	     CorreoCabecera correoCabecera = new CorreoCabecera();
	     CorreoResponse respuesta = new CorreoResponse();
	     List<Constantes> constantes = new ArrayList<>();
	     constantes = utilDao.obtenerConstantes(DBConstants.CONSTANTES_CORREO);
	     
	     correoCabecera.setNombreRemiente(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_REMITENTE_NOMBRE).getValor() );	     
	     correoCabecera.setCorreoRemitente(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_REMITENTE_CUENTA).getValor());
	     correoCabecera.setClaveRemitente(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_REMITENTE_PASSWORD).getValor());
	     
	     FormatoHTML formato =  utilDao.obtenerFormatoBlob(DBConstants.CONSTANTES_CORREO_FORMATO_RETROALIMENTACION);
	     String plantillaString = "";
	     if (formato != null) {
	            Blob plantillaBlob = formato.getFormatoHtml();
	            plantillaString = new String(plantillaBlob.getBytes(1l, (int) plantillaBlob.length()));	         
	      }
	     cuerpoCorreo = this.updateBodyRetroalimentacion(plantillaString, nombre, calendario, Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_URL).getValor(), tipoPersona);
	     correoObj.setMensaje(cuerpoCorreo);
	     correoObj.setAsunto(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_ASUNTO_RETROALIMENTACION).getValor());
	     
	     List<String> destinatarios = new ArrayList<String>();
	     destinatarios.add(correo);
	     correoCabecera.setCorreoDestino(destinatarios);
	     correoObj.setCorreoCabecera(correoCabecera);
	     try {
			respuesta = correoService.enviarCorreoHTMLFlat(correoObj);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	        if (respuesta == null) {
	            throw new RuntimeException("Ocurrio un error en el envio del correo.");
	        }
		return true;
		
		
	}
	
	private String updateBodyRetroalimentacion(String cuerpoCorreo, String nombre, String periodo, String enlace, String tipoPersona) throws SQLException {
		 
        cuerpoCorreo = cuerpoCorreo.replaceAll("%NOM_PERSONA%", nombre);
        cuerpoCorreo = cuerpoCorreo.replaceAll("%TIPO_PERSONA%", tipoPersona);
        cuerpoCorreo = cuerpoCorreo.replaceAll("%NOM_PERIODO%", periodo);
        cuerpoCorreo = cuerpoCorreo.replaceAll("%SIS_LINK%", enlace);
        return cuerpoCorreo;
    }
	
	
	@Override
	public Boolean notificacionPid(String nombre, String correo, String calendario, String accion, String tipoRegistro,String observacion) throws SQLException {
		String cuerpoCorreo = "";
		 Correo correoObj = new Correo();
	     CorreoCabecera correoCabecera = new CorreoCabecera();
	     CorreoResponse respuesta = new CorreoResponse();
	     List<Constantes> constantes = new ArrayList<>();
	     constantes = utilDao.obtenerConstantes(DBConstants.CONSTANTES_CORREO);
	     
	     correoCabecera.setNombreRemiente(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_REMITENTE_NOMBRE).getValor() );	     
	     correoCabecera.setCorreoRemitente(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_REMITENTE_CUENTA).getValor());
	     correoCabecera.setClaveRemitente(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_REMITENTE_PASSWORD).getValor());
	     
	     FormatoHTML formato =  utilDao.obtenerFormatoBlob(DBConstants.CONSTANTES_CORREO_FORMATO_PID);
	     String plantillaString = "";
	     if (formato != null) {
	            Blob plantillaBlob = formato.getFormatoHtml();
	            plantillaString = new String(plantillaBlob.getBytes(1l, (int) plantillaBlob.length()));	         
	      }
	     cuerpoCorreo = this.updateBodyPid(plantillaString, nombre, calendario, Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_URL).getValor(), accion, tipoRegistro,observacion);
	     correoObj.setMensaje(cuerpoCorreo);
	     correoObj.setAsunto(Util.getConstante(constantes, DBConstants.CONSTANTES_CORREO_ASUNTO_PID).getValor());
	     
	     List<String> destinatarios = new ArrayList<String>();
	     destinatarios.add(correo);
	     correoCabecera.setCorreoDestino(destinatarios);
	     correoObj.setCorreoCabecera(correoCabecera);
	     try {
			respuesta = correoService.enviarCorreoHTMLFlat(correoObj);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	        if (respuesta == null) {
	            throw new RuntimeException("Ocurrio un error en el envio del correo.");
	        }
		return true;
	}
	
	private String updateBodyPid(String cuerpoCorreo, String nombre, String periodo, String enlace, String accion, String tipoRegistro,String observacion) throws SQLException {
	 
        cuerpoCorreo = cuerpoCorreo.replaceAll("%NOM_PERSONA%", nombre);
        cuerpoCorreo = cuerpoCorreo.replaceAll("%ACCION%", accion);
        cuerpoCorreo = cuerpoCorreo.replaceAll("%TIPO_REGISTRO%", tipoRegistro);
        cuerpoCorreo = cuerpoCorreo.replaceAll("%NOM_PERIODO%", periodo);
        cuerpoCorreo = cuerpoCorreo.replaceAll("%OBSERVACION%", observacion);
        cuerpoCorreo = cuerpoCorreo.replaceAll("%SIS_LINK%", enlace);
        return cuerpoCorreo;
    }
	
	
	
	
	
}
