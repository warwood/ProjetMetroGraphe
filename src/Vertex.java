import java.util.ArrayList;

public abstract class Vertex {

	protected ArrayList<Edge> neighbours;
	protected String name;
	protected int dist;
	protected Vertex previous;
	
	public Vertex(String name) {
		this.name = name;
		this.neighbours = new ArrayList<Edge>();
		this.previous = null;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Edge> getNeighbours(){
		return this.neighbours;
	}
	
	public void addNeighbour(Edge edge) {
		this.neighbours.add(edge);
	}
	
	public int getDist() {
		return this.dist;
	}
	
	public void setDist(int nb) {
		this.dist = nb;
	}
	
	public void setPrevious(Vertex v) {
		this.previous = v;
	}
	
	public Vertex getPrevious() {
		return this.previous;
	}
}
