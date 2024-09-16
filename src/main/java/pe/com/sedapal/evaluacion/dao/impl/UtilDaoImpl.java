package pe.com.sedapal.evaluacion.dao.impl;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleTypes;
import pe.com.sedapal.evaluacion.dao.IUtilDAO;
import pe.com.sedapal.evaluacion.model.Constantes;
import pe.com.sedapal.evaluacion.model.FormatoHTML;
import pe.com.sedapal.evaluacion.util.DBConstants;

@Repository
public class UtilDaoImpl implements IUtilDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall jdbcCall;
	
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Constantes> obtenerConstantes(String grupo) throws SQLException {
		this.jdbcCall = new SimpleJdbcCall(this.jdbcTemplate)
				.withSchemaName(DBConstants.SCHEMA_NAME)
				.withCatalogName(DBConstants.PACKAGE_SISTEMA).withProcedureName(DBConstants.PROCEDURE_CONSTANTE_LISTADO)
				.withoutProcedureColumnMetaDataAccess().declareParameters(					
						new SqlParameter("p_grupo", Types.VARCHAR),				
						new SqlOutParameter("CUR_DATOS", OracleTypes.CURSOR, new ColumnMapRowMapper()),
						new SqlOutParameter("o_mensaje", Types.VARCHAR),
						new SqlOutParameter("o_retorno", Types.NUMERIC));
		
		Map<String, Object> inParams = new HashMap<>();
		inParams.put("p_grupo", grupo);
		jdbcCall.compile();
		Map<String, Object> result = jdbcCall.execute(inParams);
		List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("CUR_DATOS");
		List<Constantes> constantes = new ArrayList<>();
		
		for (Map<String, Object> map : rows) {
			Constantes constante = new Constantes();
			BigDecimal codigo = new BigDecimal(map.get("N_ID").toString());
			constante.setCodigo(codigo.longValue());
			constante.setGrupo(map.get("V_GRUPO").toString());
			constante.setValor(map.get("V_VALOR").toString());
			constante.setDescripcion(map.get("V_DESCRIPCION").toString());
			constantes.add(constante);
			
		}
		return constantes;		
	}
	
	 @Override
	    public FormatoHTML obtenerFormatoBlob(String nombre) throws SQLException {
	        Map<String, Object> out = null;
	        FormatoHTML objeto = null;
	  
	        
	        this.jdbcCall = new SimpleJdbcCall(this.jdbcTemplate)
					.withSchemaName(DBConstants.SCHEMA_NAME)
					.withCatalogName(DBConstants.PACKAGE_SISTEMA).withProcedureName(DBConstants.PROCEDURE_CORREO)
					.withoutProcedureColumnMetaDataAccess().declareParameters(			
							new SqlParameter("p_formato", Types.VARCHAR),							
							new SqlOutParameter("CUR_DATOS", OracleTypes.CURSOR, new ColumnMapRowMapper()),
							new SqlOutParameter("o_mensaje", Types.VARCHAR),
							new SqlOutParameter("o_retorno", Types.NUMERIC));

	        Map<String, Object> inParams = new HashMap<>();
			inParams.put("p_formato", nombre);
			Map<String, Object> result = jdbcCall.execute(inParams);
			List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("CUR_DATOS");
			  objeto = this.mapearFormatoHTMLBlob(result);
			  
	       
	        return objeto;
	    }
	 
	 
	  @SuppressWarnings("unchecked")
	    private FormatoHTML mapearFormatoHTMLBlob(Map<String, Object> resultados) throws SQLException {
	        List<Map<String, Object>> lista = (List<Map<String, Object>>) resultados.get("CUR_DATOS");
	        List<Blob> blobList = new ArrayList<>();
	        int totalLength = 0;
	        FormatoHTML item = null;
	        for (Map<String, Object> map : lista) {
	            item = new FormatoHTML();
	            item.setId(((BigDecimal) map.get("N_IDCORREO")).longValue());
	            item.setDescripcion((String) map.get("V_DESCRIPCION"));
	            byte[] formato = (byte[]) map.get("B_FORMATO");
	            //item.setArchivo(formato);
	            Blob blob = new SerialBlob(formato);
	            blob.setBytes(1, formato);
	            //item.setFormatoHtml(blob);
	            blobList.add(blob);
	        }
	        for (Blob blob : blobList) {
	            totalLength += blob.length();
	        }
	        byte[] data = new byte[totalLength];
	        int offset = 0;
	        for (Blob blob : blobList) {
	            byte[] blobData = blob.getBytes(1, (int) blob.length());
	            System.arraycopy(blobData, 0, data, offset, blobData.length);
	            offset += blobData.length;
	        }
	        
	        Blob resultBlob = new javax.sql.rowset.serial.SerialBlob(data);
	        item.setFormatoHtml(resultBlob);
	        return item;
	    }
}
