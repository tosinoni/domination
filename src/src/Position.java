package src;

public class Position {
	private int i;
	private int j;
	
	public Position(int i, int j) {
		this.i= i;
		this.j = j;
	}
	
	
	public int getI() {
		return i;
	}


	public void setI(int i) {
		this.i = i;
	}


	public int getJ() {
		return j;
	}


	public void setJ(int j) {
		this.j = j;
	}
	

	 public boolean equals(Object obj) {
	    	if (obj == this)
				return true;
			
			if (obj == null || obj.getClass() != this.getClass())
				return false;
			
			Position position = (Position) obj;
			
			return position.i == i && position.j == j;
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
