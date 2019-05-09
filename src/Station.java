
public class Station extends Vertex {
	
		private String line ;
		
		public Station(String name, String line) {
			super(name);
			this.line = line;
		}
		
		public String getLine() {
			return this.line;
		}
}
