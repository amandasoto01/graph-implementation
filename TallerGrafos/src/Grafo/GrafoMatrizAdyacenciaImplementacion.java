package Grafo;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

import Utils.Pair;

public class GrafoMatrizAdyacenciaImplementacion<T> extends GrafoMatrizAdyacencia {
	
	public Vector< Vector < Double > > graph;
	public Map <T,Integer> id;
	public Map <Integer, T> di;
	private final Double INF = new Double(1000000.0);
	
	
	public GrafoMatrizAdyacenciaImplementacion() {
		super();
		graph = new Vector < Vector < Double > > ();
		id = new HashMap< T, Integer>();
		di = new HashMap<Integer, T>();
	}

	@Override
	public void addVertex(Object vertex) {
		// TODO Auto-generated method stub
		if(!hasVertex(vertex)) {
			id.put((T)vertex,  id.size());
			di.put(di.size(), (T) vertex);
		}
		
		for(int i=0; i<graph.size(); i++) {
			graph.get(i).add(INF);
		}
		
		Vector<Double> v = new Vector<Double>();
		
		for(int i=0; i<v.size()+1; i++) {
			v.add(INF);
		}
		graph.add(v);
	}

	@Override
	public void addEdge(Object origin, Object destination, Double weight) {
		// TODO Auto-generated method stub
		if(!hasEdge(origin,destination)) {
			Integer inicio = id.get(origin);
			Integer fin = id.get(destination);
			graph.get(inicio).add(fin, weight);
		}
	}

