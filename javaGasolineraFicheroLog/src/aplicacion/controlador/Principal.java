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

		InterfazMenu intM = new ImplMenu();
		InterfazRepostaje intR =new ImplRepostaje();
		
		Scanner scan = new Scanner(System.in);
		boolean cerrarMenu=false;
		int opcion;
		do {
			intM.mostrarMenu();
			System.out.println("\nIntroduza la opción deseada: ");
			opcion=scan.nextInt();
			System.out.println("\n[INFO] - Has seleccionado la opcion " + opcion);

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
					intR.eliminarRepostaje(baseDatosNormal,baseDatosFactura);
					break;
				case 5:
					intR.modificarRepostaje(baseDatosNormal, baseDatosFactura);
					break;
				case 6:
					cerrarMenu=true;
					break;
				default:
					System.err.println("\n**[ERROR] opción elegida no disponible **");
					break;
			}
					
		}while(!cerrarMenu);
		System.out.println("\nDesconectando, Gracias por su confianza en nuestra gasolinera!");
	}

}
