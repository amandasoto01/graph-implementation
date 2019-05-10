import java.util.Vector;

import Grafo.Grafo;
import Grafo.GrafoListaDeAristasImplementacion;
import Grafo.GrafoMatrizAdyacenciaImplementacion;
import Utils.Pair;

public class Main {

	public static void main(String[] args) {
		tigreBurroPasto();
		
		verticeQuePuedaLlegarALosDemas();
		
		cambioDeMonedas();
		
		gananciaCambioDeMonedas();
		
		enviarAguaSinRegar();
		
		arbolDeExpansionMinimaSoloConHojas();
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
		
		for(int i = 0; i<3; ++i) {
			int moneda = ((GrafoMatrizAdyacenciaImplementacion<String>) g).getVertexPosition(i);
			System.out.println("Para la moneda " + moneda + " las tasas de cambio mas optimas son:");
			for(int j = 0; j<3; ++j) {
				int m2 = ((GrafoMatrizAdyacenciaImplementacion<String>) g).getVertexPosition(j);
				System.out.println(" -> " + tasasDeCambio.get(i).get(j) + " con " + m2);
			}
		}
	}

	static void gananciaCambioDeMonedas() {
		//Utilizar el algoritmo de bellman ford sobre un grafo que
		//contenga las tasas de cambio de cada moneda con respecto a las otras
		//si se detecta un ciclo negativo entonces es que se peude 
		//tener ganancia sobre los cambios de moneda
		
		Grafo<String> g = new GrafoListaDeAristasImplementacion<String>();
		
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
		
		//Intentar para todas las monedas
		Pair<Boolean, Vector<String>> cycleCheck = g.bellmanFord("dolar");
		if(cycleCheck.getFirst() == false) {
			System.out.println("El dolar puede dar ganancias con el cambio");
		}
		
		cycleCheck = g.bellmanFord("peso");
		if(cycleCheck.getFirst() == false) {
			System.out.println("El peso puede dar ganancias con el cambio");
		}
		
		cycleCheck = g.bellmanFord("libra");
		if(cycleCheck.getFirst() == false) {
			System.out.println("La libra puede dar ganancias con el cambio");
		}
		
	
	}

	static void enviarAguaSinRegar() {
		//Este problema de grafos podria ser solucionado utilizando
		//un algoritmo de flujo maximo (también conocido como de corte minimo)
		//En donde se tienen tres nodos y las conexiones entre los mismos pueden ser
		//las capacidades de flujo que cada uno de ellos tiene
	}

	static void arbolDeExpansionMinimaSoloConHojas() {
		//Se debe armar el grafo
		//Posteriormente se puede realizar para cada nodo el arbol de expansion minima
		//y adicionalmente se puede realizar un algoritmo de dijkstra para calcular la 
		//distancia de cada nodo a todos los demas
		//si se obtiene un arbol de expansion minima que contenga todos los nodos y adicionalmente
		//la distancia mas corta de dicho nodo a los demas nodos es 1 entonces se cumple con 
		//el problema dado
	}
}
