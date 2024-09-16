package pe.com.sedapal.evaluacion.service.impl;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
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
	     
	     
		     
	     System.out.println("paso 1");
	     
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

}
