package pe.com.sedapal.evaluacion.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Trabajador implements Serializable{

	private static final long serialVersionUID = 6355409188998495503L;
	
	private String codigoFicha;
	private String apellidosNombres;
	private String unidadOrganica;
	private String codigoPuesto;
	private String nombrePuesto;
	private String foto;
	private Long tipoEvaluador;
	private String correo;
	private String equipo_codigo;
	private String equipo_nombre;
	private String gerencia_codigo;
	private String gerencia_nombre;
	private String grupo_eval_codigo;
	private String grupo_eval_nombre;
	private String grupo_ocupacional;
	
}
