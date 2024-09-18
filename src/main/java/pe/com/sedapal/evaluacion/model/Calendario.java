package pe.com.sedapal.evaluacion.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Calendario implements Serializable {

	private static final long serialVersionUID = 7150131401000222397L;
	private String vCodigo;
	private String vNombre;
	private Date dPeriodoIni;
	private Date dPeriodoFin;
	private Date dParamIni;
	private Date dParamFin;
	private String  tipo;

}
