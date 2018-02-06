import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

/** Frog-related methods and variables */

public class Frog {
  
  /////////////////////////// fields ////////////////////////////////
  
  private static int x;
  private static int frogID;
  public boolean frogSide;
  private static boolean currSide;
  private static final int FROG_LENGTH = 97;
  private static int[] locationData;
  private static List<Lilypad> lilypadList;
  
  /////////////////////////// constructors ////////////////////////////////
  
  public Frog(boolean frogSide) {
    this.frogSide = frogSide;
  }
  
  public Frog(int x, int[] frogLocationData, List<Lilypad> lilypadList, JFrame jf) {
    this.x = x;
    this.lilypadList = lilypadList;
    locationData = frogLocationData;
    currSide = determineFrog();
    jump();
    boolean decision = checkVictory();
    if (decision) { 
      
      
      
      ActionListener actionListener1 = new ActionListener(){
        public void actionPerformed(ActionEvent event){
          new Menu(true);
          jf.dispose();
        }
      };
      
      ActionListener actionListener2 = new ActionListener(){
        public void actionPerformed(ActionEvent event){
          System.exit(0);
        }
      };
      
      JButton yesButton;
      JButton noButton;
      JLabel victoryMessage = new JLabel();
      
      victoryMessage.setText(String.format("      Puzzle solved! Would you like to try another round?        "));
      yesButton = new JButton("Yes");
      noButton = new JButton("No");
      
      yesButton.addActionListener(actionListener1);
      noButton.addActionListener(actionListener2);
      
      JFrame frame = new JFrame();
      frame.setSize(400,150); 
      frame.setLocationRelativeTo(null);
      frame.setResizable(true); 
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      frame.setVisible(true);
      
      
      JPanel panel = new JPanel();
      panel.add(victoryMessage);
      panel.add(yesButton);
      panel.add(noButton);
      frame.add(panel);
    }
  }
  
  ////////////////////////////// methods ////////////////////////////////
  
  // method that checks if puzzle is solved
  public static boolean checkVictory() {
    int i = 0;
    while (i < lilypadList.size()) {
      if ((i != lilypadList.size()/2) && (!lilypadList.get(i).isEmpty())) {
        // check if the left side contains any true frogs
        if (i < lilypadList.size()/2) {
          if (((Frog)lilypadList.get(i).peek()).frogSide) {
            return false;
          }
          
        }
        else {
          // check if right side contains any false frogs
          if (!((Frog)lilypadList.get(i).peek()).frogSide) {
            return false;
          }
        }
      }
      i++;
    }
    // check if middle lilypad is empty. If empty, win.
    if (!lilypadList.get((lilypadList.size()-1)/2).isEmpty()) {
      return false;
    }
    return true;
  }
  
  // method that checks if a frog jump to a lilypad is plausible
  public static void jump() {
    if (!(lilypadList.get(frogID).isEmpty())) {
      if (currSide) {
        if ((frogID + 1 != lilypadList.size()) && (frogID != lilypadList.size() - 1)) {
          if (lilypadList.get(frogID + 1).isEmpty()) {
            lilypadList.get(frogID + 1).push(lilypadList.get(frogID).pop());

          }
          else if ((frogID + 2 != lilypadList.size()) && (lilypadList.get(frogID + 2).isEmpty())) {
            lilypadList.get(frogID + 2).push(lilypadList.get(frogID).pop());

          }
          else {

          }
        }
      }
      else {
        if (frogID >= 0) {
          if (lilypadList.get(frogID - 1).isEmpty()) {
            lilypadList.get(frogID - 1).push(lilypadList.get(frogID).pop());

          }
          else if (lilypadList.get(frogID - 2).isEmpty()) {
            lilypadList.get(frogID - 2).push(lilypadList.get(frogID).pop());
   
          }
          else {

          }
          
        }
      }
    }
  }
  
  // method that determines which frog was clicked by the player
  public static boolean determineFrog() {
    frogID = 0;
    for(int startX : locationData) {
      if ((x >= startX) && (x <= startX + FROG_LENGTH)) {
        break;
      }
      frogID++;
    }
    
    return (((Frog)(lilypadList.get(frogID).peek())).frogSide);
  }
  
  
  
  
}
