

// class to store information for each item of food in the inventory database
public class Food {
    private String NAME;
    private int GRAINCONTENT;
    private int FVCONTENT;
    private int PROCONTENT;
    private int OTHERCONTENT;
    

// Food() Constructor & Getters for each datafield

	public String getName(){
        return this.NAME;
    }
    public int getGrainContent(){
        return this.GRAINCONTENT;
    }
    public int getFVContent(){
        return this.FVCONTENT;
    }
    public int getProContent(){
        return this.PROCONTENT;
    }
    public int getOtherContent(){
        return this.OTHERCONTENT;
    }
	
    public Food(String foodName, int grains, int fv, int pro, int other){
		this.NAME = foodName;
		this.GRAINCONTENT = grains;
		this.FVCONTENT = fv;
		this.PROCONTENT = pro;
		this.OTHERCONTENT = other;
    }
}