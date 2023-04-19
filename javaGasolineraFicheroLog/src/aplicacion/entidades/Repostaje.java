package aplicacion.entidades;

import java.time.LocalDate;

/**
 * Clase entidad Repostaje que representa a dicha entidad. Contiene sus atributos, constructores y los getter & setter 
 * para acceso a los atributos.
 * @author nico
 *
 */
public class Repostaje {

	//Atributos encapsulados
	private double litrosRepostados;	
	private double importeTotal;
	private String dniCliente; 
	private String matriculaVehiculoCliente;
	private LocalDate fechaRepostaje;
	private int identificador;

	//Constructores con paso de parametros (para instancias factura/normal)y vacío
	public Repostaje(String dniCliente, String matriculaVehiculoCliente,int identificador, LocalDate fechaRepostaje, double litrosRepostados, double importeTotal) {
		this.identificador=identificador;
		this.importeTotal = importeTotal;
		this.dniCliente = dniCliente;
		this.matriculaVehiculoCliente = matriculaVehiculoCliente;
		this.fechaRepostaje = fechaRepostaje;
		this.litrosRepostados = litrosRepostados;
	}
	public Repostaje (int identificador, LocalDate fechaRepostaje, double litrosRepostados, double importeTotal) {
		this.identificador=identificador;
		this.fechaRepostaje=fechaRepostaje;
		this.litrosRepostados=litrosRepostados;
		this.importeTotal=importeTotal;
	}

	public Repostaje() {
		
	}
	
	//Getter and setter para mostrar/modificar los atributos encapsulados
	public LocalDate getFechaActual() {
		return fechaRepostaje;
	}
	public double getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(double importeTotal) {
		this.importeTotal = importeTotal;
	}
	public String getDniCliente() {
		return dniCliente;
	}
	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}
	public String getMatriculaVehiculoCliente() {
		return matriculaVehiculoCliente;
	}
	public void setMatriculaVehiculoCliente(String matriculaVehiculoCliente) {
		this.matriculaVehiculoCliente = matriculaVehiculoCliente;
	}
	public double getLitrosRepostados() {
		return litrosRepostados;
	}

	public void setLitrosRepostados(double litros) {
		this.litrosRepostados = litros;
	}
	public void setFechaActual(LocalDate fechaActual) {
		this.fechaRepostaje = fechaActual;
	}
	public int getIdentidicador() {
		return identificador;
	}
	public void SetIdentidicador(int id) {
		this.identificador=id;
	}

	//Sobre escritura toString del objeto para que no muestra la referencia en memoria al imprimir por consola
	@Override
	public String toString() {
		return "\n--- Datos del Repostaje ---\n\nid: "+ identificador+ "\nFecha del repostaje: " + fechaRepostaje+"\nLitros repostados: " + litrosRepostados + "\nImporte pagado: " + importeTotal + " €\n"
				+ ((dniCliente==null) ? "" : "DNI del cliente:"+ dniCliente)  + ((matriculaVehiculoCliente==null)? "": "\nMatricula del vehículo: "+matriculaVehiculoCliente);
	}
}
