

import java.sql.*;
import java.util.*;
import javax.management.Query;
import javax.swing.text.html.StyleSheet;



public class Fridge {
    //index: 0=Grains, 1=FV, 2=Protein, 3=other
	private static int[] adultMale = new int[4];
	private static int[] adultFemale = new int[4];
	private static int[] childOver = new int[4];
	private static int[] childUnder = new int[4];
	private static ArrayList<Food> storage = new ArrayList<Food>();


// Storing a Connection and ResultSet variable to be accesible across functions in the class	
	private static Connection conn;
    private static ResultSet results;


// Constructor for Fridge()
// Sets the nutrient requirments for each type of client
    public Fridge() { // turn this into fridge()		
		
		StringBuffer names = new StringBuffer();
		try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/FOOD_INVENTORY", "student", "ensf");
            Statement myStmt = conn.createStatement();
            String x = "SELECT Client, WholeGrains, FruitVeggies, Protein, Other, Calories FROM DAILY_CLIENT_NEEDS";
            results = myStmt.executeQuery(x);
			
			while(results.next()){
				int totalCals = results.getInt("Calories");
				double g = results.getInt("WholeGrains")/100.0;
				double fv = results.getInt("FruitVeggies")/100.0;
				double p = results.getInt("Protein")/100.0;
				double o = results.getInt("Other")/100.0;
			// Simple if statements to ensure the right values go to the right array	
				if(results.getString("Client").equals("Adult Male")){
					adultMale[0] = (int)(totalCals*g);
					adultMale[1] = (int)(totalCals*fv);
					adultMale[2] = (int)(totalCals*p);
					adultMale[3] = (int)(totalCals*o);
				}
				if(results.getString("Client").equals("Adult Female")){
					adultFemale[0] = (int)(totalCals*g);
					adultFemale[1] = (int)(totalCals*fv);
					adultFemale[2] = (int)(totalCals*p);
					adultFemale[3] = (int)(totalCals*o);				}
				if(results.getString("Client").equals("Child over 8")){
					childOver[0] = (int)(totalCals*g);
					childOver[1] = (int)(totalCals*fv);
					childOver[2] = (int)(totalCals*p);
					childOver[3] = (int)(totalCals*o);
				}
				if(results.getString("Client").equals("Child under 8")){
					childUnder[0] = (int)(totalCals*g);
					childUnder[1] = (int)(totalCals*fv);
					childUnder[2] = (int)(totalCals*p);
					childUnder[3] = (int)(totalCals*o);
				}	
			}
            myStmt.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }  
		
		fillStorage();
    }


// Access database in order to fill an arraylist full of food objects so the information from the 
// inventory is more conveniently accesible inside the program
	public static void fillStorage(){
		try{
			Statement myStmt = conn.createStatement();
            String x = "SELECT Name, GrainContent, FVContent, ProContent, Other, Calories FROM AVAILABLE_FOOD";
            results = myStmt.executeQuery(x);
			
			while(results.next()){
				int totalCals = results.getInt("Calories");
				double g = results.getInt("GrainContent")/100.0;
				double fv = results.getInt("FVContent")/100.0;
				double p = results.getInt("ProContent")/100.0;
				double o = results.getInt("Other")/100.0;
				
				Food myFood = new Food(results.getString("Name"), (int)(totalCals*g), (int)(totalCals*fv), (int)(totalCals*p), (int)(totalCals*o));
				storage.add(myFood);
				
			}				
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
// To be called once an order form has been finalized, this function is called to update the inventory by 
// removing the passed item from the inventory
	public static void deleteFromInventory(String myFood){
		try{
			String query = "DELETE FROM AVAILABLE_FOOD WHERE name=?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, myFood);
            statement.executeUpdate();
            statement.close();
		}catch(SQLException e){
			e.printStackTrace();
		}	
	}

	
// Getters to return the int[] with the nutrient reqs for each type of client
	public int[] getAdultMale(){
		return this.adultMale;
	}
	public int[] getAdultFemale(){
		return this.adultFemale;
	}
	public int[] getChildOver(){
		return this.childOver;
	}
	public int[] getChildUnder(){
		return this.childUnder;
	}
	public ArrayList<Food> getStorage(){
		return this.storage;
	}
	

}