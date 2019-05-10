import java.util.Vector;

import Grafo.Grafo;
import Grafo.GrafoMatrizAdyacenciaImplementacion;
import Utils.Pair;

public class Main {

	public static void main(String[] args) {
		tigreBurroPasto();
		
		verticeQuePuedaLlegarALosDemas();
		
		cambioDeMonedas();
		
	}

	static void tigreBurroPasto() {
		//Problema 1: Tigre Burro Paja
		//Se puede realizar como un grafo cuyos nodos sean todas las posibles combinaciones
		//de seres vivos y cuyos caminos sean los que se
		//pueden tomar para no infringir las combinaciones en las cuales
		//los unos se comerian con los otros y encontrando el camino utilizando
		//el algoritmo de Dijkstra
		
		Grafo<String> g = new GrafoMatrizAdyacenciaImplementacion<String>();
		g.addVertex("");
		g.addVertex("T");
		g.addVertex("B");
		g.addVertex("P");
		g.addVertex("TB");
		g.addVertex("TP");
		g.addVertex("BP");
		g.addVertex("TBP");
		
		g.addEdge("", "B", 1.0);
		g.addEdge("B", "", 1.0);
		
		g.addEdge("B", "TB", 1.0);
		g.addEdge("TB", "B", 1.0);
		
		g.addEdge("B", "BP", 1.0);
		g.addEdge("BP", "B", 1.0);
		
		g.addEdge("TB", "T", 1.0);
		g.addEdge("T", "TB", 1.0);
		
		g.addEdge("B", "TP", 1.0);
		g.addEdge("TP", "B", 1.0);
		
		g.addEdge("BP", "P", 1.0);
		g.addEdge("P", "BP", 1.0);
		
		g.addEdge("P", "TP", 1.0);
		g.addEdge("TP", "P", 1.0);
		
		g.addEdge("TP", "T", 1.0);
		g.addEdge("T", "TP", 1.0);
		
		g.addEdge("TP", "TBP", 1.0);
		g.addEdge("TBP", "TP", 1.0);
		
		
		Vector<Pair<String, Double>> res = g.dijkstra("TBP");
		int pos = ((GrafoMatrizAdyacenciaImplementacion<String>) g).getVertexPosition(res.get(res.size()-1).getFirst());
		while(pos != ((GrafoMatrizAdyacenciaImplementacion<String>) g).getVertexPosition(res.get(pos).getFirst())){
			System.out.println(res.get(pos).getFirst());
			pos = ((GrafoMatrizAdyacenciaImplementacion<String>) g).getVertexPosition(res.get(pos).getFirst());
		}
		return;
	}
	
	static void verticeQuePuedaLlegarALosDemas() {
		//Para este problema simplemente se puede realizar
		//la construccion del grafo y utilizar cualquier algoritmo de recorrido
		//sobre el mismo como DFS o BFS y si el algoritmo recorrre todos los vertices
		//del grafo entonces el mismo puede llegar a todos los nodos
		
		Grafo<Integer> g = new GrafoMatrizAdyacenciaImplementacion<Integer>();
		g.addVertex(0);
		g.addVertex(1);
		g.addVertex(2);
		g.addVertex(3);
		g.addVertex(4);
		
		g.addEdge(0, 1, 1.0);
		g.addEdge(0, 2, 1.0);
		g.addEdge(1, 2, 1.0);
		g.addEdge(2, 4, 1.0);
		g.addEdge(1, 3, 1.0);
		
		Vector<Integer> recorrido1 = g.bfs(0);
		if(recorrido1.size() == 5) {
			System.out.println("0 si llega a todos los nodos");
		} else {
			System.out.println("0 no llega a todos los nodos");
		}
		
		Vector<Integer> recorrido2 = g.bfs(3);
		if(recorrido2.size() == 5) {
			System.out.println("3 si llega a todos los nodos");
		} else {
			System.out.println("3 no llega a todos los nodos");
		}
	}

	static void cambioDeMonedas() {
		//Para el cambio de monedas se puede construir una matriz como un grafo
		//En donde cada posicion de la matriz representa la tasa de cambio entre
		//la moneda i y la moneda j
		//Para encontrar la tasa de cambio mas optima entre una moneda y otra se puede
		//hallar el camino mas corto utilizando el algoritmo de Floyd Warshall pero
		//en vez de realizar una suma del valor de las monedas, se multiplican las tasas
		//de cambio
		
		Grafo<String> g = new GrafoMatrizAdyacenciaImplementacion<String>();
		
		g.addVertex("dolar");
		g.addVertex("peso");
		g.addVertex("libra");
		
		g.addEdge("dolar", "dolar", 1.0);
		g.addEdge("peso", "peso", 1.0);
		g.addEdge("libra", "libra", 1.0);
		
		g.addEdge("dolar", "peso", 0.2);
		g.addEdge("peso", "dolar", 5.0);
		
		g.addEdge("dolar", "libra", 2.0);
		g.addEdge("libra", "dolar", 0.5);
		
		g.addEdge("libra", "peso", 0.1);
		g.addEdge("peso", "libra", 10.0);
		
		Vector<Vector<Double> > tasasDeCambio = ((GrafoMatrizAdyacenciaImplementacion<String>) g).floydWarshallByMultiplication();
		//El vector de tasas de cambio contiene la tasa mas optima de una moneda a otra
		
		
		
	}
}
