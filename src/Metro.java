import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Metro extends Graph {

	private ArrayList<Station> stationList;
	private ArrayList<String> stringList;

	
	public Metro(ArrayList<String> vertexList) {
		int i = 0;
		String start = "";
		String end = "";
		String line = "";
		
		ArrayList<Edge> edgeList = new ArrayList<Edge>();
		super.edgeList = edgeList;
		this.stringList = vertexList;
		i = this.fillVertexList(i);
		i++;
		int j = 0;
		// Boucle parcourant la deuxieme partie du fichier pour récupérér les connexions(arcs et aretes)
		while (i < vertexList.size()) {
			if (vertexList.get(i).charAt(0) != '%') {
				
				if (vertexList.get(i).contains(">")) {
					start = vertexList.get(i).substring(0, vertexList.get(i).lastIndexOf(">"));
					end = vertexList.get(i).substring(vertexList.get(i).lastIndexOf(">") + 1);
				}
				else {
					start = vertexList.get(i).substring(0, vertexList.get(i).lastIndexOf(":"));
					end = vertexList.get(i).substring(vertexList.get(i).lastIndexOf(":") + 1);
				}

				this.edgeList.add(new Edge(findByName(start, line), findByName(end, line), vertexList.get(i).contains(">")));
				this.edgeList.get(j).getSrc().addNeighbour(this.edgeList.get(j));
				if (!this.edgeList.get(j).getIsOriented())
					this.edgeList.get(j).getDest().addNeighbour(this.edgeList.get(j));
				j++;
			
			}
			if (vertexList.get(i).charAt(0) == '%' && vertexList.get(i).length() > 1 )
				line = vertexList.get(i).substring(vertexList.get(i).lastIndexOf("e") + 1).replaceAll(" ","").toLowerCase();
			i++;
		}
		//Ajout des arêtes matérialisant les changements
		for(j = 0;this.stationList.size() > j;j++) {	
			for(int k = 0;this.stationList.size() > k;k++) {
				if (this.stationList.get(k).getName().equals(this.stationList.get(j).getName()) && !this.stationList.get(k).getLine().equals(this.stationList.get(j).getLine())) {
					this.stationList.get(j).addNeighbour(new Edge(this.stationList.get(j), this.stationList.get(k),false));
				}
			}			
		}
		super.vertexList = (ArrayList<Vertex>)((ArrayList<?>)stationList); 	
	}
	
	/**
	 * Methode parcourant les lignes du fichier texte pour récupérer le numéro de ligne et le nom de la station
	 * @param : ArrayList composé de chaque ligne du fichier texte
	**/
	public  int fillVertexList(int i) {
		String line = "";
		String name;
		ArrayList<Station> stationList = new ArrayList<Station>();
		while (this.stringList.get(i).charAt(0)!= '#') {
			if (this.stringList.get(i).charAt(0) == '%') {
				if(this.stringList.get(i).length() > 5 ) {
					line = this.stringList.get(i).substring(this.stringList.get(i).lastIndexOf("e") + 1).replaceAll(" ","").toLowerCase();
				}	
			}
			else {
				name = this.stringList.get(i);
				if (!name.equals("Sens Ouest-Est"))
					stationList.add(new Station(name,line));				
			}
		i++;	
		}
		this.stationList = stationList;
		return i;
	}
	
	/**
	 * Méthode permettant de récuperer le vertex associé à un nom et une ligne spécifiques.
	 * @param name, line
	 * @return le vertex correspondant au nom en paramètre
	 */
	public Vertex findByName(String name, String line) {
		int i = 0;
		while (i < this.stationList.size()) {
			if (this.stationList.get(i).getName().equals(name) && this.stationList.get(i).getLine().replaceAll(" ","").equals(line))
				return stationList.get(i);
			i++;
		}
		return null;
		
	}
	
	/**
	 * Méthode affichant un itinéraire d'un nom de station src à un nom de station dest.
	 * Procède à des vérifications sur l'existence de la station, teste l'égalité entre la source et la destination.
	 * Lance Dijkstra sur la source, puis parcoure la liste chainée composée des étapes pour afficher  une série de
	 * A -> B (ligne x)
	 */
	public void Route(String src, String dest) {
		if (findByName(src) == null || findByName(dest) == null) {
			if (findByName(src) == null)
				System.out.println("La source mentionée n'existe pas.");
			if (findByName(dest) == null)
				System.out.println("La destination mentionée n'existe pas.");
		}
		else if (src.contentEquals(dest))
			System.out.println("La source n'est pas différente de la destination");
		else {
			Dijkstra(src, this.vertexList);
			LinkedList<Vertex> lv = new LinkedList<Vertex>();
			lv = getShortestPath(findByName(src), findByName(dest));
			int i = 0;
			while(lv.get(i).getName().equals(lv.get(i+1).getName()))
				i++;
			while (!lv.get(i).getName().equals(dest)) {
				String line = ((Station)lv.get(i)).getLine();
				String dep = lv.get(i).getName();
				while (((Station)lv.get(i)).getLine().equals(line) && !lv.get(i).getName().equals(dest)) 
					i++;
				System.out.printf("%s -> %s (ligne %s)\n",dep,lv.get(i).getName(), line);
					
			}
		}
	
	}
	

	  

		
}
