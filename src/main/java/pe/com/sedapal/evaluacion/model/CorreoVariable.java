package pe.com.sedapal.evaluacion.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class CorreoVariable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8644031021979495480L;
	private List<String> nombreVariable;	
	private List<String> valorVariable;
	
}
