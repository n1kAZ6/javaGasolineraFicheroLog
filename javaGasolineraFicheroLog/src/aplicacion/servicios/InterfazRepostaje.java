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
	 * Llama al método de la clase elegirTipoGasolina, muestra al usuario el precio actual del combustible
	 * para preguntar por la cantidad a repostar e indicarle el costo final del repostaje dependiendo del tipo de combustible elegido.
	 * 
	 * @param baseDatosNormal La lista de objetos tipo Repostaje tipo normal
	 * @return la lista de objeto tipo Repostaje normal actualizada
	 */
	void repostajeNormal(List<Repostaje>baseDatosNormal);
	
	/**	  
	 * Llama al método de la clase elegirTipoGasolina, muestra al usuario el precio actual del combustible
	 * para preguntar por DNI, matricula del vehículo y la cantidad a repostar para indicarle el costo final del repostaje
	 * segun el tipo de combustible elegido.
	 * 
	 * @param baseDatosFactura La lista de objetos tipo Repostaje tipo factura
	 * @return la lista de objeto tipo Repostaje factura actualizada
	 */
	void repostajeFactura(List<Repostaje>baseDatosFactura);
	
	/**
	 * Pregunta al usuario cual tipo de repostaje quiere listar (factura o normal) y actua en consecuencia.
	 * @param baseDatosNormal
	 * @param baseDatosFactura
	 */
	void verRepostajes(List<Repostaje>baseDatosNormal,List<Repostaje>baseDatosFactura);
	
	/**
	 * Permite al usuario elegir el tipo de repostaje (Factura/normal) para solicitar el id del repostaje que quiere eliminar,
	 * si existe lo elimina de la lista y si no existe se muestra un aviso.
	 * @param baseDatosNormal
	 * @param baseDatosFactura
	 * @return la lista de objeto tipo Repostaje normal/factura actualizada si se hubiera eliminado
	 */
	void eliminarRepostaje (List<Repostaje>baseDatosNormal,List<Repostaje>baseDatosFactura);
	
	/**
	 * Muestra al usuario los repostajes y pide el id del repostaje que quiere modificar, si no existe se muestra un aviso.
	 * si existe le pide el campo a modificar de la BBDD (la lista) y el nuevo dato.
	 * @param baseDatosNormal
	 * @param baseDatosFactura
	 * @return la lista en cuestión con los repostaje actualizada si se hubiera modificado
	 */
	void modificarRepostaje(List<Repostaje>baseDatosNormal,List<Repostaje> baseDatosFactura);
}
