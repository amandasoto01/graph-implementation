package Grafo;

import java.util.Vector;

import Utils.Pair;

public abstract class Grafo<T> {
	//edge = arista 
	public abstract void addVertex(T vertex);
	public abstract void addEdge(T origin, T destination, Double weight);
	public abstract boolean hasVertex( T vertex);
	public abstract boolean hasEdge(T origin, T destination);
	
	public abstract Vector<T> dfs (T vertex); //pila
	public abstract Vector<T> bfs (T vertex); //cola
	public abstract Vector< Pair<T, Double> > dijkstra (T vertex); // un nodo a muchos ~ cola de prioridad
	public abstract Vector< Vector<Double> > floydWarshall (); // de todos a todos
	public abstract Vector< T > kruskall (); //arbol de expansion minima - algoritmo voraz 
	public abstract Vector< T > prim (); //arbol de expansion minima - programacion dinamica 
}
