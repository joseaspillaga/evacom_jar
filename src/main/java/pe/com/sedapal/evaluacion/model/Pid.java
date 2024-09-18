package pe.com.sedapal.evaluacion.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Pid extends Trabajador implements Serializable{

	private static final long serialVersionUID = 995702431701558850L;
	
	private boolean veredictoRegistro;
	private boolean veredictoCumplimiento;
}
