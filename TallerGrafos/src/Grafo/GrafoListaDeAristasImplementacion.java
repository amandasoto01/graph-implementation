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

	private static final Double INF = 10000000.0;
	
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

	public GrafoListaDeAristasImplementacion() {
		aristas= new ArrayList<>();
		id = new HashMap<>();
		di = new HashMap<>();
	}

	@Override
	public void addVertex(Object vertex) {
		if(!hasVertex(vertex)) {
			id.put((T) vertex, id.size());
			di.put(di.size(), (T) vertex);
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
			for(int i = 0; i<aristas.size(); i++) {
				if(aristas.get(i).origen == origin && aristas.get(i).destino == destination) {
					return true;
				}
			}
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

	@Override
	public Pair<Boolean, Vector<T>> bellmanFord(Object src) {
Vector<Double> dist = new Vector<>(); 
		
		int pSrc = id.get(src);
		  
        for (int i=0; i<id.size(); ++i) {
        	dist.add(INF);
        }

        dist.set(pSrc, 0.0);
  
        for (int i=1; i<id.size(); ++i) 
        { 
            for (int j=0; j<aristas.size(); ++j) 
            { 
                int u = id.get(aristas.get(j).origen); 
                int v = id.get(aristas.get(j).destino); 
                Double weight = aristas.get(j).peso; 
                if (dist.get(u)!=INF && 
                		dist.get(u)+ weight< dist.get(v)) 
                	dist.set(v,dist.get(u)+weight); 
            } 
        } 
  
        for (int j=0; j<aristas.size(); ++j) 
        { 
        	
        	 int u = id.get(aristas.get(j).origen); 
             int v = id.get(aristas.get(j).destino); 
             Double weight = aristas.get(j).peso; 
             if (dist.get(u)!=INF && 
             		dist.get(u)+ weight< dist.get(v)) {
            	 return new Pair<Boolean,Vector<T>>(false, null);
             }
        } 
        return new Pair<Boolean,Vector<T>>(true,null);
     
	}

}
