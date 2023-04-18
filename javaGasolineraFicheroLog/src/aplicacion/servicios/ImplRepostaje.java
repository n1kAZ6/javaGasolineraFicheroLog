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

	private LocalDate fechaRepostaje =  LocalDate.now();
	private double precioLitro95=1.65;
	private double precioLitro98=1.85;
	private double precioLitroDiesel=1.70;
	
	@Override
	public List<Repostaje> repostajeNormal(List<Repostaje> baseDatosNormal) {
		
		double litrosArepostar, importe;
		Scanner scan = new Scanner(System.in);
		int tipoGasolina;
		
		tipoGasolina=elegirTipoGasolina();
		if(tipoGasolina==1) {
			System.out.println("El precio del litro sin plomo 95 es "+precioLitro95);
			System.out.println("¿Cuantos litros quiere repostar?");
			litrosArepostar=scan.nextDouble();
			importe=litrosArepostar*precioLitro95;
			System.out.print("El importe total de "+litrosArepostar+" litros de sin plomo 95 es ");
			System.out.printf("%1.2f",importe);
			
		}else if(tipoGasolina==2) {
			System.out.println("El precio del litro sin plomo 98 es "+ precioLitro98);
			System.out.println("¿Cuantos litros quiere repostar?");
			litrosArepostar=scan.nextDouble();
			importe=litrosArepostar*precioLitro98;
			System.out.print("El importe total de "+litrosArepostar+" litros de sin plomo 98 es ");
			System.out.printf("%1.2f",importe);
		} else {
			System.out.println("El precio del litro de diesel es "+precioLitroDiesel);
			System.out.println("¿Cuantos litros quiere repostar?");
			litrosArepostar=scan.nextDouble();
			importe=litrosArepostar*precioLitroDiesel;
			System.out.print("El importe total de "+litrosArepostar+" litros de diesel es ");
			System.out.printf("%1.2f",importe);
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
		return baseDatosNormal;
	}

	@Override
	public List<Repostaje> repostajeFactura(List<Repostaje> baseDatosFactura) {
		
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
			System.out.println("El precio del litro sin plomo 95 es "+precioLitro95);
			System.out.println("¿Cuantos litros quiere repostar?");
			litrosArepostar=scan.nextDouble();
			importe=litrosArepostar*precioLitro95;
			System.out.print("El importe total de "+litrosArepostar+" litros de sin plomo 95 es ");
			System.out.printf("%1.2f",importe);
			
		}else if(tipoGasolina==2) {
			System.out.println("El precio del litro sin plomo 98 es "+precioLitro98);
			System.out.println("¿Cuantos litros quiere repostar?");
			litrosArepostar=scan.nextDouble();
			importe=litrosArepostar*precioLitro98;
			System.out.print("El importe total de "+litrosArepostar+" litros de sin plomo 98 es ");
			System.out.printf("%1.2f",importe);
		} else {
			System.out.println("El precio del litro de diesel es "+precioLitroDiesel);
			System.out.println("¿Cuantos litros quiere repostar?");
			litrosArepostar=scan.nextDouble();
			importe=litrosArepostar*+precioLitroDiesel;
			System.out.print("El importe total de "+litrosArepostar+" litros de diesel es ");
			System.out.printf("%1.2f",importe);
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
		return baseDatosFactura;
	}

	@Override
	public void verRepostajes(List<Repostaje> baseDatosNormal, List<Repostaje> baseDatosFactura) {

		String opcionRepostaje=JOptionPane.showInputDialog("Ver repostaje normal o con factura (n/f): ");
		if(opcionRepostaje.equalsIgnoreCase("n")) {
			if(baseDatosNormal.size()>0) {
				for(int i=0;i<baseDatosNormal.size();i++) 
					System.out.println(baseDatosNormal.get(i));			
			}
			else 
				System.out.println("No hay datos de repostajes que mostrar");
		}
		else if(opcionRepostaje.equalsIgnoreCase("f")) {
			if(baseDatosNormal.size()>0) {
				for(int i=0;i<baseDatosNormal.size();i++) 
					System.out.println(baseDatosFactura.get(i));			
			}
			else 
				System.out.println("No hay datos de repostajes que mostrar");
		}
		else
			System.err.println("**[ No ha introducido repostaje con factura o normal (f/n) ]**");
	}

	@Override
	public List<Repostaje> eliminarRepostajeNormal(List<Repostaje> baseDatosNormal) {
		
		int idAeliminar, contadorPosicion=0;
		boolean idExiste=false;
		
		System.out.println("---[ Mostrando base de datos Repostaje normal ]---");
		
		for(Repostaje registro:baseDatosNormal) 
			System.out.println(registro);
		
		idAeliminar=Integer.parseInt(JOptionPane.showInputDialog("Introduce el id del repostaje a eliminar: "));
		
		for(Repostaje registro:baseDatosNormal) {
			int idBaseDato = registro.getIdentidicador();
			if(idBaseDato==idAeliminar) {
				idExiste=true;
				break;
			}
			contadorPosicion++;
		}
		if(!idExiste)
			System.out.println("El registro con id "+ idAeliminar +" no se encuentra en la base de datos ");
		else {
			System.out.println("---[ Repostaje eliminado correctamente ]---");
			baseDatosNormal.remove(contadorPosicion);			
		}
		return baseDatosNormal;
	}

	@Override
	public List<Repostaje> eliminarRepostajeFactura(List<Repostaje> baseDatosFactura) {
		int idAborrar, contadorPosicion=0;
		boolean idExiste=false;
		System.out.println("---[ Mostrando los Repostaje con factura ]---");
		for(Repostaje registro:baseDatosFactura) {
			System.out.println(registro);
		}		
		idAborrar=Integer.parseInt(JOptionPane.showInputDialog("Introduce el id del repostaje a eliminar: "));

		for(Repostaje registro:baseDatosFactura) {
			int idBaseDato = registro.getIdentidicador();
			if(idBaseDato==idAborrar) {
				idExiste=true;
				break;
			}
			contadorPosicion++;
		}
		if(!idExiste)
			System.out.println("El registro con id "+ idAborrar +" no se encuentra en la base de datos ");
		else {
			System.out.println("---[ Repostaje eliminado correctamente ]---");
			baseDatosFactura.remove(contadorPosicion);
		}
					
		return baseDatosFactura;
	}

	@Override
	public List<Repostaje> modificarRepostaje(List<Repostaje> baseDatos) {
		int idmodificar, contadorPosicion=0;
		boolean idExiste=false;
		
		System.out.println("---[ Mostrando los Repostaje ]---");
		
		for(Repostaje registro:baseDatos) 
			System.out.println(registro);
				
		idmodificar=Integer.parseInt(JOptionPane.showInputDialog("Introduce el id del repostaje a modificar: "));

		for(Repostaje registro:baseDatos) {
			int idBaseDato = registro.getIdentidicador();
			if(idBaseDato==idmodificar) {
				idExiste=true;
				break;
			}
			contadorPosicion++;
		}
		if(!idExiste)
			System.out.println("El registro con id "+ idmodificar +" no se encuentra en la base de datos ");
		else {
			String campoAmodificar= JOptionPane.showInputDialog("Introduce campo a modificar (litros, importe, dni o matricula): ");
			
			if(campoAmodificar.equalsIgnoreCase("litros")) {
				double litros = Double.parseDouble(JOptionPane.showInputDialog("Introduce nueva cantidad de litros: "));
				baseDatos.get(contadorPosicion).setLitrosRepostados(litros);	
			}
			else if(campoAmodificar.equalsIgnoreCase("importe")) {
				double importe = Double.parseDouble(JOptionPane.showInputDialog("Introduce nuevo importe: "));
				baseDatos.get(contadorPosicion).setImporteTotal(importe);	
			}
			else if(campoAmodificar.equalsIgnoreCase("dni")) {
				String dniNuevo = JOptionPane.showInputDialog("Introduce nuevo dni:");
				baseDatos.get(contadorPosicion).setDniCliente(dniNuevo);
			}
			else if(campoAmodificar.equalsIgnoreCase("matricula")) {
				String matriculaNueva = JOptionPane.showInputDialog("Introduce nueva matricula:");
				baseDatos.get(contadorPosicion).setMatriculaVehiculoCliente(matriculaNueva);
			}

		}
		return baseDatos;			
	}
	private int generarId(List<Repostaje>baseDatosFactura) {
		
		//Crea un id único que no se ecuentre ya registrado.
		int idMaximo=1;
		boolean esIdUnico=false;
		while (!esIdUnico) {
		    esIdUnico = true;
		    for (Repostaje registro : baseDatosFactura) {
		        if (registro.getIdentidicador() == idMaximo) {
		            esIdUnico = false;
		            idMaximo++;
		            break;
		        }
		    }
		}
		return idMaximo;	
	}
	private int elegirTipoGasolina() {
		
		Scanner scan = new Scanner(System.in);
		int tipoGasolina;
		do {
			System.out.println("\nQue tipo de gasolina desea repostar?");
			System.out.println("1. sin plomo 95");
			System.out.println("2. sin plomo 98");
			System.out.println("3. Diesel");
			tipoGasolina=scan.nextInt();
			
			if(tipoGasolina<1||tipoGasolina>3)
				System.err.println("**[ERROR] no se reconoce la gasolina seleccionada **");
		}while(tipoGasolina<1||tipoGasolina>3);
		
		return tipoGasolina;	
}

	
}
