package perceptron;

public class Image {

	private String p1;
	private String comment;
	private int width;
	private int height;
	private boolean[][] data;	
	
	public Image(String p1, String comment, int width, int height,
			boolean[][] data) {
		this.p1 = p1;
		this.comment = comment;
		this.width = width;
		this.height = height;
		this.data = data;
	}	
	
	public void showVals(){
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				System.out.print(data[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	public boolean[][] getData(){
		return data;
	}
	
	public int getValue(){
		if (comment.equals("#Yes")){
			return 1; 
		}
		else{
			return 0;
		}
	}
}
