package pe.com.sedapal.evaluacion.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class CorreoImagen implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6322007173261678329L;
	private String nombreVariable;
	private String urlImagen;
	private byte[] archivoAdjunto;
}
