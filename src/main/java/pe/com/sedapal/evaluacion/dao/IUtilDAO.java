package pe.com.sedapal.evaluacion.dao;

import java.sql.SQLException;
import java.util.List;

import pe.com.sedapal.evaluacion.model.Constantes;
import pe.com.sedapal.evaluacion.model.FormatoHTML;

public interface IUtilDAO {
	List<Constantes> obtenerConstantes(String grupo) throws SQLException;
	FormatoHTML obtenerFormatoBlob(String nombre) throws SQLException;
}
