package Grafo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

import Utils.Pair;

public class GrafoListaDeAristasImplementacion<T> extends GrafoMatrizAdyacencia {
	
	static class Arista <T>{
		T origen;
		T destino;
		Double peso;
		public Arista(T origen, T destino, Double peso) {
			this.origen = origen;
			this.destino = destino;
			this.peso = peso;
		}
		
		
	}
	
	List <Arista> aristas = new ArrayList(); 
	public Map <T,Integer> id;
	public Map <Integer, T> di;

	
	static class OrdenarAristas implements Comparator<Arista>{

		
		@Override
		public int compare(Arista o1, Arista o2) {
			return  (o2.peso.compareTo(o1.peso));
		}

	}
	
	public GrafoListaDeAristasImplementacion(List<Arista> aristas, Map<T, Integer> id, Map<Integer, T> di) {
		this.aristas = aristas;
		this.id = id;
		this.di = di;
	}

	@Override
	public void addVertex(Object vertex) {
		if(!hasVertex(vertex)) {
			id.put((T) vertex, id.size()+1);
			di.put(di.size()+1, (T) vertex);
		}
	}

	@Override
	public void addEdge(Object origin, Object destination, Double weight) {
		if(!hasEdge(origin, destination)) {
			Arista a = new Arista(origin, destination, weight);
			aristas.add(a);
		}
	}

	@Override
	public boolean hasVertex(Object vertex) {
		
		if(id.containsKey(vertex))
			return true;
		else
			return false;
	}

	@Override
	public boolean hasEdge(Object origin, Object destination) {
		
		if(id.containsKey(origin) && id.containsKey(destination)) {
			return true;
		}
		return false;
	}
	
	@Override
	public Vector<T> dfs (Object vertex) {
		return null;
	}

	@Override
	public Vector<T> bfs(Object vertex) {
		return null;
	}

	@Override
	public Vector dijkstra(Object vertex) {
		return null;
	}

	@Override
	public Vector< Vector<Double> > floydWarshall() {
		return null;
	}

	@Override
	public Vector kruskall() {
		
		Arrays.sort(aristas.toArray());
		return null;
	}

	@Override
	public Vector prim() {
		return null;
	}

}
