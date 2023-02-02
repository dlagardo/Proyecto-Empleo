package net.itinajeroutil;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;


	public class Utileria {
		public static String guardarArchivo(MultipartFile multiPart, String ruta) {
			// Obtenemos el nombre original del archivo.
			String nombreOriginal = multiPart.getOriginalFilename();
			nombreOriginal=nombreOriginal.replace(" ","-");
			String nombreFinal=randomAlphaNumeric(8)+ nombreOriginal;
			try {
			// Formamos el nombre del archivo para guardarlo en el disco duro.
			File imageFile = new File(ruta + nombreFinal);
			System.out.println("Archivo: " + imageFile.getAbsolutePath());
			//Guardamos fisicamente el archivo en HD.
			multiPart.transferTo(imageFile);
			return nombreFinal;
			} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
			return null;
			}
		}
		
		 public static String randomAlphaNumeric(int count) {
		        // El banco de caracteres
		        String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890";
		        // La cadena en donde iremos agregando un carácter aleatorio
		        StringBuilder builder = new StringBuilder();
		        while(count -- != 0) {
		        	int character =(int) (Math.random() * CARACTERES.length());
		        	builder.append(CARACTERES.charAt(character));
		        }
		        return builder.toString();
		    }
		 
		 /**
		  * Funcion que elimina un archivo dada una ruta
		  * @param ruta
		  * @return true|false si se elimino o no
		  */
		 public static boolean eliminarArchivo(String ruta) {
			
		 	File archivo =  new File(ruta);
		     if (!archivo.exists()) {
		         return false;
		     } else {
		     	return archivo.delete();
		     }
		    
		 }
}
