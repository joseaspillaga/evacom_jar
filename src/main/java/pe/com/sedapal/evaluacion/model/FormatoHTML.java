package pe.com.sedapal.evaluacion.model;

import java.io.Serializable;
import java.sql.Blob;

import lombok.Data;

@Data
public class FormatoHTML implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6472978672819923487L;
	private Long id;	
	private String descripcion;
	private String titulo;
	private Blob formatoHtml;
	private byte[] archivo;
}
