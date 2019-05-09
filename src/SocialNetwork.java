import java.util.ArrayList;
import java.util.LinkedList;

public class SocialNetwork extends Graph{

	private ArrayList<Person> personList;
	private ArrayList<String> stringList;
	
	public SocialNetwork(ArrayList<String> vertexList) {
		int i = 0;
		String id;
		String start = "";
		String end = "";
		
		ArrayList<Edge> edgeList = new ArrayList<Edge>();
		super.edgeList = edgeList;

		ArrayList<Person> personList = new ArrayList<Person>();
		while (vertexList.get(i).charAt(0)!= '#') {
			id = vertexList.get(i);
			personList.add(new Person(id));
			i++;
		}
		this.personList = personList;
		//On cast en vertex le tableau de personne pour pouvoir utiliser les méthodes de graph
		super.vertexList = (ArrayList<Vertex>)((ArrayList<?>)personList);
		//On incrémente pour passer la ligne commencant par #
		i++;
		int j = 0;
		while (i < vertexList.size()) {
			start = vertexList.get(i).substring(0, vertexList.get(i).lastIndexOf(":"));
			end = vertexList.get(i).substring(vertexList.get(i).lastIndexOf(":") + 1); 
			this.edgeList.add(new Edge(findByName(start),findByName(end)));
			this.edgeList.get(j).getSrc().addNeighbour(this.edgeList.get(j));
			i++;
			j++;
		}
	}
	
	/**
	 * Méthode affichant un itinéraire d'un id de personne src à un id de personne dest.
	 * Procède à des vérifications sur l'existence de la personne, teste l'égalité entre la source et la destination.
	 * Lance Dijkstra sur la source, puis parcoure la liste chainée composée des étapes pour afficher  une série de
	 * A -> B 
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
			for (int i = 0; i < lv.size(); i++)
				if (i ==0 )
					System.out.printf("%s",lv.get(i).getName());
				else 
					System.out.printf(" -> %s", lv.get(i).getName());
			}
			System.out.printf("\n");
	}
	
	/**
	 * Méthode permettant de verifier la règle des six degrés de séparation.
	 * On lance Dijkstra sur chaque personne et on vérifie que la distance de toute personne à la source est bien inférieure ou égale à 7 (il y a donc au plus 6 personnes séparant la source et la destination)
	 * @return le booléen correspondant
	 */
	public Boolean sixDeg() {
		ArrayList<Person> unvisited = new ArrayList<Person>();
		unvisited.addAll(this.personList);
		for (Person p:unvisited) {
			Dijkstra(p.getName(),this.vertexList);
			for (Vertex v:this.vertexList) {
				if (v.getDist() > 7) {
					System.out.println("La règle des 6 degrès de séparation n'est pas verifiée.");
					System.out.printf("%s -> %s distance: %s \n", p.getName(), v.getName(), v.getDist());
					return false;
				}
			}
		}
		System.out.println("La règle des 6 degrès de séparation est verifiée");
		return true;
	}
	
}
