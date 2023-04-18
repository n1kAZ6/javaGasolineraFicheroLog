package aplicacion.servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import aplicacion.entidades.Repostaje;

/**
 * Clase que implementa la interfazRepostaje donde se definen al detalle la funcionalidad de los métodos
 * que dan servicio al repostaje.
 * @author nico
 *
 */
public class ImplRepostaje implements InterfazRepostaje{

	private LocalDate fechaRepostaje = LocalDate.now();
	private double precioLitro95=1.65;
	private double precioLitro98=1.85;
	private double precioLitroDiesel=1.70;
	
	@Override
	public void repostajeNormal(List<Repostaje> baseDatosNormal) {
		
		double litrosArepostar, importe;
		Scanner scan = new Scanner(System.in);
		int tipoGasolina;
		
		tipoGasolina=elegirTipoGasolina();
		if(tipoGasolina==1) {
			System.out.println("\nEl precio del litro de combustible de sin plomo 95 es de "+precioLitro95+" €");
			System.out.println("¿Cuantos litros quiere repostar?");
			litrosArepostar=scan.nextDouble();
			importe=litrosArepostar*precioLitro95;
			System.out.print("El importe total de "+litrosArepostar+" litros de sin plomo 95 es ");
			System.out.printf("%1.2f €",importe);
			
		}else if(tipoGasolina==2) {
			System.out.println("\nEl precio del litro de combustible de sin plomo 98 es de "+ precioLitro98+" €");
			System.out.println("¿Cuantos litros quiere repostar?");
			litrosArepostar=scan.nextDouble();
			importe=litrosArepostar*precioLitro98;
			System.out.print("El importe total de "+litrosArepostar+" litros de sin plomo 98 es ");
			System.out.printf("%1.2f €",importe);
		} else {
			System.out.println("\nEl precio del litro de combustible diesel es de "+precioLitroDiesel+" €");
			System.out.println("¿Cuantos litros quiere repostar?");
			litrosArepostar=scan.nextDouble();
			importe=litrosArepostar*precioLitroDiesel;
			System.out.print("El importe total de "+litrosArepostar+" litros de diesel es ");
			System.out.printf("%1.2f €",importe);
		}
		System.out.println("\nGracias, repostaje finalizado.");

		Repostaje repostajeNormal = new Repostaje();
		
		//Se asigna a la instancia del objeto (con los setters) los datos (atributos) correspondientes del respotaje facilitados por el usuario
		repostajeNormal.SetIdentidicador(generarId(baseDatosNormal));
		repostajeNormal.setFechaActual(LocalDate.now());
		repostajeNormal.setLitrosRepostados(litrosArepostar);
		repostajeNormal.setImporteTotal(importe);
		
		//Guardamos en la BBDD el objeto respostajeNormal correspondiente con sus atributos especificos y la devuelve en el return
		baseDatosNormal.add(repostajeNormal);
	}

	@Override
	public void repostajeFactura(List<Repostaje> baseDatosFactura) {
		
		String dniCliente, matricula;
		double litrosArepostar, importe;
		Scanner scan = new Scanner(System.in);
		int tipoGasolina;
		
		
		System.out.println("Introduzca su DNI: ");
		dniCliente=scan.nextLine();
		System.out.println("Introduzca la matricula del vehículo: ");
		matricula=scan.nextLine();
		
		tipoGasolina=elegirTipoGasolina();
		if(tipoGasolina==1) {
			System.out.println("\nEl precio del litro de combustible de sin plomo 95 es de "+precioLitro95 + " €");
			System.out.println("\n¿Cuantos litros quiere repostar?");
			litrosArepostar=scan.nextDouble();
			importe=litrosArepostar*precioLitro95;
			System.out.print("\nEl importe total de "+litrosArepostar+" litros de sin plomo 95 es ");
			System.out.printf("%1.2f €",importe);
			
		}else if(tipoGasolina==2) {
			System.out.println("\nEl precio del litro de combustible de sin plomo 98 es de "+precioLitro98 +" €");
			System.out.println("\n¿Cuantos litros quiere repostar?");
			litrosArepostar=scan.nextDouble();
			importe=litrosArepostar*precioLitro98;
			System.out.print("El importe total de "+litrosArepostar+" litros de sin plomo 98 es ");
			System.out.printf("%1.2f €",importe);
		} else {
			System.out.println("\nEl precio del litro de combustible diesel es de "+precioLitroDiesel +" €");
			System.out.println("\n¿Cuantos litros quiere repostar?");
			litrosArepostar=scan.nextDouble();
			importe=litrosArepostar*+precioLitroDiesel;
			System.out.print("\nEl importe total de "+litrosArepostar+" litros de diesel es ");
			System.out.printf("%1.2f €",importe);
		}
		
		System.out.println("\nGracias, repostaje finalizado.");
		Repostaje repostajeConFactura = new Repostaje();
		
		//Se asigna a la instancia del objeto (con los setters) los datos (atributos) correspondientes del respotaje facilitados por el usuario
		repostajeConFactura.SetIdentidicador(generarId(baseDatosFactura));		
		repostajeConFactura.setDniCliente(dniCliente);
		repostajeConFactura.setImporteTotal(importe);
		repostajeConFactura.setMatriculaVehiculoCliente(matricula);
		repostajeConFactura.setLitrosRepostados(litrosArepostar);
		repostajeConFactura.setFechaActual(fechaRepostaje);
		
		//Guardamos en la BBDD el objeto respostajeNormal correspondiente con sus atributos especificos y la devuelve en el return
		baseDatosFactura.add(repostajeConFactura);
	}

