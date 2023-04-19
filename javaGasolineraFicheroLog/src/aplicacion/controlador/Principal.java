package aplicacion.controlador;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import aplicacion.entidades.Repostaje;
import aplicacion.servicios.ImplGestionFicheros;
import aplicacion.servicios.ImplMenu;
import aplicacion.servicios.ImplRepostaje;
import aplicacion.servicios.InterfazGestionFicheros;
import aplicacion.servicios.InterfazMenu;
import aplicacion.servicios.InterfazRepostaje;

public class Principal {

	//Constante con ruta del archivo de LOG en que se escribirá. Para evitar error debe cambiar por su username y existir en su escritorio. 
	public static final String RUTA_ARCHIVO_LOG = "C:\\Users\\nico\\Desktop\\log.txt"; 
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		List<Repostaje>baseDatosNormal = new ArrayList<>();
		List<Repostaje>baseDatosFactura = new ArrayList<>();

		InterfazMenu intM = new ImplMenu();
		InterfazRepostaje intR =new ImplRepostaje();
		InterfazGestionFicheros intF = new ImplGestionFicheros();
		
		Scanner scan = new Scanner(System.in);
		boolean cerrarMenu=false;
		int opcion;
		try {
			do {
				intM.mostrarMenu();
				intF.escrituraFichero(RUTA_ARCHIVO_LOG, LocalDateTime.now() +" [INFO] - Mostrando menú principal");
				System.out.println("\nIntroduza la opción deseada: ");
				opcion=scan.nextInt();
				intF.escrituraFichero(RUTA_ARCHIVO_LOG, LocalDateTime.now() +" [INFO] - Usuario seleccionó la opción " + opcion);
				System.out.println("\n[INFO] - Has seleccionado la opcion " + opcion);
	
				switch(opcion) {
					case 1:
						intF.escrituraFichero(RUTA_ARCHIVO_LOG, LocalDateTime.now() +" [INFO] - Se accede al método repostajeNormal()");
						intR.repostajeNormal(baseDatosNormal);
						break;
					case 2:
						intF.escrituraFichero(RUTA_ARCHIVO_LOG, LocalDateTime.now() + " [INFO] - Se accede al método repostajeFactura()");
						intR.repostajeFactura(baseDatosFactura);
						break;
					case 3:
						intF.escrituraFichero(RUTA_ARCHIVO_LOG, LocalDateTime.now() +" [INFO] - Se accede al método verRepostaje()");
						intR.verRepostajes(baseDatosNormal, baseDatosFactura);
						break;
					case 4:
						intF.escrituraFichero(RUTA_ARCHIVO_LOG, LocalDateTime.now() +" [INFO] - Se accede al método eliminarRepostaje()");
						intR.eliminarRepostaje(baseDatosNormal,baseDatosFactura);
						break;
					case 5:
						intF.escrituraFichero(RUTA_ARCHIVO_LOG, LocalDateTime.now() +" [INFO] - Se accede al método modificarRepostaje()");
						intR.modificarRepostaje(baseDatosNormal, baseDatosFactura);
						break;
					case 6:
						cerrarMenu=true;
						intF.escrituraFichero(RUTA_ARCHIVO_LOG, LocalDateTime.now() +" [INFO] - Cierre de la aplicación solicitado");
						break;
					default:
						System.err.println("\n**[ERROR] opción elegida no disponible **");
						intF.escrituraFichero(RUTA_ARCHIVO_LOG, LocalDateTime.now() +" [ERROR] - Usuario seleccionó una opción no disponible");
						break;
				}
						
			}while(!cerrarMenu);
		} catch(InputMismatchException e) {
			System.err.println("\n**[ERROR] entrada inválida: por favor ingrese un número entero **");
			intF.escrituraFichero(RUTA_ARCHIVO_LOG, LocalDateTime.now() +" [ERROR InputMismatchException] - El usuario introdujo entrada inválida por scanner ");
            scan.nextLine(); //limpiar el buffer de entrada para que si se produce la excepcion pueda continuar la aplicación
		} catch(NullPointerException npe) {
			System.err.println("\n**[ERROR] ocurrió una excepción no esperada: " + npe.getMessage() + " **");
			intF.escrituraFichero(RUTA_ARCHIVO_LOG, LocalDateTime.now() +" [ERROR NullPointerException] - El objeto al que se accede tiene un valor null "+ npe.getMessage());
		} catch(Exception e) {
			System.err.println("\n**[ERROR] ocurrió una excepción no esperada: " + e.getMessage() + " **");
			intF.escrituraFichero(RUTA_ARCHIVO_LOG, LocalDateTime.now() +" [ERROR Exception] - Se produjo una excepción no esperada "+ e.getMessage());
		}	
		System.out.println("\nDesconectando, Gracias por su confianza en nuestra gasolinera!");
		intF.escrituraFichero(RUTA_ARCHIVO_LOG, LocalDateTime.now() +" [INFO] - Aplicación cerrada");
	}

}
