package pe.com.sedapal.evaluacion.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Constantes  implements Serializable{
	
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 4843538848646805424L;
	private Long codigo;
	private String grupo;
	private String valor;
	private String descripcion;
}
