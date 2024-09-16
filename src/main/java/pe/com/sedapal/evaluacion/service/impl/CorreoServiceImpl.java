package pe.com.sedapal.evaluacion.service.impl;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import pe.com.sedapal.evaluacion.model.Correo;
import pe.com.sedapal.evaluacion.model.CorreoResponse;
import pe.com.sedapal.evaluacion.model.UCorreo;
import pe.com.sedapal.evaluacion.service.ICorreoService;

@Service
public class CorreoServiceImpl implements ICorreoService{
	
	@Autowired
    Environment env;
	
	 private static CorreoServiceImpl correoFacade = null;
	    UCorreo utilitarioCorreo = new UCorreo();
	    String correo; //= recursos.getString("Correo");
	    String puerto; //= recursos.getString("puerto");
	    String autorizacion; //=recursos.getString("smtpAuth");
	    String timeout;

	    private CorreoServiceImpl() {
	    }

	    @Override
	    public CorreoServiceImpl devuelveInstancia() {
	        if (correoFacade == null) {
	            correoFacade = new CorreoServiceImpl();
	        }
	        return correoFacade;
	    }

	    @Override
	    public CorreoResponse enviarCorreoHTML(Correo correoParametro) throws Exception {
	        CorreoResponse correoResponse = null;
	        String cuerpo = correoParametro.getMensaje();
	        String asunto = correoParametro.getAsunto();
	        CorreoNotificacionServiceImpl notificacion = CorreoNotificacionServiceImpl.devuelveInstancia();
	        try {
	            correoResponse = new CorreoResponse();
	            if (correoParametro.getVariable().getNombreVariable() != null || correoParametro.getVariable().getValorVariable() != null) {
	                for (int i = 0; i < correoParametro.getVariable().getNombreVariable().size(); i++) {
	                    cuerpo = cuerpo.replaceAll(correoParametro.getVariable().getNombreVariable().get(i),
	                            correoParametro.getVariable().getValorVariable().get(i));
	                    asunto = asunto.replaceAll(correoParametro.getVariable().getNombreVariable().get(i),
	                            correoParametro.getVariable().getValorVariable().get(i));
	                }
	            }
	            correoParametro.setMensaje(cuerpo);
	            correoParametro.setAsunto(asunto);
	            correoResponse = notificacion.enviarCorreo(getPropiedades(), correoParametro);
	        } catch (Exception e) {
	          System.out.println(e.getMessage());
	        }
	        return correoResponse;
	    }

	    private Properties getPropiedades() {
	        correo = env.getProperty("app.config.servidor.correo.host");
	        puerto = env.getProperty("app.config.servidor.correo.puerto");
	        autorizacion = env.getProperty("app.config.servidor.correo.smtpAuth");
	        timeout = env.getProperty("app.config.servidor.correo.timeout");

	        Properties propiedad = new Properties();


	        if (correo.contains("gmail")) {
	            /*properties for gmail smtp*/
	            propiedad.put("mail.smtp.auth", autorizacion);
	            propiedad.put("mail.smtp.ssl.enable", "true");
	            propiedad.put("mail.smtp.host", correo);
	            propiedad.put("mail.smtp.port", puerto);
	            propiedad.put("mail.smtp.timeout", timeout);
	            /**/
	        } else {
	            /*Properties for sedapal smtp*/
	            propiedad.put("mail.smtp.auth", autorizacion);
	            propiedad.put("mail.smtp.starttls.enable", "true");
	            propiedad.put("mail.smtp.host", correo);
	            propiedad.put("mail.smtp.port", puerto);
	            /**/
	        }

	        return propiedad;
	    }

	    @Override
	    public CorreoResponse enviarCorreoHTMLFlat(Correo correoParametro) throws Exception {
	        CorreoResponse correoResponse = null;
	        String cuerpo = correoParametro.getMensaje();
	        String asunto = correoParametro.getAsunto();
	        CorreoNotificacionServiceImpl notificacion = CorreoNotificacionServiceImpl.devuelveInstancia();
	        try {
	            correoResponse = new CorreoResponse();
	            correoParametro.setMensaje(cuerpo);
	            correoParametro.setAsunto(asunto);
	            correoResponse = notificacion.enviarCorreo(getPropiedades(), correoParametro);
	        } catch (Exception exception) {
	            throw exception;
	        }
	        return correoResponse;
	    }
}
