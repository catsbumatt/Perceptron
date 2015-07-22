package perceptron;

import java.util.ArrayList;
import java.util.List;
/**
 * Perceptron class that does all the perceptron work
 * @author Matt Catsburg
 *
 */
public class Perceptron {

	private List<Feature> features = new ArrayList<Feature>();
	private List<Double> weights = new ArrayList<Double>();
	private int featWeightSize = 50;
	private List<Image> images;
	
	// contains classes learned from the perceptron algorithm	
	private List<Integer> learnedClasses = new ArrayList<Integer>();

	public Perceptron(List<Image> images) {
		this.images = images;
		features.add(new Dummy()); 
		for (int i = 0; i < featWeightSize - 1; i++) {
			features.add(new Feature());
		}
		// create threshold
		weights.add((double) Math.round(-Math.random()*10));
		// Initialize weights with random numbers
		for (int i = 0; i < featWeightSize - 1; i++) {			
			weights.add((double) Math.round(Math.random()*10));
		}
		for (int i = 0; i < 100; i++){
			learnedClasses.add(0);
		}
		runPerceptron();
		
		
	}

	/**
	 * performs the perception algorithm, adjusting weights each iteration
	 */
	public void runPerceptron() {

		double accuracy = 0;
		int count = 0;
		System.out.println("starting perceptron algorithm");	
		// until the perceptron is always right(or some limit)
		while (accuracy != 1) {
			if (count >= 100){break;}
			//System.out.println("Iteration " + (count + 1));			
			for (int i = 0; i < images.size(); i++){
				// present an example
				double sum = 0; // (50, i=0)SUM weight i * feat i
				for (int j = 0; j < featWeightSize; j++){
					sum = sum + weights.get(j) * featValue(images.get(i), features.get(j));
				}
				int learnedClass;
				if (sum > 0){
					learnedClass = 1;
				}else{
					learnedClass = 0;
				}
				learnedClasses.set(i, learnedClass);
				// if correct
				if (learnedClass == images.get(i).getValue()){					
					// do nothing
				}
				// the prediction is wrong
				// if the result is negative
				else if (learnedClass == 1){
					// adjust weights to less
					for (int j = 0; j < featWeightSize; j++){
						// feat Vector consists of all features, adjusting all weights to feature vals
						weights.set(j, weights.get(j) - featValue(images.get(i), features.get(j)));
					}
				}
				else{
					for (int j = 0; j < featWeightSize; j++){
						// feat Vector consists of all features, adjusting all weights to feature vals
						weights.set(j, weights.get(j) + featValue(images.get(i), features.get(j)));
					}
				}				
			}		
			
			accuracy = checkAccuracy();
			System.out.println("Iteration " + count + ". Accuracy of: " + accuracy);
			count++;
		}
		
		System.out.println();
		for (int i = 0; i < 100; i++) {
			System.out.print(images.get(i).getValue());
		}
		System.out.print("---Img Vals");
		System.out.println();
		
		for (int i = 0; i < 100; i++) {
			System.out.print(learnedClasses.get(i));
		}
		System.out.print("---LearnedVals");
		System.out.println();
		
		int featCount = 1;
		for (Double weight: weights){
			System.out.println("Weight on Feature " + featCount + ": " + weight);
			featCount++;
		}
		System.out.println();
		System.out.println("--------Features still classified incorrectly:");
		for (int i = 0; i < 100; i++){
			if (learnedClasses.get(i) != images.get(i).getValue()){
				System.out.println("   " + (i+1));
			}
		}
	}
	
	/**
	 * Returns the value of the feature given an image
	 * @param img
	 * @param feat
	 * @return
	 */
	public int featValue(Image img, Feature feat){
		if (feat instanceof Dummy){
			return 1;
		}
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			if (img.getData()[feat.getRow()[i]][feat.getCol()[i]] == feat.getSgn()[i]){
				sum++;
			}
		}
		return sum>2 ?1:0;
	}

	
	/**
	 * Compares the classes learned from the perceptron with the real image values
	 * @return accuracy
	 */
	public double checkAccuracy() {
		// compare the learned values with the actual image values
		double correct = 0.0;
		for (int i = 0; i < 100; i++){
			if (learnedClasses.get(i) == images.get(i).getValue()){
				correct++;
			}
		}
		return correct/100;
	}
	

}
