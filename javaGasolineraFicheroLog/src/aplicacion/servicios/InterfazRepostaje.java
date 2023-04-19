package aplicacion.servicios;

import java.util.List;
import aplicacion.entidades.Repostaje;

/**
 * Interfaz que define la cabecera de los métodos que tendrá que se tendrán que implementar para dar servicio a repostaje
 * @author nico
 *
 */
public interface InterfazRepostaje {

	/**	  
	 * Crea instancias de tipo Repostaje (normal) y son almacenadas en su lista correspondiente. 
	 * Los datos necesarios para el repostaje los introduce el usuario por consola.
	 * @param baseDatosNormal La lista de objetos tipo Repostaje tipo normal
	 */
	void repostajeNormal(List<Repostaje>baseDatosNormal);
	
	/**	  
	 * Crea instancias de tipo Repostaje (factura) y son almacenadas en su lista correspondiente
	 * Los datos de la factura y el repostaje es introducido por el usuario en consola.
	 * segun el tipo de combustible elegido.
	 * @param baseDatosFactura La lista de objetos tipo Repostaje tipo factura
	 */
	void repostajeFactura(List<Repostaje>baseDatosFactura);
	
	/**
	 * Pregunta al usuario cual tipo de repostaje quiere listar (factura o normal) y actua en consecuencia.
	 * @param baseDatosNormal
	 * @param baseDatosFactura
	 */
	void verRepostajes(List<Repostaje>baseDatosNormal,List<Repostaje>baseDatosFactura);
	
	/**
	 * Elimina instancia de tipo Repostaje (Factura/normal) registradas con anterioridad por medio del id que introduce
	 * el usuario por consola.
	 * @param baseDatosNormal
	 * @param baseDatosFactura
	 */
	void eliminarRepostaje (List<Repostaje>baseDatosNormal,List<Repostaje>baseDatosFactura);
	
	/**
	 * Permite al usuario modificar el valor de los atributos repostaje ya realizados con anterioridad.
	 * Si existe le pide el campo a modificar y el nuevo valor.
	 * @param baseDatosNormal
	 * @param baseDatosFactura
	 */
	void modificarRepostaje(List<Repostaje>baseDatosNormal,List<Repostaje> baseDatosFactura);
}
