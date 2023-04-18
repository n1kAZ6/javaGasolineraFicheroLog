package aplicacion.servicios;
/**
 * Interfaz que tiene la cabecera de los métodos que dan servicio a la gestión de ficheros.
 * @author n1ko
 *
 */
public interface InterfazGestionFicheros {

	/**
	 * Este método realiza la escritura del fichero de la ruta que recibe por argumento
	 * @param rutaFichero (ruta absoluta donde se encuentra el fichero)
	 * @param mensaje (que se quiere escribir en el fichero)
	 */
	void escrituraFichero(String rutaFichero,String mensaje);
}
