import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public abstract class Graph {
	
	protected ArrayList<Vertex> vertexList;
	protected ArrayList<Edge> edgeList;

	/**
	 * M�thode permettant de r�cuperer le vertex associ� � un nom sp�cifique.
	 * @param vertexList
	 * @param name
	 * @return le vertex correspondant au nom en param�tre
	 */
	public Vertex findByName(String name) {
		int i = 0;
		while (i < this.vertexList.size()) {
			if (this.vertexList.get(i).getName().equals(name))
				return vertexList.get(i);
			i++;
		}
		return null;
	}
	
	/**
	 * M�thode retournant l'entier correspondant au nombre de voisins d'une certain noeud.
	 * On le calcule en sommant le nombre de troncons (de valeur 1) appartenant � l'ensemble des voisins de chaque noeud
	 * ayant le m�me nom.
	 * @param name
	 * @return
	 */
	public int getNbOfNeighbours(String name) {
		int nb = 0;
		for (Vertex vt: this.vertexList) {
			if (vt.getName().equals(name)) {
				for (Edge neig: vt.getNeighbours()) {
					if (neig.getValue() != 0) {
						nb++;
					}
				}
			}
		}
		return nb;
	}
	
	/**
	 * M�thode retournant une table de hachage compos�e de cl�s: nom de sommet et de valeurs: nombre de voisins.
	 */
	// On ne peut pas utiliser un type primitif en tant qu'argument g�n�rique d'une hashmap
	public HashMap<String, Integer>  getTopNeighbours() {
		HashMap<String, Integer> topNeighboursVertex = new HashMap<String, Integer>();
		for (Vertex vt:this.vertexList) {
			if (!topNeighboursVertex.containsKey(vt.getName())) 
				topNeighboursVertex.put(vt.getName(), getNbOfNeighbours(vt.getName()));
			}
		return topNeighboursVertex;
	}
	
	/**
	 * M�thode d'affichage des premiers �l�ments de la table de hachage pass�e en param�tre.
	 */
	public void printTopTen() {
		HashMap<String, Integer> neighbourMap = new HashMap<String, Integer>();
		neighbourMap = this.getTopNeighbours();
	    Object[] neighbourArray = neighbourMap.entrySet().toArray();
	    Arrays.sort(neighbourArray, new Comparator() {
	        public int compare(Object o1, Object o2) {
	            return ((Map.Entry<String, Integer>) o2).getValue().compareTo(((Map.Entry<String, Integer>) o1).getValue());
	        }
	    });
		    for (int i = 0; i <= 9;i++) {
		        System.out.println(((Map.Entry<String, Integer>) neighbourArray[i]).getKey()+ " poss�de " + ((Map.Entry<String, Integer>) neighbourArray[i]).getValue() + " voisins.");
		    }
		}
	
	/**
	 * M�thode appliquant l'algorithme de Dijkstra calculant les distances minimales d'un sommet src � tous les autres sommets du graphe compos� de l'ensemble de sommet X
	 * @param src (nom du sommet de d�part)
	 * @param X (ensemble de sommet du graphe)
	 */
	public void Dijkstra(String src, ArrayList<Vertex> X) {
		Vertex source = findByName(src);
		if (source == null)
			System.out.println("La source sp�cifi�e n'existe pas.");
		
		ArrayList<Vertex> unvisited = new ArrayList<Vertex>();
		unvisited.addAll(X);
		for (Vertex v:unvisited)
			v.setDist(Integer.MAX_VALUE);
		source.setDist(0);
		while (!unvisited.isEmpty()) {
			Vertex current = getMinDistVertex(unvisited);
			unvisited.remove(current);
			updateDist(current,unvisited);
		}		
	}
	
	
	/**
	 * M�thode permettant de r�cuperer le sommet de distance mininmale dans une liste en param�tre.
	 * @param vl est une liste de sommets.
	 * @return le sommet de distance minimale.
	 */
	public Vertex getMinDistVertex(ArrayList<Vertex> vl) {
		Vertex minDistVertex = null;
		int minDist = Integer.MAX_VALUE;
		for (Vertex v:vl) {
			if (v.getDist() < minDist) {
				minDist = v.getDist();
				minDistVertex = v;
			}
		}
		return minDistVertex;
	}
	
	/**
	 * M�thode permettant de mettre � jour la distance des sommets qui sont suivants de current et ne sont pas d�j� visit�s
	 * @param current
	 * @param vl
	 */
	public void updateDist(Vertex current, ArrayList<Vertex> vl) {
		for (Edge i:current.getNeighbours()) {
			if (i.getSrc() == current) {
				if (vl.contains(i.getDest())) {
					if ((current.getDist() + i.getValue()) < i.getDest().getDist()) {
						i.getDest().setDist(current.getDist() + i.getValue());
						i.getDest().setPrevious(current);
					}
				}
			}
			else {
				if (!i.getIsOriented()) {
					if (vl.contains(i.getSrc())) {
						if (current.getDist() + i.getValue() < i.getSrc().getDist())
							i.getSrc().setDist(current.getDist() + i.getValue());
							i.getSrc().setPrevious(current);
					}
				}
			}
		}
	}
	
	/**
	 * M�thode renvoyant une liste chain�e compos�e des sommets composant le plus court chemin (dans l'ordre).
	 * @param src (sommet source)
	 * @param dest (sommet destination)
	 * @return
	 */
	public LinkedList<Vertex> getShortestPath(Vertex src, Vertex dest) {
		LinkedList<Vertex> shortestPath = new LinkedList<Vertex>();
		Vertex current = dest;
		shortestPath.add(current);
		while (current.getPrevious() != null) {
			current = current.getPrevious();
			shortestPath.add(current);
		}
		Collections.reverse(shortestPath);
		return shortestPath;
	}
	
	public abstract void Route(String src, String dest);

	
}