	@Override
	public boolean hasVertex(Object vertex) {
		// TODO Auto-generated method stub
		if(id.containsKey(vertex)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean hasEdge(Object origin, Object destination) {
		// TODO Auto-generated method stub
		if(hasVertex(origin) && hasVertex(destination) ) {
			Integer inicio = id.get(origin);
			Integer fin = id.get(destination);
			if( graph.get(inicio).get(fin) == INF) {
				return false;
			}else {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Vector<T> dfs (Object vertex) {
		// TODO Auto-generated method stub
		//pila
		int val=0;
		Vector<Boolean> visitados = new Vector<Boolean>();
		Vector<T> resultado = new Vector<T>();
		Stack<T> pila = new Stack<T>();
		pila.push((T)vertex);
		
		for(int i=0; i<graph.size(); i++) {
			visitados.add(false);
		}
		
		if(hasVertex(vertex)) {
			 val = id.get(vertex);
			 
			 while(!pila.empty()) {
				 val = id.get(pila.peek());
					if(!visitados.get(val)) {
						
						resultado.add(di.get(pila.peek()));
						visitados.set(id.get(pila.peek()), true);
						
						pila.pop();
						for(int i=0; i<graph.get(val).size(); i++) {
							if(graph.get(val).get(i) != INF) {
								pila.push((T) graph.get(val).get(i));
							}
						}
					}else {
						pila.pop();
					}
				}
		}
		
		
		return resultado;
	}

	@Override
	public Vector<T> bfs(Object vertex) {
		// TODO Auto-generated method stub
		//cola
		
		int val=0;
		Vector<Boolean> visitados = new Vector<Boolean>();
		Vector<T> resultado = new Vector<T>();
		Queue<T> cola = new LinkedList<T> ();
		
		cola.add((T) vertex);
		
		for(int i=0; i<graph.size(); i++) {
			visitados.add(false);
		}
		
		if(hasVertex(vertex)) {
			 val = id.get(vertex);
			 
			 while(!cola.isEmpty()) {
				 val = id.get(cola.peek());
				 if(!visitados.get(val)) {
						
						resultado.add(di.get(cola.peek()));
						visitados.set(id.get(cola.peek()), true);
						
						cola.remove();
						
						for(int i=0; i<graph.get(val).size(); i++) {
							if(graph.get(val).get(i) != INF) {
								cola.add((T) graph.get(val).get(i));
							}
						}
					}else {
						cola.remove();
					}
			 }
					 
		}
		return resultado;
	}

	@Override
	public Vector dijkstra(Object vertex) {
		// TODO Auto-generated method stub
		//Pair <Double, T > --> Distancia y el padre
		Vector < Pair <Double, T > > resultado = new Vector < Pair <Double, T > >();
		// Pair <Double, Integer > --> Distancia y el vertice al que hay esa distancia
		PriorityQueue < Pair <Double, Integer > > cola = new PriorityQueue < Pair <Double, Integer > > ();
		int val = 0;
		
		for(int i=0; i < graph.size(); i++) {
			resultado.add(new Pair(INF,null));
		}
		
		if(hasVertex(vertex)) {
			val = id.get(vertex);
			resultado.set(val, new Pair(0, vertex));
			
			cola.add(new Pair(0,vertex));
			
			while(!cola.isEmpty()) {
				
				Pair <Double, Integer > aux = cola.remove();
				
				if(aux.getFirst() < resultado.get(aux.getSecond()).getFirst()) {
					resultado.set(aux.getSecond(), new Pair(aux.getFirst(),aux.getSecond()));
					
					for(int i=0; i<graph.size(); i++) {
						if(graph.get(aux.getSecond()).get(i) != INF) {
							cola.add(new Pair( resultado.get(aux.getSecond()).getFirst() + graph.get(aux.getSecond()).get(i), i ));
						}
						
					}
				
				}
				
			}
		}
		
		
		return resultado;
	}

	@Override
	public Vector< Vector<Double> > floydWarshall() {
		Vector< Vector<Double> >dist = new Vector<Vector<Double>>();
		for(int i = 0; i<graph.size(); ++i) {
			Vector<Double> d = new Vector<>();
			for(int j = 0; j<graph.get(i).size(); j++) {
				d.add(graph.get(i).get(j));
			}
			dist.add(d);
		}
  
        for (int k = 0; k < graph.size(); k++) 
        { 
            for (int i = 0; i < graph.size(); i++) 
            { 
                for (int j = 0; j < graph.size(); j++) 
                { 
                    if (dist.get(i).get(k) + dist.get(k).get(j) < dist.get(i).get(j)) 
                        dist.get(i).set(j, dist.get(i).get(k) + dist.get(k).get(j)); 
                } 
            } 
        } 
  
        // Print the shortest distance matrix
        return dist;
	}
	
	public Vector< Vector<Double> > floydWarshallByMultiplication() {
		Vector< Vector<Double> >dist = new Vector<Vector<Double>>();
		for(int i = 0; i<graph.size(); ++i) {
			Vector<Double> d = new Vector<>();
			for(int j = 0; j<graph.get(i).size(); j++) {
				d.add(graph.get(i).get(j));
			}
			dist.add(d);
		}
  
        for (int k = 0; k < graph.size(); k++) 
        { 
            for (int i = 0; i < graph.size(); i++) 
            { 
                for (int j = 0; j < graph.size(); j++) 
                { 
                    if (dist.get(i).get(k) * dist.get(k).get(j) > dist.get(i).get(j)) 
                        dist.get(i).set(j, dist.get(i).get(k) * dist.get(k).get(j)); 
                } 
            } 
        } 
  
        // Print the shortest distance matrix
        return dist;
	}

	@Override
	public Vector kruskall() {
		// TODO Auto-generated method stub
		Vector< Pair<Double, Pair<Integer, Integer> > > lista = new Vector<Pair<Double, Pair<Integer, Integer> > >();
		Double mst = 0.0;
		Vector< Boolean > arbol = new Vector<Boolean>();
		Vector < Pair < T, T> > resultado = new Vector< Pair<T,T> >();
		
		for(int i=0; i<graph.size(); i++) {
			for(int j=0; j<graph.size(); j++) {
				if(graph.get(i).get(j) != INF) {
					lista.add(new Pair(graph.get(i).get(j), new Pair(i,j)));
				}
			}
		}
		
		Collections.sort(lista, new PairDoublePairIntegerIntegerComparator());
		
		for(int i=0; i<graph.size(); i++) {
			arbol.add(false);
		}
		
		
		
		for(int k=0; k<lista.size();k++) {
			Pair< Double , Pair <Integer, Integer >> pareja = lista.get(k);
			
			if(arbol.get(pareja.getSecond().getFirst()) == false && 
					arbol.get(pareja.getSecond().getSecond()) == false) {
				resultado.add(new Pair(di.get(pareja.getSecond().getFirst()),
						di.get(pareja.getSecond().getSecond())));
				arbol.set(pareja.getSecond().getFirst(), true);
				arbol.set(pareja.getSecond().getSecond(), true);
			} else if((arbol.get(pareja.getSecond().getFirst()) == true && 
					arbol.get(pareja.getSecond().getSecond()) == true)) {
				//Do nothing
			} else {
				if(arbol.get(pareja.getSecond().getFirst()) == false) {
					resultado.add(new Pair(di.get(pareja.getSecond().getFirst()),
							di.get(pareja.getSecond().getSecond())));
					arbol.set(pareja.getSecond().getFirst(), true);
				} else {
					resultado.add(new Pair(di.get(pareja.getSecond().getFirst()),
							di.get(pareja.getSecond().getSecond())));
					arbol.set(pareja.getSecond().getSecond(), true);
				}
			}
		}
		
		return resultado;
	}

	@Override
	public Vector prim() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getVertexPosition(Object value) {
		if(id.containsKey(value)== false) {
			return -1;
		}
		return id.get(value);
	}

	@Override
	public Pair<Boolean, Vector<T>> bellmanFord(Object src) {
		return null;
	}

}

class PairDoublePairIntegerIntegerComparator implements Comparator<Pair<Double, Pair<Integer, Integer> > > 
{ 

    public int compare(Pair<Double, Pair<Integer, Integer> > a, Pair<Double, Pair<Integer, Integer> > b) 
    { 
    	if(a.getFirst() == b.getFirst()) {
    		if(a.getSecond().getFirst() == b.getSecond().getFirst() ) {
    			return a.getSecond().getSecond().compareTo(b.getSecond().getSecond());
    		} else {
    			return a.getSecond().getFirst().compareTo(b.getSecond().getFirst());
    		}
    	} else {
    		return a.getFirst().compareTo(b.getFirst()); 
    	}
    }
        
}