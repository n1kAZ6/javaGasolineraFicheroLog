package aplicacion.servicios;

import java.awt.HeadlessException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import aplicacion.controlador.Principal;
import aplicacion.entidades.Repostaje;

/**
 * Clase que implementa la interfazRepostaje donde se definen al detalle la funcionalidad de los métodos
 * que dan servicio al repostaje.
 * @author nico
 *
 */
public class ImplRepostaje implements InterfazRepostaje{

	private double precioLitro95=1.65;
	private double precioLitro98=1.85;
	private double precioLitroDiesel=1.70;
	private InterfazGestionFicheros intF = new ImplGestionFicheros();
	
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

		//Guardamos en la lista el objeto tipo respostaje (normal) con sus atributos
		baseDatosNormal.add(new Repostaje(generarId(baseDatosNormal),LocalDate.now(),litrosArepostar,importe));
		intF.escrituraFichero(Principal.RUTA_ARCHIVO_LOG, LocalDateTime.now() + " [INFO] - Instacia Repostaje normal guardado en la base de datos");

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
		//Guardamos en la lista el objeto respostaje (factura) correspondiente con sus atributos especificos
		baseDatosFactura.add(new Repostaje(dniCliente, matricula,generarId(baseDatosFactura),LocalDate.now(),litrosArepostar,importe));
		intF.escrituraFichero(Principal.RUTA_ARCHIVO_LOG, LocalDateTime.now() + " [INFO] - Instacia Repostaje factura guardado en la base de datos");
	}

	@Override
	public void verRepostajes(List<Repostaje> baseDatosNormal, List<Repostaje> baseDatosFactura) {

		if(esRepostajeConFactura()) {		
			if(!baseDatosFactura.isEmpty()) {		
				for(Repostaje repostaje : baseDatosFactura) 
					System.out.println(repostaje.toString());
				
				intF.escrituraFichero(Principal.RUTA_ARCHIVO_LOG, LocalDateTime.now() +" [INFO] - Lista de repostaje factura visualizada");
			}
			else 
				System.out.println("\n[INFO] No hay datos de repostajes con factura que mostrar");
		}
		else {
			if(!baseDatosNormal.isEmpty()) {
				for(Repostaje repostaje : baseDatosNormal) 
					System.out.println(repostaje.toString());
				
				intF.escrituraFichero(Principal.RUTA_ARCHIVO_LOG, LocalDateTime.now() +" [INFO] - Lista de repostaje normal visualizada");
			}
			else 
				System.out.println("\n[INFO] No hay datos de repostaje normal que mostrar");
		}
	}

	@Override
	public void eliminarRepostaje(List<Repostaje> baseDatosNormal,List<Repostaje> baseDatosFactura) {
		
		int posicionRepostaje=0;
	
		if(esRepostajeConFactura()){	
			if(baseDatosFactura.isEmpty())
				System.out.println("\n[INFO] No hay repostajes con factura registrados para eliminar");
			else {  //Si el usuario quiere eliminar repostaje con factura y al menos hay un repostaje registrado entra en este else para pedir la modificación	
				posicionRepostaje=encuentraPosicionRepostaje(baseDatosFactura);
	
				if(posicionRepostaje != -1) { //Si no es -1 es que existe el repostaje (factura) que el usuario quiere eliminar
					baseDatosFactura.remove(posicionRepostaje);
					System.out.println("\n[INFO] Repostaje eliminado correctamente");
					intF.escrituraFichero(Principal.RUTA_ARCHIVO_LOG, LocalDateTime.now() +" [INFO] - Repostaje factura eliminado de la base de datos");
				}
				else
					System.out.println("\n[INFO] El repostaje no se encuentra en la base de datos");
			}	
		} 
		else { //Entra en este bloque solo si el usuario eligió eliminar respotaje normal
			if(baseDatosNormal.isEmpty())
				System.out.println("\n[INFO] No hay repostajes normal registrados para eliminar");
			else {
				posicionRepostaje=encuentraPosicionRepostaje(baseDatosNormal);
				
				if(posicionRepostaje != -1) { //Si no es -1 es que existe el repostaje (normal) que el usuario quiere eliminar
					baseDatosNormal.remove(posicionRepostaje);
					System.out.println("\n[INFO] Repostaje eliminado correctamente");
					intF.escrituraFichero(Principal.RUTA_ARCHIVO_LOG, LocalDateTime.now() +" [INFO] - Repostaje normal eliminado de la base de datos");
				}
				else
					System.out.println("\n[INFO] El repostaje no se encuentra registrado en la base de datos");		
			
			}	
		}					
	}

	@Override
	public void modificarRepostaje(List<Repostaje> baseDatosNormal,List<Repostaje> baseDatosFactura) {
		
		int posicionRepostaje=0;
		
		if(esRepostajeConFactura()){ 	
			if(baseDatosFactura.isEmpty())
				System.out.println("\n[INFO] No hay repostajes con factura registrados para modificar");		
			else { //Si el usuario quiere modificar repostaje con factura y al menos hay un repostaje registrado entra en este else para pedir la modificación	
				posicionRepostaje=encuentraPosicionRepostaje(baseDatosFactura);	
				if(posicionRepostaje != -1) { //Si cumple la condición es que existe el repostaje (factura) que el usuario quiere modificar 
					modificarCampo(baseDatosFactura,posicionRepostaje);				
				} else 
					System.out.println("\n[INFO] El repostaje no se encuentra en la base de datos");			
			}			
		} 
		else { //Entra aquí solo si el usuario eligió modificar respotaje normal		
			if(baseDatosNormal.isEmpty())
				System.out.println("\n[INFO] No hay repostajes normal registrados para modificar");			
			else {	//Si al menos hay un repostaje normal registrado entra en este bloque para pedir la modificación		
				posicionRepostaje=encuentraPosicionRepostaje(baseDatosNormal);
				
				if(posicionRepostaje != -1) { //Si no es -1 es que existe el repostaje (normal) que el usuario quiere modificar
					modificarCampo(baseDatosNormal,posicionRepostaje);	
				} else 
					System.out.println("\n[INFO] El repostaje no se encuentra registrado en la base de datos");							
			}	
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
		try {
			
			do {
				System.out.println("\nQue tipo de gasolina desea repostar?");
				System.out.println("\n1. sin plomo 95");
				System.out.println("2. sin plomo 98");
				System.out.println("3. Diesel");
				tipoGasolina=scan.nextInt();
				
				if(tipoGasolina<1||tipoGasolina>3) {
					System.err.println("\n**[ERROR] no se reconoce la gasolina seleccionada **");
				}
			}while(tipoGasolina<1||tipoGasolina>3);
			
		} catch(InputMismatchException e) {
			throw new InputMismatchException("Valor no válido");
		}      
		
		return tipoGasolina;	
	}
	
	/**
	 * Metodo cuya función es identificar el tipo de repostaje con el que el usuario quiere trabajar
	 * @return true si es un repostaje con factura o false si no lo es
	 */
	private boolean esRepostajeConFactura(){
		
		String elegirRepostaje;

		try {
			
			elegirRepostaje=JOptionPane.showInputDialog("Selecciona tipo de respotaje, normal o con factura (n/f): ");
			
			if(elegirRepostaje.equalsIgnoreCase("n")) 
				return false;
			else if(elegirRepostaje.equalsIgnoreCase("f")) 
				return true;
			else
				System.err.println("\n[ERROR] No ha introducido repostaje con factura o normal (f/n)");
			
			
		}catch(HeadlessException he) {
			System.err.println("\n**[ERROR] Por favor, intente ejecutar esta aplicación en un entorno con una interfaz gráfica de usuario **");
			intF.escrituraFichero(Principal.RUTA_ARCHIVO_LOG, LocalDateTime.now()+ "[ERROR HeadlessException] - Debido a que se ejecuta la aplicación en entorno sin gráficos");
		}
		return false;
		
	}
	
	/**
	 * La finalidad de este método es encontrar la posición del objeto repostaje en la lista en base al id del repostaje, este lo introduce el usuario.
	 * @param baseDatos
	 * @return si existe el id repostaje devuelve la posición en la lista, si no devuelve -1
	 */
	private int encuentraPosicionRepostaje(List<Repostaje>baseDatos) {
		
		int idAencontrar, contPosicion=0;
		boolean idExiste = false;
		
		try {
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
		} catch(NumberFormatException nfe) {
			System.err.println("\n**[ERROR] Entrada inválida: por favor ingrese un número entero **");
			intF.escrituraFichero(Principal.RUTA_ARCHIVO_LOG, LocalDateTime.now()+ " [ERROR NumberFormatException] - El usuario introdujo entrada inválida por scanner, no se pudo ejecutar el método parseInt()");
		} catch(HeadlessException he) {
			System.err.println("\n**[ERROR] Por favor, intente ejecutar esta aplicación en un entorno con una interfaz gráfica de usuario **");
			intF.escrituraFichero(Principal.RUTA_ARCHIVO_LOG, LocalDateTime.now()+ "[ERROR HeadlessException] - Debido a que se ejecuta la aplicación en entorno sin gráficos");
		} 
		return -1;
	}

	/**
	 * Permite modificar el campo (atributo) de la instancia ya registrada anteriormente pidiendo al usuario el campo a modificar y el nuevo valor por consola.
	 * @param baseDatos
	 * @param posicionRepostaje (en la baseDatos)
	 */
	private void modificarCampo(List<Repostaje>baseDatos, int posicionRepostaje) {
		
		boolean seHaModificado=false;
	    try {
	        String campoAModificar = JOptionPane.showInputDialog("Introduce campo a modificar (litros, importe, dni o matricula");
	        switch (campoAModificar.toLowerCase()) {
	            case "litros":
	                double litros = Double.parseDouble(JOptionPane.showInputDialog("Introduce nueva cantidad de litros: "));
	                baseDatos.get(posicionRepostaje).setLitrosRepostados(litros);
					System.out.println("\n[INFO] Campo modificado correctamente");
					seHaModificado=true;
	                break;
	            case "importe":
	                double importe = Double.parseDouble(JOptionPane.showInputDialog("Introduce nuevo importe: "));
	                baseDatos.get(posicionRepostaje).setImporteTotal(importe);
					System.out.println("\n[INFO] Campo modificado correctamente");
					seHaModificado=true;
	                break;
	            case "dni":
	                String dni = JOptionPane.showInputDialog("Introduce nuevo dni:");
	                baseDatos.get(posicionRepostaje).setDniCliente(dni);
					System.out.println("\n[INFO] Campo modificado correctamente");
					seHaModificado=true;
	                break;
	            case "matricula":
	                String matricula = JOptionPane.showInputDialog("Introduce la nueva matricula:");
	                baseDatos.get(posicionRepostaje).setMatriculaVehiculoCliente(matricula);
					System.out.println("\n[INFO] Campo modificado correctamente");
					seHaModificado=true;
	                break;
	            default:
	            	System.err.println("Campo no válido");
	        }
	        
	    } catch (NumberFormatException e) {
			System.err.println("\n**[ERROR] Entrada inválida: por favor ingrese un número entero **");
			intF.escrituraFichero(Principal.RUTA_ARCHIVO_LOG, LocalDateTime.now()+ " [ERROR NumberFormatException] - El usuario introdujo entrada inválida por scanner, no se pudo ejecutar el método parseInt()");

	    } catch(NullPointerException npe) {
			System.err.println("\n**[ERROR] ocurrió una excepción no esperada: " + npe.getMessage() + " **");
			intF.escrituraFichero(Principal.RUTA_ARCHIVO_LOG, LocalDateTime.now() +" [ERROR NullPointerException] - El objeto al que se accede tiene un valor null "+ npe.getMessage());
		}
	    if(seHaModificado) 
			intF.escrituraFichero(Principal.RUTA_ARCHIVO_LOG, LocalDateTime.now() +" [INFO] - Repostaje modificado de la base de datos");
	}
	

	
}
