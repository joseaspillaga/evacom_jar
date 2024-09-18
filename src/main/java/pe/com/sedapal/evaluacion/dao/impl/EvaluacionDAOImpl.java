package pe.com.sedapal.evaluacion.dao.impl;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleTypes;
import pe.com.sedapal.evaluacion.dao.IEvaluacionDAO;
import pe.com.sedapal.evaluacion.model.Trabajador;
import pe.com.sedapal.evaluacion.util.DBConstants;

@Repository
public class EvaluacionDAOImpl implements IEvaluacionDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall jdbcCall;
	
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Trabajador> obtenerCorreosAutoevaluacionPendiente(String codCalendario) throws SQLException {
		this.jdbcCall = new SimpleJdbcCall(this.jdbcTemplate)
				.withSchemaName(DBConstants.SCHEMA_NAME)
				.withCatalogName(DBConstants.PACKAGE_SISTEMA).withProcedureName(DBConstants.PROCEDURE_CORREO_EVALUADO_AUTOEVALUACION)
				.withoutProcedureColumnMetaDataAccess().declareParameters(						
						new SqlParameter("p_codigo_calendario", Types.VARCHAR),
						new SqlOutParameter("CUR_DATOS", OracleTypes.CURSOR, new ColumnMapRowMapper()),
						new SqlOutParameter("o_mensaje", Types.VARCHAR),
						new SqlOutParameter("o_retorno", Types.NUMERIC));
		
		Map<String, Object> inParams = new HashMap<>();
		inParams.put("p_codigo_calendario", codCalendario);
		
		jdbcCall.compile();
		Map<String, Object> result = jdbcCall.execute(inParams);
		
		List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("CUR_DATOS");
		List<Trabajador> lista = new ArrayList<>();
		
		for (Map<String, Object> map : rows) {
			Trabajador trabajador =  new Trabajador();
			
			trabajador.setCodigoFicha(map.get("VFICHA").toString());
			trabajador.setApellidosNombres(map.get("VNOMBRECOMPLETO").toString());
			trabajador.setCorreo(map.get("VCORREO").toString());
			
			lista.add(trabajador);
		}
		return lista;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Trabajador> obtenerCorreosEvaluacionPendiente(String codCalendario) throws SQLException {
		this.jdbcCall = new SimpleJdbcCall(this.jdbcTemplate)
				.withSchemaName(DBConstants.SCHEMA_NAME)
				.withCatalogName(DBConstants.PACKAGE_SISTEMA).withProcedureName(DBConstants.PROCEDURE_CORREO_EVALUADOR_EVALUACION)
				.withoutProcedureColumnMetaDataAccess().declareParameters(						
						new SqlParameter("p_codigo_calendario", Types.VARCHAR),
						new SqlOutParameter("CUR_DATOS", OracleTypes.CURSOR, new ColumnMapRowMapper()),
						new SqlOutParameter("o_mensaje", Types.VARCHAR),
						new SqlOutParameter("o_retorno", Types.NUMERIC));
		
		Map<String, Object> inParams = new HashMap<>();
		inParams.put("p_codigo_calendario", codCalendario);
		
		jdbcCall.compile();
		Map<String, Object> result = jdbcCall.execute(inParams);
		
		List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("CUR_DATOS");
		List<Trabajador> lista = new ArrayList<>();
		
		for (Map<String, Object> map : rows) {
			Trabajador trabajador =  new Trabajador();
			
			trabajador.setCodigoFicha(map.get("VFICHA").toString());
			trabajador.setApellidosNombres(map.get("VNOMBRECOMPLETO").toString());
			trabajador.setCorreo(map.get("VCORREO").toString());
			
			lista.add(trabajador);
		}
		return lista;
	}
	
	
	
}