	@Override
	public void verRepostajes(List<Repostaje> baseDatosNormal, List<Repostaje> baseDatosFactura) {

		if(esRepostajeConFactura()) {
			if(baseDatosFactura.size()>0) {
				for(int i=0;i<baseDatosFactura.size();i++) 
					System.out.println(baseDatosFactura.get(i).toString());			
			}
			else 
				System.out.println("\n[INFO] No hay datos de repostajes que mostrar");
		}
		else {
			if(baseDatosNormal.size()>0) {
				for(int i=0;i<baseDatosNormal.size();i++) 
					System.out.println(baseDatosNormal.get(i).toString());			
			}
			else 
				System.out.println("\n[INFO] No hay datos de repostaje que mostrar");
		}
	}

	@Override
	public void eliminarRepostaje(List<Repostaje> baseDatosNormal,List<Repostaje> baseDatosFactura) {
		
		int posicionRepostaje=0;
	
		if(esRepostajeConFactura()){	
			
			posicionRepostaje=encuentraPosicionRepostaje(baseDatosFactura);

			if(posicionRepostaje != -1) {
				System.out.println("\n[INFO] Repostaje eliminado correctamente");
				baseDatosFactura.remove(posicionRepostaje);
			}
			else
				System.out.println("\n[INFO] El repostaje no se encuentra en la base de datos");
			
		}
		else {
			
			posicionRepostaje=encuentraPosicionRepostaje(baseDatosNormal);
			
			if(posicionRepostaje != -1) {
				System.out.println("\n[INFO] Repostaje eliminado correctamente ---");
				baseDatosNormal.remove(posicionRepostaje);
			}
			else
				System.out.println("\n[INFO] El repostaje no se encuentra registrado en la base de datos");		
		}					
	}


	@Override
	public void modificarRepostaje(List<Repostaje> baseDatosNormal,List<Repostaje> baseDatosFactura) {
		int idmodificar, contadorPosicion=0;
		boolean idExiste=false;
		
		
		int posicionRepostaje=0;
		
		if(esRepostajeConFactura()){	
			
			posicionRepostaje=encuentraPosicionRepostaje(baseDatosFactura);

			if(posicionRepostaje != -1) { //Si cumple la condición es que existe el repostaje (factura) que el usuario quiere modificar 
				String campoAmodificar= JOptionPane.showInputDialog("Introduce campo a modificar (litros, importe, dni o matricula): ");
				
				if(campoAmodificar.equalsIgnoreCase("litros")) {
					double litros = Double.parseDouble(JOptionPane.showInputDialog("Introduce nueva cantidad de litros: "));
					baseDatosFactura.get(posicionRepostaje).setLitrosRepostados(litros);
					System.out.println("\n[INFO] Campo modificado correctamente");
				}
				else if(campoAmodificar.equalsIgnoreCase("importe")) {
					double importe = Double.parseDouble(JOptionPane.showInputDialog("Introduce nuevo importe: "));
					baseDatosFactura.get(posicionRepostaje).setImporteTotal(importe);	
				}
				else if(campoAmodificar.equalsIgnoreCase("dni")) {
					String dniNuevo = JOptionPane.showInputDialog("Introduce nuevo dni:");
					baseDatosFactura.get(posicionRepostaje).setDniCliente(dniNuevo);
				}
				else if(campoAmodificar.equalsIgnoreCase("matricula")) {
					String matriculaNueva = JOptionPane.showInputDialog("Introduce nueva matricula:");
					baseDatosFactura.get(posicionRepostaje).setMatriculaVehiculoCliente(matriculaNueva);
				}
			}
			else
				System.out.println("\n[INFO] El repostaje no se encuentra en la base de datos");
			
		}
		else {
			
			posicionRepostaje=encuentraPosicionRepostaje(baseDatosNormal);
			
			if(posicionRepostaje != -1) { //Si cumple la condición es que existe el repostaje (normal) que el usuario quiere modificar
				String campoAmodificar= JOptionPane.showInputDialog("Introduce campo a modificar (litros, importe, dni o matricula): ");
				
				if(campoAmodificar.equalsIgnoreCase("litros")) {
					double litros = Double.parseDouble(JOptionPane.showInputDialog("Introduce nueva cantidad de litros: "));
					baseDatosNormal.get(posicionRepostaje).setLitrosRepostados(litros);
					System.out.println("\n[INFO] Campo modificado correctamente");
				}
				else if(campoAmodificar.equalsIgnoreCase("importe")) {
					double importe = Double.parseDouble(JOptionPane.showInputDialog("Introduce nuevo importe: "));
					baseDatosNormal.get(posicionRepostaje).setImporteTotal(importe);	
				}
				else if(campoAmodificar.equalsIgnoreCase("dni")) {
					String dniNuevo = JOptionPane.showInputDialog("Introduce nuevo dni:");
					baseDatosNormal.get(posicionRepostaje).setDniCliente(dniNuevo);
				}
				else if(campoAmodificar.equalsIgnoreCase("matricula")) {
					String matriculaNueva = JOptionPane.showInputDialog("Introduce nueva matricula:");
					baseDatosNormal.get(posicionRepostaje).setMatriculaVehiculoCliente(matriculaNueva);
				}
			}
			else
				System.out.println("\n[INFO] El repostaje no se encuentra registrado en la base de datos");		
		}					
	}
	
