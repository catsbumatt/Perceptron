package perceptron;

/**
 * Feature that is used as inputs in the perceptron algorithm
 * @author Matt Catsburg
 *
 */
public class Feature {

	protected int[] row = new int[4];
	protected int[] col = new int[4];
	protected boolean[] sgn = new boolean[4];
	
	
	public Feature(){
		for (int i = 0; i < 4; i++) {
			// randomly choose row and col
			row[i] = (int) (Math.random() * 10);
			col[i] = (int) (Math.random() * 10);
			// randomly choose + and - connections
			if (Math.random()>0.5){
				sgn[i] = true;
			}
			else{
				sgn[i] = false;
			}
		}
	}	
	
	public int[] getRow(){
		return row;
	}
	
	public int[] getCol(){
		return col;
	}
	
	public boolean[] getSgn(){
		return sgn;
	}
	
}
