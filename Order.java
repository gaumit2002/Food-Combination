

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.util.*;
import java.util.Scanner;
import java.io.*;
// Order.java contains GUI functionality, as well as the base mainframe for the ordering platform

public class Order implements ActionListener, OrderOutput{
// A private variable giving access to ComplexDialogPanel which is a seperate file apart of the GUI
	private ComplexDialogPanel hamperPanel = new ComplexDialogPanel();
	
// Order()'s private variables
	private ArrayList<Hampers> myHampers = new ArrayList<>();
	private String hamperName;
	private int males;
	private int females;
	private int kidsO;
	private int kidsU;
	private String finalOrder;

// Constructor for Order() 
	public Order(){
	}


// The Main() function for the program, it implements some GUI functionalities alongside the code displayed in the

	public static void main(String[] args){
		
		EventQueue.invokeLater(() -> {
			Order buttonListener = new Order();
			
            JFrame frame = new JFrame("----   Food Bank Order Main Menu   ----");
            frame.setSize(550, 200);
			// make a button close it
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            JPanel buttonsPanel = new JPanel();
			buttonsPanel.setLayout(new BorderLayout());
			
			JLabel header = new JLabel("Please select an option from below: ");
			buttonsPanel.add(header, BorderLayout.PAGE_START);
			
	// Prompt User , get hamper info, creat&add hamper to arraylist for the order.. creating a hamper object should
	// fill out the order while checking to see if those items are available (final result for the request should be 
	// indicated folloing the "submit buttton")
			JButton addButton = new JButton( new AbstractAction("Add a new hamper to exisisting order") {
				@Override
				public void actionPerformed( ActionEvent e ) {
					buttonListener.hamperPanel = new ComplexDialogPanel();					
					buttonListener.hamperPanel.createAndShowGui();
					int listSize =  buttonListener.myHampers.size();
					int[] hamperInfo = buttonListener.hamperPanel.getHamperInfo();
					if(buttonListener.hamperPanel.getHamperStatus()){
						Hampers hamper = new Hampers(hamperInfo[0], hamperInfo[1], hamperInfo[2], hamperInfo[3]);
						buttonListener.myHampers.add(hamper);
						JOptionPane.showMessageDialog(null, "Another hamper has been added to your order.");
					}
				}
			});
			
	// basically prints to screen hampers and their order... but no actal ingredients
	// helps user check their order
			JButton viewButton = new JButton( new AbstractAction("View current hampers in the exisisting order") {
				@Override
				public void actionPerformed( ActionEvent e ) {
					JOptionPane.showMessageDialog(null, "Here is your current order.");
					String currentOrder = new String();
					int  hamperNumber = 1;
					for(Hampers hamper : buttonListener.myHampers){
						currentOrder += ("\nHamper "+hamperNumber+": \n");
						currentOrder += hamper.returnOrder();
						hamperNumber++;
					}
					System.out.println(currentOrder+"\n______END______");
				}
			});
			
	// Submission button - checks intent, fills out order form, clses JFrame... program ends
			JButton submitButton = new JButton( new AbstractAction("Submit Order") {
				@Override
				public void actionPerformed( ActionEvent e ) {
					String[] options = {"Return to Main Menu", "Yes"};
					Icon icon = null;
					int reply = JOptionPane.showOptionDialog(null, null,"Are You Sure?", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, icon, options,options[0]);
					
					if (reply == 1){
						int  hamperNumber = 1;
						String currentOrder = "\nOriginal Request:\n\n";
						for(Hampers hamper : buttonListener.myHampers){
							currentOrder += ("Hamper "+hamperNumber+": Adult Males: "+hamper.getMale()+"	Adult Females: "+hamper.getFemale()
							+"\nChild Over 8: "+hamper.getKidsOver()+"		Child Under 8: "+hamper.getKidsUnder()+System.lineSeparator());
							hamperNumber++;
						}
						hamperNumber = 1;
						for(Hampers hamper : buttonListener.myHampers){
							currentOrder += ("\nHamper "+hamperNumber+": "+System.lineSeparator());
							currentOrder += hamper.returnOrder();
							hamperNumber++;
						}
						buttonListener.finalOrder = currentOrder;
						
						try{
							buttonListener.printOrder();
						}catch(IOException err){
							System.out.println(err);
						}
						JOptionPane.showMessageDialog(null, "The order has been submitted.");
						frame.dispose();
					}
				}
			});

            
			viewButton.addActionListener(buttonListener);
			buttonsPanel.add(viewButton, BorderLayout.LINE_END);
            addButton.addActionListener(buttonListener);
			buttonsPanel.add(addButton, BorderLayout.LINE_START);
			submitButton.addActionListener(buttonListener);
			buttonsPanel.add(submitButton, BorderLayout.PAGE_END);
			
            frame.getContentPane().add(BorderLayout.CENTER, buttonsPanel);
            frame.setVisible(true);
        });
    }
	
	public void actionPerformed(ActionEvent event){
    }  

// Allows hampers to be added and returned as needed
	public void addHampers(int m, int f, int ko, int ku) throws IllegalArgumentException{
		if(m<0||f<0||ko<0||ku<0){
			throw new IllegalArgumentException();
		}
		Hampers hamp = new Hampers(m, f, ko, ku);
		this.myHampers.add(hamp);
	}
	
	public Hampers getHampers(int index){
		return this.myHampers.get(index);
	}
	
// prints order to the outut textfile named "orderform.txt"
// Loops through ArrayList of Hampers getting the finla orde from each
	public void printOrder() throws IOException{  


		FileWriter myWriter = new FileWriter("Orderform.txt");
		
		myWriter.write("----   Food Bank Order Form   ----"+System.lineSeparator()+System.lineSeparator());
		myWriter.write(this.finalOrder);
		
		myWriter.close();
	}
	
	//Setter function for testing purposes
	public void setFinalString(String str){
		this.finalOrder = str;
	}
	
}