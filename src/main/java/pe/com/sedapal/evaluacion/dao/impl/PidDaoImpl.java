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
import pe.com.sedapal.evaluacion.dao.IPidDAO;
import pe.com.sedapal.evaluacion.model.Pid;
import pe.com.sedapal.evaluacion.util.DBConstants;

@Repository
public class PidDaoImpl implements IPidDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall jdbcCall;
	
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Pid> obtenerCorreosEvaluadosRegistroPendiente(String codCalendario) throws SQLException {
		this.jdbcCall = new SimpleJdbcCall(this.jdbcTemplate)
				.withSchemaName(DBConstants.SCHEMA_NAME)
				.withCatalogName(DBConstants.PACKAGE_SISTEMA).withProcedureName(DBConstants.PROCEDURE_CORREO_EVALUADO_PID_REGISTRO)
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
		List<Pid> lista = new ArrayList<>();
		
		for (Map<String, Object> map : rows) {
			Pid pid = new Pid();
			
			pid.setCodigoFicha(map.get("VFICHA").toString());
			pid.setApellidosNombres(map.get("VNOMBRECOMPLETO").toString());
			pid.setCorreo(map.get("VCORREO").toString());
			
			int nEstadoRegistro = ((Number) map.get("NESTADO_REGISTRO")).intValue();
			int nEstadoCumplimiento = ((Number) map.get("NESTADO_CUMPLIMIENTO")).intValue();
			
			pid.setVeredictoRegistro(nEstadoRegistro > 0);
			pid.setVeredictoCumplimiento(nEstadoCumplimiento > 0);
			
			lista.add(pid);
		}
		return lista;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Pid> obtenerCorreosEvaluadosCumplimientoPendiente(String codCalendario) throws SQLException {
		this.jdbcCall = new SimpleJdbcCall(this.jdbcTemplate)
				.withSchemaName(DBConstants.SCHEMA_NAME)
				.withCatalogName(DBConstants.PACKAGE_SISTEMA).withProcedureName(DBConstants.PROCEDURE_CORREO_EVALUADO_PID_CUMPLIMIENTO)
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
		List<Pid> lista = new ArrayList<>();
		
		for (Map<String, Object> map : rows) {
			Pid pid = new Pid();
			
			pid.setCodigoFicha(map.get("VFICHA").toString());
			pid.setApellidosNombres(map.get("VNOMBRECOMPLETO").toString());
			pid.setCorreo(map.get("VCORREO").toString());
			
			int nEstadoRegistro = ((Number) map.get("NESTADO_REGISTRO")).intValue();
			int nEstadoCumplimiento = ((Number) map.get("NESTADO_CUMPLIMIENTO")).intValue();
			
			pid.setVeredictoRegistro(nEstadoRegistro > 0);
			pid.setVeredictoCumplimiento(nEstadoCumplimiento > 0);
			
			lista.add(pid);
		}
		return lista;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Pid> obtenerCorreosEvaluadoresRegistroPendiente(String codCalendario) throws SQLException {
		this.jdbcCall = new SimpleJdbcCall(this.jdbcTemplate)
				.withSchemaName(DBConstants.SCHEMA_NAME)
				.withCatalogName(DBConstants.PACKAGE_SISTEMA).withProcedureName(DBConstants.PROCEDURE_CORREO_EVALUADOR_PID_REGISTRO)
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
		List<Pid> lista = new ArrayList<>();
		
		for (Map<String, Object> map : rows) {
			Pid pid = new Pid();
			
			pid.setCodigoFicha(map.get("VFICHA").toString());
			pid.setApellidosNombres(map.get("VNOMBRECOMPLETO").toString());
			pid.setCorreo(map.get("VCORREO").toString());
			
			int nEstadoRegistro = ((Number) map.get("NESTADO_REGISTRO")).intValue();
			int nEstadoCumplimiento = ((Number) map.get("NESTADO_CUMPLIMIENTO")).intValue();
			
			pid.setVeredictoRegistro(nEstadoRegistro > 0);
			pid.setVeredictoCumplimiento(nEstadoCumplimiento > 0);
			
			lista.add(pid);
		}
		return lista;
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Pid> obtenerCorreosEvaluadoresCumplimientoPendiente(String codCalendario) throws SQLException {
		this.jdbcCall = new SimpleJdbcCall(this.jdbcTemplate)
				.withSchemaName(DBConstants.SCHEMA_NAME)
				.withCatalogName(DBConstants.PACKAGE_SISTEMA).withProcedureName(DBConstants.PROCEDURE_CORREO_EVALUADOR_PID_CUMPLIMIENTO)
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
		List<Pid> lista = new ArrayList<>();
		
		for (Map<String, Object> map : rows) {
			Pid pid = new Pid();
			
			pid.setCodigoFicha(map.get("VFICHA").toString());
			pid.setApellidosNombres(map.get("VNOMBRECOMPLETO").toString());
			pid.setCorreo(map.get("VCORREO").toString());
			
			int nEstadoRegistro = ((Number) map.get("NESTADO_REGISTRO")).intValue();
			int nEstadoCumplimiento = ((Number) map.get("NESTADO_CUMPLIMIENTO")).intValue();
			
			pid.setVeredictoRegistro(nEstadoRegistro > 0);
			pid.setVeredictoCumplimiento(nEstadoCumplimiento > 0);
			
			lista.add(pid);
		}
		return lista;
	}
	
}
