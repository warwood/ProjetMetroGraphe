
public class Arc {
	
	private Vertex vertex1, vertex2; // vertex1 = source vertex2 = destination
	private int value;
	
	private Arc(Vertex vertex1, Vertex vertex2, int value) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;	
		this.value = value;
	}
	
	private Arc(Vertex vertex1, Vertex vertex2) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		if(vertex1.getName() == vertex2.getName()) this.value = 0;
		else this.value = 1;
	}
	
	private Vertex getVertex1() {
		return this.vertex1;
	}
	
	private Vertex getVertex2() {
		return this.vertex2;
	}
	
	
}
