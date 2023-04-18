package aplicacion.servicios;

/**
 * Clase que implementa la interfaz menú y es donde se describe la funcionalidad concreta de 
 * los métodos de dicha interfaz.
 * @author nico
 *
 */
public class ImplMenu implements InterfazMenu {

	@Override
	public void mostrarMenu() {
		// TODO Auto-generated method stub
		System.out.println("\n---- Menú gasolinera ----");
		System.out.println("\n1. Repostaje normal");
		System.out.println("2. Repostaje factura");
		System.out.println("3. Ver repostajes");
		System.out.println("4. Eliminar repostajes");
		System.out.println("5. Modificar repostaje");
		System.out.println("6. Salir");
	}
	
}
