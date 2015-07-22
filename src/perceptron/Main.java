package perceptron;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {	
		if (args.length != 1){
			throw new Error("Incorrect number of arguments");
		}
		try {
//			Scanner sc = new Scanner(new File("src/perceptron/image.data"));
			Scanner sc = new Scanner(new File(args[0]));
//			while(sc.hasNextLine()){
//				System.out.println(sc.nextLine());
//			}
			
			List<Image> images = new ArrayList<Image>();
			while(sc.hasNext()){
				String firstLine = sc.next();
				String comment = sc.next();
				int width = sc.nextInt();
				int height = sc.nextInt();
				boolean[][] vals = new boolean[width][height];
				String data = sc.next();
				data = data + sc.next();
				int sInd = 0;
				for (int i = 0; i < width; i++){
					for (int j = 0; j < height; j++){
						if (data.charAt(sInd) == '1'){
							vals[i][j] = true;
						}
						else{
							vals[i][j] = false;
						}
						sInd++;
						
					}
				}
				images.add(new Image(firstLine, comment, width, height, vals));
			}
				
			new Perceptron(images);
			
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		if (args.length != 1){
			//System.out.println("no arguments given, using defaults");
			
		}

	}

}
