package pe.com.sedapal.evaluacion.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class CorreoResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7677707314844947095L;
	private int status;	
	private String mensaje;	
}
