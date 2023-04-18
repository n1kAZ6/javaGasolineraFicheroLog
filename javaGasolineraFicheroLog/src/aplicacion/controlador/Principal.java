package aplicacion.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import aplicacion.entidades.Repostaje;
import aplicacion.servicios.ImplMenu;
import aplicacion.servicios.ImplRepostaje;
import aplicacion.servicios.InterfazMenu;
import aplicacion.servicios.InterfazRepostaje;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		List<Repostaje>baseDatosNormal = new ArrayList<>();
		List<Repostaje>baseDatosFactura = new ArrayList<>();

		Repostaje reposFactura = new Repostaje();
		Repostaje reposNormal = new Repostaje();

		InterfazMenu intM = new ImplMenu();
		InterfazRepostaje intR =new ImplRepostaje();
		
		Scanner scan = new Scanner(System.in);
		boolean cerrarMenu=false;
		int opcion;
		do {
			intM.mostrarMenu();
			System.out.println("Introduza la opción deseada: ");
			opcion=scan.nextInt();
			System.out.println("[INFO] - Has seleccionado la opcion " + opcion);

			switch(opcion) {
				case 1:
					intR.repostajeNormal(baseDatosNormal);
					break;
				case 2:
					intR.repostajeFactura(baseDatosFactura);
					break;
				case 3:
					intR.verRepostajes(baseDatosNormal, baseDatosFactura);
					break;
				case 4:
					String eliminarRepostaje=JOptionPane.showInputDialog("Cual repostaje quiere eliminar normal o con factura (n/f): ");
					if(eliminarRepostaje.equalsIgnoreCase("n")) 
						intR.eliminarRepostajeNormal(baseDatosNormal);
					else if(eliminarRepostaje.equalsIgnoreCase("f")) 
						intR.eliminarRepostajeFactura(baseDatosFactura);
					else
						System.err.println("No ha introducido repostaje con factura o normal (f/n)");
					break;
				case 5:
					String modificiarRepostaje=JOptionPane.showInputDialog("Cual repostaje quiere modificar normal o con factura (n/f): ");
					if(modificiarRepostaje.equalsIgnoreCase("n")) 
						intR.modificarRepostaje(baseDatosNormal);
					else if(modificiarRepostaje.equalsIgnoreCase("f")) 
						intR.modificarRepostaje(baseDatosFactura);
					else
						System.err.println("No ha introducido repostaje con factura o normal (f/n)");
					break;
				case 6:
					cerrarMenu=true;
					break;
				default:
					System.err.println("**Error: opción elegida no disponible");
					break;
			}
					
		}while(!cerrarMenu);
		System.out.println("Desconectando, Gracias por su confianza en nuestra gasolinera!");
	}

}
