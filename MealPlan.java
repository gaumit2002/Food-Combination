
import java.util.*;

public class MealPlan{
	// required percentages of the total cals, data pulled from myFridge 
	private int reqGrains;
	private int reqFV;
	private int reqPro;
	private int reqOther;
	
	//required total cals
	private Fridge myFridge;
	
	
// Constructor uses getters to get daily values from fridge and multpily it by the number of individuals
// in each hamper
	public MealPlan(int male, int female, int kidO, int kidU){
		this.myFridge = new Fridge();
		
		this.reqGrains = male*myFridge.getAdultMale()[0] + female*myFridge.getAdultFemale()[0] + kidU*myFridge.getChildUnder()[0] + kidO*myFridge.getChildOver()[0];
		this.reqFV = male*myFridge.getAdultMale()[1] + female*myFridge.getAdultFemale()[1] + kidU*myFridge.getChildUnder()[1] + kidO*myFridge.getChildOver()[1];
		this.reqPro = male*myFridge.getAdultMale()[2]+ female*myFridge.getAdultFemale()[2] + kidU*myFridge.getChildUnder()[2] + kidO*myFridge.getChildOver()[2];
		this.reqOther = male*myFridge.getAdultMale()[3]+ female*myFridge.getAdultFemale()[3] + kidU*myFridge.getChildUnder()[3] + kidO*myFridge.getChildOver()[3];
		
	}
	
	public Fridge getFridge(){
		return this.myFridge;
	}
	public int getGrains(){
		return this.reqGrains;
	}
	public int getFV(){
		return this.reqFV;
	}
	public int getPro(){
		return this.reqPro;
	}
	public int getOther(){
		return this.reqOther;
	}

// this is our sorting algorithm. It basically finds all possible subsets of the inventory, the power set,
// which is done by calling the funciton powerSet() located below it. It stores each possible set of
// food items which match the requirments as a Knapsack object, alongside the sum of the differences
// which will allow the best option to be chosen by locating that ehich has the lowest sum of differences
	public ArrayList<Knapsack> possibleKnapsacks() throws HamperException {

		ArrayList<Food> z = this.myFridge.getStorage();
        int grain=0;
        int protein=0;
        int FV=0;
        int other=0;
        Food itemID;
        int sumOfDifferences=0;

        ArrayList<ArrayList<Food>> combinations=powerSet(z.subList(0, z.size()/2)); //input will come from Shaheriers Arraylist of Foods.
		ArrayList<Knapsack> possibleHamper = new ArrayList<Knapsack>();

        for(ArrayList<Food> x : combinations) {
            ArrayList<Food> ID = new ArrayList<Food>();
            for(int i=0 ; i<x.size() ; i++){
                // all these methods are from Food object.
                grain += x.get(i).getGrainContent();
                protein += x.get(i).getProContent();
                FV += x.get(i).getFVContent();
                other += x.get(i).getOtherContent();

                itemID = x.get(i);     // keeping track of the items in Knapsack.
                ID.add(itemID);
                
            }
            if(grain>=this.reqGrains && protein>=this.reqPro && FV>=this.reqFV && other>=this.reqOther){
                grain = grain-this.reqGrains;
                protein = protein-this.reqPro;
                FV = FV-this.reqFV;
                other = other-this.reqOther;
                sumOfDifferences = grain + protein + FV + other;
				
                Knapsack H= new Knapsack(ID,sumOfDifferences);     //creates a Knapsack object for possible hampers.
                possibleHamper.add(H);                       //puts the created Knapsack in the Hamper ArrayList to get the best hamper later.
            }
            grain=0;
            protein=0;
            FV=0;
            other=0;
            itemID= null;
            sumOfDifferences=0;    
        }
        if (!possibleHamper.isEmpty()){
            return possibleHamper;
        }
        else{ // if no possible combos to meet requirments
            throw new HamperException();
        }
		
		
		
		
		
    }
	
	public ArrayList<ArrayList<Food>> powerSet(List<Food> originalSet) {
        ArrayList<ArrayList<Food>> sets = new ArrayList<ArrayList<Food>>();
        if (originalSet.isEmpty()) {
            sets.add(new ArrayList<Food>());
            return sets;
        }
        List<Food> list = originalSet;
        Food head = list.get(0);
        ArrayList<Food> rest = new ArrayList<Food>(list.subList(1, list.size())); 
        for (ArrayList<Food> set : powerSet(rest)) {
            ArrayList<Food> newSet = new ArrayList<Food>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }       
        return sets;
    }

}