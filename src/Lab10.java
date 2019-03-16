import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Lab10 {
	public static void main(String[] args) throws FileNotFoundException {
//		double r = Cannon.GetMaxRange(degrees, velocity);
//		System.out.println(r);
//		ArrayList<Double> xt = Cannon.GetX();
//		ArrayList<Double> yt = Cannon.GetY();
//		System.out.println(xt.size());
//		System.out.println(yt.size());
//		CSV(xt, yt);
//		System.out.println("----------");
//		RMHC(10, 75000);
//		RMHC(100, 1);
//		RMHC(10, 75000);
//		RMHC(100, 95000);
		RMHC(100, 65000);	
		System.out.println("----------");
		
	}
    public static void CSV (ArrayList<Double> X, ArrayList<Double> Y) throws FileNotFoundException{

        FileWriter fileWriter = null;
        try {
        	fileWriter = new FileWriter("test.csv");
    		fileWriter.append("X Values");
    		fileWriter.append("Y Values");
    		fileWriter.append("\n");

        	for (int i = 0; i < X.size(); i++) {
        		fileWriter.append(Double.toString(X.get(i)));
        		fileWriter.append(",");
        		fileWriter.append(Double.toString(Y.get(i)));
        		fileWriter.append("\n");
        	}
        	
        }
        catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        }
        finally {
        	try {
        		fileWriter.flush();
        		fileWriter.close();
        	}
        	catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();

        	}
        }
    }
	public static ScalesSolution RMHC(int iter, int setting)
	{
		// iter = iterations 
		// setting = max or min (1 for max, 0 for min) any other number is to achieve closest distance
		ScalesSolution currentSol = new ScalesSolution(0.0,0.0);
		Double range = Cannon.GetMaxRange(currentSol.GetD(), currentSol.GetV());
		
		if (setting != 1 && setting != 0 ) {
			
			if (setting != 2) {
				System.out.println("TARGET distance:  "+ setting+"m");
				System.out.print("\t Initial solution: ");
				currentSol.print();
				System.out.println("\t Initial distance: " + Math.round(range) + "m \t" + Math.round(Math.abs(setting-range))+ "m off target!" );
			}

			
			for (int i = 0; i < iter ; i++) {
				ScalesSolution newSol = new ScalesSolution(currentSol.GetD(),currentSol.GetV());
				newSol.SmallChange();
				Double newRange = Cannon.GetMaxRange(newSol.GetD(), newSol.GetV());

				if (Math.abs(range-setting) > Math.abs(newRange-setting) ) {
					currentSol = newSol;
					range = newRange;
				}
//				Printing test solutions
				System.out.print("SOLUTION: " );
				newSol.print();
				System.out.println("("+ i + ") Best Distance: " + range);
				
			}
			
			
			System.out.print("\t Best solution:    ");
			currentSol.print();
			System.out.println("\t CLOSEST distance: " + Math.round(range) + "m \t" + Math.round(Math.abs(setting-range))+ "m off target! (in " + iter + " tries)" );
			System.out.println("----------");
			return currentSol;
		}
		
		
		if (setting == 1) {System.out.println("MAXIMUM distance ");}
		else if (setting == 0) {System.out.println("MINIMUM distance ");}
		System.out.print("\t Initial Solution: " );
		currentSol.print();
		System.out.println("\t Initial Distance: " + Math.round(range) + "m");
		

		for (int i = 0; i < iter ; i++) {
			ScalesSolution newSol = new ScalesSolution(currentSol.GetD(),currentSol.GetV());
			newSol.SmallChange();
			Double newRange = Cannon.GetMaxRange(newSol.GetD(), newSol.GetV());
			if (setting == 1) {
				if (range < newRange ) {
					currentSol = newSol;
					range = newRange;
				}
			}
			else if (setting == 0) {
				if (range > newRange ) {
					currentSol = newSol;
					range = newRange;
				}
			
			}
//				Printing test solutions
				System.out.print("SOLUTION: " );
				newSol.print();
				System.out.println("("+ i + ") Best Distance: " + range);
		}
		if (setting == 1) {
			System.out.print("\t MAXIMUM Solution: " );
			currentSol.print();
			System.out.println("\t MAXIMUM Distance: " + Math.round(range) + "m (in " + iter + " tries)");
			System.out.println("----------");
			return(currentSol);
		}
		else {
			System.out.print("\t MINIMUM Solution: " );
			currentSol.print();
			System.out.println("\t MINIMUM Distance: " + Math.round(range) + "m (in " + iter + " tries)");
			System.out.println("----------");
			return(currentSol);
		}


	}
}
