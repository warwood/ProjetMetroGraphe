
public class Edge {

	private Vertex vertex1, vertex2; 
	private int value;
	/** Si isOriented = true alors orient�, alors le vertex1 est source et le vertex2 est la destination**/
	private boolean isOriented;
	
	// Constructeur r�utilisable dans d'autres cas (pas dans le projet)
	public Edge(Vertex vertex1, Vertex vertex2, int value, boolean isOriented) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;	
		this.value = value;
		this.isOriented = isOriented;
	}
	
	/** Constructeur � utiliser pour le m�tro
	 * 	this.value = 0 : represente un changement de ligne dans une m�me station
	 *  this.value = 1 : reprente un tron�on entre deux stations diff�rentes **/
	public Edge(Vertex vertex1, Vertex vertex2, boolean isOriented) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		if(vertex1.getName().equals(vertex2.getName()))
				this.value = 0;
		else 
				this.value = 1;
		this.isOriented = isOriented;
	}
	
	// Constructeur pour le r�seau social
	public Edge(Vertex vertex1, Vertex vertex2) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.value = 1;
		this.isOriented = false;
	}
	
	public Vertex getSrc() {
		return this.vertex1;
	}
	
	public Vertex getDest() {
		return this.vertex2;
	}
	
	public boolean getIsOriented() {
		return this.isOriented;
	}
	
	public int getValue() {
		return this.value;
	}
	
}
