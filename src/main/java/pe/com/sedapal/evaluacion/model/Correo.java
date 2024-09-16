package pe.com.sedapal.evaluacion.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Correo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7817922742779207439L;
	
	private CorreoCabecera correoCabecera;
	private List<CorreoAdjunto> archivosAdjuntos;
	private List<CorreoImagen> imagenesAdjuntas;
	private CorreoVariable variable;
	private String mensaje;
	private String asunto;

}
