

import java.util.*;

public class Hampers{
	private int MALE;
	private int FEMALE;
	private int KIDSOVER;
	private int KIDSUNDER;
	private static MealPlan nutrition;
	private String FINALORDER;

// Constructor for Hampers(), Hampers acts as a sort-of root for the beginning of the order creation
	public Hampers(int male, int female, int kidO, int kidU){
		this.nutrition = new MealPlan(male, female, kidO, kidU);
		
		this.MALE = male;
		this.FEMALE = female;
		this.KIDSOVER = kidO;
		this.KIDSUNDER = kidU;
		try{
			this.FINALORDER = makeMyOrder(this.nutrition.possibleKnapsacks());
		}catch(HamperException e){
			this.FINALORDER = "Not able to meet minimum nutrition requirments for this hamper.";
		}
	}
	public static String makeMyOrder(ArrayList<Knapsack> tmp){
		Knapsack best = tmp.get(1);
		String order = "";
		for(Knapsack x : tmp){
			if(best.getSOD() > x.getSOD()){
				best = x;
			}
		}
		
		for(Food items : best.getPossibles()){
			Fridge deleteFridge = nutrition.getFridge();
			deleteFridge.deleteFromInventory(items.getName());
			order += items.getName();
			order += "\n";
		}
		order+="\n";
		
		return order;
	}
	
	
// Getters for the Hampers class
	public String returnOrder(){
		return this.FINALORDER;
	}
	public MealPlan getMealPlan(){
		return this.nutrition;
	}	
	public int getMale(){
		return this.MALE;
	}
	public int getFemale(){
		return this.FEMALE;
	}
	public int getKidsOver(){
		return this.KIDSOVER;
	}
	public int getKidsUnder(){
		return this.KIDSUNDER;
	}
}