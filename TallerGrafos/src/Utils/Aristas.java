package Utils;


public class Aristas<T> {
	private double costo;
	private T destino;
	private T origen;
	
	public Aristas(double costo, T origen, T destino) {
		super();
		this.origen= origen;
		this.costo = costo;
		this.destino = destino;
	}



	public T getOrigen() {
		return origen;
	}



	public void setOrigen(T origen) {
		this.origen = origen;
	}



	@Override
	public String toString() {
		return "Aristas [costo=" + costo + ", destino=" + destino + ", origen=" + origen + "]";
	}



	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public T getDestino() {
		return destino;
	}

	public void setDestino(T destino) {
		this.destino = destino;
	}
	
	
	
	
}
