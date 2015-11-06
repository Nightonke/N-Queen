package N_Queen;

public class Queen {

	private int row;
	private int col;

	public Queen(int r, int c){
		row = r;
		col = c;
	}

	public boolean canAttack(Queen q) {
		return row == q.getRow() ||
			   col == q.getCol() ||
			   Math.abs(col - q.getCol()) == Math.abs(row - q.getRow());
	}

	public void moveDown(int spaces) {
		row = (row + spaces) % Node.getSize();
	}

	public void setRow(int r){
		row = r;
	}

	public int getRow(){
		return row;
	}

	public void setCol(int c){
		col = c;
	}

	public int getCol(){
		return col;
	}
	
	public String toString(){
		return "(" + row + ", " + col + ")";
	}
}