	/**
	 * Método que genera un número entero que será el id único para cada repostaje que se va registrar
	 * @param baseDatosFactura
	 * @return id
	 */
	private int generarId(List<Repostaje>baseDatos) {
		
		int idMayor=0;
		if(baseDatos.isEmpty())
			return 1;
		else {
			for (Repostaje registro : baseDatos) {
			    if (registro.getIdentidicador() > idMayor) {
			        idMayor=registro.getIdentidicador();
			        break;
			    }
			}
		}
		return idMayor+1;	
	}
	
	/**
	 * Método para que pueda elegir el usuario el tipo de gasolina a repostar
	 * @return tipoGasolina: un entero que hace referencia al tipo de gasolina elegida
	 */
	private int elegirTipoGasolina() {

		
		Scanner scan = new Scanner(System.in);
		int tipoGasolina;
		do {
			System.out.println("\nQue tipo de gasolina desea repostar?");
			System.out.println("\n1. sin plomo 95");
			System.out.println("2. sin plomo 98");
			System.out.println("3. Diesel");
			tipoGasolina=scan.nextInt();
			
			if(tipoGasolina<1||tipoGasolina>3)
				System.err.println("\n**[ERROR] no se reconoce la gasolina seleccionada **");
		}while(tipoGasolina<1||tipoGasolina>3);
		
		return tipoGasolina;	
	}
	
	/**
	 * Metodo cuya función es identificar el tipo de repostaje con el que el usuario quiere trabajar
	 * @return true si es un repostaje con factura o false si no lo es
	 */
	private boolean esRepostajeConFactura(){
		String elegirRepostaje;

		elegirRepostaje=JOptionPane.showInputDialog("Selecciona tipo de respotaje, normal o con factura (n/f): ");
		
		if(elegirRepostaje.equalsIgnoreCase("n")) 
			return false;
		else if(elegirRepostaje.equalsIgnoreCase("f")) 
			return true;
		else
			System.err.println("\nNo ha introducido repostaje con factura o normal (f/n)");
		
		return false;
		
	}
	
	/**
	 * La finalidad de este método es encontrar la posición del repostaje en la lista en cuestión. El id del repostaje lo introduce el usuario.
	 * @param baseDatos
	 * @return si existe el id repostaje devuelve la posición en la lista, si no devuelve -1
	 */
	private int encuentraPosicionRepostaje(List<Repostaje>baseDatos) {
		int idAencontrar, contPosicion=0;
		boolean idExiste = false;
		
		idAencontrar=Integer.parseInt(JOptionPane.showInputDialog("Introduce el id del repostaje: "));
		for(Repostaje registro:baseDatos) {
			int idBaseDato = registro.getIdentidicador();
			if(idBaseDato==idAencontrar) {
				idExiste=true;
				break;
			}
			contPosicion++;
		}
		if(idExiste)
			return contPosicion;
		else
			return -1;
	}


	
}
