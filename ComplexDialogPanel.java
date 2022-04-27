

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;


// This class is strictly used for the GUI, it displays a second frame prompting
// for user input which is stored in Order.java 
public class ComplexDialogPanel extends JPanel {
   public static final String[] LABEL_TEXTS = {"Adult Males", "Adult Females", "Kids over 8 years-old", "Kids NOT over 8 years-old"};
   public static final int COLS = 8;
   private Map<String, JTextField> labelFieldMap = new HashMap<>();
   private static int[] hamperInfo = new int[4]; 
   private static boolean hamperStatus;

//Constructor for this class which aids in setting up the frame 
   public ComplexDialogPanel() {
      setLayout(new GridBagLayout());
      for (int i = 0; i < LABEL_TEXTS.length; i++) {
         String labelTxt = LABEL_TEXTS[i];
         add(new JLabel(labelTxt), createGbc(0, i));

         JTextField textField = new JTextField(COLS);
         labelFieldMap.put(labelTxt, textField);
         add(textField, createGbc(1, i));
      }

      setBorder(BorderFactory.createTitledBorder("Enter Hamper Information"));
   }

// Returns the string input given by the user into the textbox which correlates with 
// the specific lable from "labelText"
   public String getText(String labelText) {
      JTextField textField = labelFieldMap.get(labelText);
      if (textField != null) {
         return textField.getText();
      } else {
         throw new IllegalArgumentException(labelText);
      }
   }

// Creates a GridBagConstraints object which then has its default values altered
// to help format the layout
   public static GridBagConstraints createGbc(int x, int y) {
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridx = x;
      gbc.gridy = y;
      gbc.weightx = 1.0;
      gbc.weighty = gbc.weightx;
      if (x == 0) {
         gbc.anchor = GridBagConstraints.LINE_START;
         gbc.fill = GridBagConstraints.BOTH;
         gbc.insets = new Insets(3, 3, 3, 39);
      } else {
         gbc.anchor = GridBagConstraints.LINE_END;
         gbc.fill = GridBagConstraints.HORIZONTAL;
         gbc.insets = new Insets(3, 3, 3, 3);
      }
      return gbc;
   }

// This creates the second GUI frame which prompts the user for input then stores
// it in Order.java
   public static void createAndShowGui() {
		ComplexDialogPanel mainPanel = new ComplexDialogPanel();

		int optionType = JOptionPane.DEFAULT_OPTION;
		int messageType = JOptionPane.PLAIN_MESSAGE;
		Icon icon = null;
	  //options inside hamper adder
		String[] options = { "Add Hamper", "Go Back" };
      //initial value = "submit"
		Object initialValue = options[0];
		int reply = JOptionPane.showOptionDialog(null, mainPanel,
			"Adding A New Hamper To The Existing Order", optionType, messageType, icon, options,
			initialValue);
		for (int i = 0; i < 4; i ++){
			if(mainPanel.getText(LABEL_TEXTS[i]).length() == 0){
				JOptionPane.showMessageDialog(null, "An entry field was left blank\nPlease fill in each box with a single numeric value\n(i.e. 0, 1, ..., 9)");
				hamperStatus = false;
				return;
			}
		}
		if (reply == 0) {
			for (int i = 0; i < 4; i ++){
				hamperInfo[i] = Integer.valueOf(mainPanel.getText(LABEL_TEXTS[i]).charAt(0)) - 48; 
			}
		}
		if(hamperInfo[0] == 0 && hamperInfo[1] == 0 && hamperInfo[2] == 0 && hamperInfo[3] == 0 ){
			JOptionPane.showMessageDialog(null, "The Hamper you attempted to add was empty.");
			hamperStatus = false;
			return;
		}
		for(int i = 0; i < 4; i++){
			if(validateInput(mainPanel.getText(LABEL_TEXTS[i])) == false){
				JOptionPane.showMessageDialog(null, "Input for "+'"'+LABEL_TEXTS[i]
				+'"'+"was Invalid. \nEnsure the entry is a positive, single-digit numeric value (i.e. 0, 1, ..., 9)");			
				return;
			}			
		}
   }
 
// This validates the input, ensuring it meets the intended specifications for
// this program
// The input has a length no greater than 1 (ensuring it's > 0), and the input contains only an integer value
// Otherwise, no hamper is added
   public static boolean validateInput(String input){
	   if(input.length() >= 2){
		   hamperStatus = false;
		   return false;
	   }
	   try{
		   Integer.parseInt(input); 
	   }catch(NumberFormatException e){
		   hamperStatus = false;
		   return false;
	   }
	   
	   hamperStatus = true;
	   return true;
   }
   
  //Getters to return and help store the input information in Order.java in order
  //to then create a ne wHampers object
   public static int[] getHamperInfo(){
	   return hamperInfo;
   }
   public static boolean getHamperStatus(){
	   return hamperStatus;
   }

}