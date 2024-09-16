package pe.com.sedapal.evaluacion.util;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.naming.directory.Attribute;

 

import pe.com.sedapal.evaluacion.model.Constantes;

public class Util {
	public static String ordenarArray(String cadena) {
		ArrayList<Integer> arrayListInt = new ArrayList<>();
		
		for (int i = 0; i < cadena.length(); i++) {
			char  valor1 = cadena.charAt(i);
			
			int numero=Character.getNumericValue(valor1);
			arrayListInt.add(numero);
		}
		
		Comparator<Integer> comparador = Collections.reverseOrder();
		Collections.sort(arrayListInt, comparador.reversed());
		
		String cadenaOrdenada="";
		for (int i = 0; i < arrayListInt.size(); i++) {
			cadenaOrdenada=cadenaOrdenada+arrayListInt.get(i);
		}
		
		return cadenaOrdenada;
	}
	
	
	public static boolean validarConsecutivos(String cadena) {
		
		cadena= ordenarArray(cadena);
		
		if (cadena.length() == 1) {
			return false;
		}else if (cadena.isEmpty()) {
			return false;
		}
			
		for (int i = 0; i < cadena.length() - 1; i++) {
			char  valor1 = cadena.charAt(i);
			char  valor2 = cadena.charAt(i + 1);
			
			if ( (Character.getNumericValue(valor1) + 1) != (Character.getNumericValue(valor2)) ) {
				return false;
			}
		}
		return true;		
	}
	
	
	public static int validarCaracterRepetido(String cadena) {
		char[] arrayCadena=cadena.toCharArray();
		char caracter;
		int contador=0;
		
		for (int i = 0; i < arrayCadena.length; i++) {
			caracter=arrayCadena[i];
			for (int j = 1; j < arrayCadena.length; j++) {
				if(arrayCadena[j] == caracter) {
					contador++;
				}else {
					j=arrayCadena.length;
					contador=0;
				}
				
			}
			i=arrayCadena.length;
			
		}
		
		return contador;
	}
	
	

	public static HttpURLConnection setUrlProperties(HttpURLConnection urlCon, String user, String password,
			String contentType, String method, String parameters) throws IOException {
		String userCredentials = user + ":" + password;
		String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));
		urlCon.setRequestProperty("Authorization", basicAuth);
		urlCon.setUseCaches(false);
		urlCon.setDoInput(true);
		urlCon.setDoOutput(true);
		urlCon.setRequestProperty("Content-Type", contentType);
		urlCon.setRequestMethod(method);
		DataOutputStream wr = new DataOutputStream(urlCon.getOutputStream());
		wr.writeBytes(parameters);
		wr.flush();
		wr.close();
		return urlCon;
	}

	public static HttpURLConnection setUrlProperties(HttpURLConnection urlCon, String bearer, String contentType,
			String method, String parameters) throws IOException {
		String basicAuth = "Bearer " + bearer;
		urlCon.setRequestProperty("Authorization", basicAuth);
		urlCon.setUseCaches(false);
		urlCon.setDoInput(true);
		urlCon.setDoOutput(true);
		urlCon.setRequestProperty("Content-Type", contentType);
		urlCon.setRequestMethod(method);
		DataOutputStream wr = new DataOutputStream(urlCon.getOutputStream());
		wr.writeBytes(parameters);
		wr.flush();
		wr.close();
		return urlCon;
	}

	public static HttpURLConnection setUrlProperties(HttpURLConnection urlCon, String sessionToken, String contentType, String parameters) throws IOException {
		urlCon.setRequestProperty("Authorization", sessionToken);
		urlCon.setUseCaches(false);
		urlCon.setDoInput(true);
		urlCon.setDoOutput(true);
		urlCon.setRequestProperty("Content-Type", contentType);
		urlCon.setRequestMethod("POST");
		DataOutputStream wr = new DataOutputStream(urlCon.getOutputStream());
		wr.writeBytes(parameters);
		wr.flush();
		wr.close();
		return urlCon;
	}

	public static StringBuffer getResponse(InputStream is) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response;
	}

	public static String toDateFormat(String fecha) {
		try {
			SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			return myFormat.format(fromUser.parse(fecha));
		} catch (Exception e) {
			return fecha;
		}
	}
	

	
	public static String concatenar(String... strParte) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < strParte.length; i++) {
			sb.append(strParte[i]);
		}
		return sb.toString();
	}
	
	public static boolean  isEmptyString(String string) {
	    return string.isEmpty();
	}
	
	public static boolean isNotEmptyString(String string) {
	    return !(string == null || string.isEmpty());
	}
	
	
	public static boolean isEmptyStringOrNull(String string) {
	    return string == null || string.isEmpty();
	}
	
	@SuppressWarnings({ "unchecked", "null"})
	public static <T> T converTo(Object value, Class<T> classConvert, T valueDefault){
		
		try {
			if(value == null){
				return valueDefault;
			} else if(value instanceof Attribute) {
				Object obj = ((Attribute)value).get(0);
				return converTo(obj.toString(),classConvert, valueDefault);
			} else if(String.class == classConvert) {
				if(String.class.isInstance(value) && String.class != classConvert) {
					return converTo(value.toString(), classConvert, valueDefault);
				}else {
					return (T) value.toString();
				}
			}else if(Integer.class == classConvert) {
				return (T) Integer.valueOf(value.toString());
			}else if(Long.class == classConvert) {
				return (T) Long.valueOf(value.toString());
			}else if(Double.class == classConvert) {
				return (T) Double.valueOf(value.toString());
			}else if(Float.class == classConvert) {
				return (T) Float.valueOf(value.toString());
			}else if(BigDecimal.class == classConvert) {
				return (T) new BigDecimal(value.toString());
			}else if(Boolean.class == classConvert) {
				return (T) Boolean.valueOf(value.toString());
			}else if(Byte.class == classConvert) {
				return (T) Byte.valueOf(value.toString());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return valueDefault;
	}

	
	 
	
 
	
	  public static String completarConCerosIzq(String campo, int size)
	    {
	        String result = campo;
	        int largo = campo.length();
	        for(int i = largo; i < size; i++)
	            result = "0" + result;

	        return result;
	    }
	  
	  public static Constantes getConstante(List<Constantes> constantes, int id) {
		  List<Constantes> resultado = new ArrayList<>();
		  resultado = constantes.stream().filter(c -> c.getCodigo() == id).collect(Collectors.toList());
		  if (resultado.size()>0) {
			  return resultado.get(0);
		  } else {
			  return null;
		  }
	  }
	  
	  public static String remueveCerosIzquierda(String str) {
	        if (str == null || str.isEmpty()) {
	            return str;
	        }
	        return str.replaceFirst("^0+(?!$)", "");
	    }
	  
	  public static boolean imageExists(String urlString) {
	        try {
	            URL url = new URL(urlString);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("HEAD");
	          
	            int responseCode = connection.getResponseCode();
	            return (responseCode == HttpURLConnection.HTTP_OK);
	        } catch (IOException e) {
	            return false;
	        }
	    }
	  
	  public static String escalaLikertFinal(Double promedio) {
		  String escala="";
		  if (promedio<50) {
			  escala="No cumple";
		  } else {
			  if (promedio<75) {
				  escala="En Desarrollo";
			  } else {
				  if (promedio<95) {
					  escala="Efectivo";
				  }   else {
					  escala="Altamente efectivo";
				  }
			  }
		  }
		  return escala;
		  
	  }
}
