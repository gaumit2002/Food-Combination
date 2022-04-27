
import java.util.*;


public class Knapsack{
	//this class represents one possible list of food items for a single hamper along with its sum of difference
	private ArrayList<Food> possibles = new ArrayList<Food>(); 
	private int sod;
	
	public Knapsack(ArrayList<Food> foods, int sumODiff){
		this.possibles = foods;
		this.sod = sumODiff;
	}
	//Getters
	public ArrayList<Food> getPossibles(){
		return this.possibles;
	}
	public int getSOD(){
		return this.sod;
	}
	
}