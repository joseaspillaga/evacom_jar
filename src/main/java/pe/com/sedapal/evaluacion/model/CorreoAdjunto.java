package pe.com.sedapal.evaluacion.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class CorreoAdjunto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8366499598484663803L;
	private String pathAdjunto;
	private String nombreArchivo;
	private byte[] archivoAdjunto;
}
