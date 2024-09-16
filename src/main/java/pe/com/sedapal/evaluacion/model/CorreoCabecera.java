package pe.com.sedapal.evaluacion.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class CorreoCabecera implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<String> correoDestino;
	private List<String> correoCopia;
	private List<String> correoCopiaOculta;
	private String correoRemitente;
	private String nombreRemiente;
	private String claveRemitente;
	
}
