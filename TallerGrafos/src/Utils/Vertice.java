package Utils;

import java.util.ArrayList;
import java.util.List;

public class Vertice<T> {
	private T dato;
	private List<Aristas<T>> vecinos;
	private boolean visitado;

	public Vertice(T datollega) {
		dato = datollega;
		this.vecinos = new ArrayList<Aristas<T>>();
		visitado = false;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Vertice [dato=" + dato + ", vecinos=" + vecinos + ", visitado=" + visitado + "]";
	}

	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}

	public List<Aristas<T>> getVecinos() {
		return vecinos;
	}

	public void setVecinos(List<Aristas<T>> vecinos) {
		this.vecinos = vecinos;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	public void addEdgeVertex(T origin, T destination, Double weight) {
		this.vecinos.add(new Aristas<T>(weight, this.dato , destination));
	}

	public boolean hasEdge(T origin, T destination) {
		for (Aristas<T> aristas : vecinos) {
			if (aristas.getDestino().equals(destination) && origin.equals(this.dato)) {
				return true;
			}
		}
		return false;
	}

}
