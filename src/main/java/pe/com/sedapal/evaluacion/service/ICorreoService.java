package pe.com.sedapal.evaluacion.service;

import pe.com.sedapal.evaluacion.model.Correo;
import pe.com.sedapal.evaluacion.model.CorreoResponse;
import pe.com.sedapal.evaluacion.service.impl.CorreoServiceImpl;

public interface ICorreoService {
	CorreoServiceImpl devuelveInstancia();

    CorreoResponse enviarCorreoHTML(final Correo correoParametro) throws Exception;

    CorreoResponse enviarCorreoHTMLFlat(final Correo correoObj) throws Exception;
}
