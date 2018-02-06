import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import sun.audio.*;
import java.io.*;

/** Start-up menu that displays instructions and asks the user how many
  * frogs desired */

public class Menu extends JFrame {
 
////////////////////////////// fields /////////////////////////////////
  
  private JLabel labelInputFrog;
  private JTextField inputFrogAmount;
  private JButton startButton;
  
  private JLabel rules1;
  private JLabel rules2;
  
//////////////////////////// constructors /////////////////////////////

  public Menu() {
    createComponents();
    setParameters();
  }
  
  public Menu(boolean determinant) {
    if (determinant) {
      createComponents2();
      setParameters();
    }
  }

  ///////////////////////////// methods ///////////////////////////////
  
  // sets the parameters for the menu JPanel
  public void setParameters() {
    setSize(600,200);
    setResizable(true);
    setLayout(new FlowLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    setTitle("Frog Puzzle");
    // center JFrame
    setLocationRelativeTo(null);
    
  }
  
  // creates the components of the JPanel
  public void createComponents() {
    /*       Window Layout        */
    /*JLabel*/
    labelInputFrog = new JLabel();
    labelInputFrog.setText(String.format("Enter number of frogs you wish to be in the puzzle:"));
    
    rules1 = new JLabel();
    rules1.setText(String.format("Maneuver the frogs on the right to the left and vice versa."));
    rules2 = new JLabel();
    rules2.setText(String.format("----------Input must be an integer, 1 to 12----------"));
    
    /*JTextField*/
    final int FIELD_WIDTH = 10;
    inputFrogAmount = new JTextField(FIELD_WIDTH);
   
    
    ActionListener actionListener = new ActionListener(){
      public void actionPerformed(ActionEvent event){
        int numOfFrogs = Integer.parseInt(inputFrogAmount.getText());
        if(numOfFrogs <= 12 && numOfFrogs > 0){
          new UserInterface(numOfFrogs);
          
          /*------ Background Music ------*/
          AudioPlayer MGP = AudioPlayer.player;
          AudioStream music;
          AudioData MD;
          ContinuousAudioDataStream loop = null;
    
          try{
            //InputStream test = new FileInputStream("welcome.wav");
            //AudioPLayer.player.start(music);
            music = new AudioStream(new FileInputStream("welcomeorig.wav"));
            MD = music.getData();
            loop = new ContinuousAudioDataStream(MD);
          }catch(IOException error){System.out.println("File Not Found");}
    
          MGP.start(loop);
          /*------------------------------*/

          
          dispose();
        } else {
          rules2.setText(String.format("THE INPUT MUST BE AN INTEGER, FROM THE NUMBERS 1 TO 12."));
        }/*max frogs is 12, must be greater than 0*/
        
        
      }
    };
    
    /*JButton*/
    startButton = new JButton("Start Puzzle");
    getRootPane().setDefaultButton(startButton);
    startButton.addActionListener(actionListener);
    
    
    
    /*Panel for the Label, Text-Field, and Button*/
    JPanel balancePanel = new JPanel();
    balancePanel.add(labelInputFrog);
    balancePanel.add(inputFrogAmount);
    balancePanel.add(startButton);
    
    JPanel rulesPanel1 = new JPanel();
    rulesPanel1.add(rules1);
    JPanel rulesPanel2 = new JPanel();
    rulesPanel2.add(rules2);
    
    
    /*Add Panels to Frame*/
    add(balancePanel);
    add(rulesPanel1);
    add(rulesPanel2);
    
  } /*END createComponents() method*/

  
  // creates the components of the JPanel WITHOUT background music
  public void createComponents2() {
    /*       Window Layout        */
    /*JLabel*/
    labelInputFrog = new JLabel();
    labelInputFrog.setText(String.format("Enter number of frogs you wish to be in the puzzle:"));
    
    rules1 = new JLabel();
    rules1.setText(String.format("Maneuver the frogs on the right to the left and vice versa."));
    rules2 = new JLabel();
    rules2.setText(String.format("----------Input must be an integer, 1 to 12----------"));
    
    /*JTextField*/
    final int FIELD_WIDTH = 10;
    inputFrogAmount = new JTextField(FIELD_WIDTH);
   
    
    ActionListener actionListener = new ActionListener(){
      public void actionPerformed(ActionEvent event){
        int numOfFrogs = Integer.parseInt(inputFrogAmount.getText());
        if(numOfFrogs <= 12 && numOfFrogs > 0){
          new UserInterface(numOfFrogs);  
          dispose();
        } else {
          rules2.setText(String.format("THE INPUT MUST BE AN INTEGER, FROM THE NUMBERS 1 TO 12."));
        }/*max frogs is 12, must be greater than 0*/
        
        
      }
    };
    
    /*JButton*/
    startButton = new JButton("Start Puzzle");
    getRootPane().setDefaultButton(startButton);
    startButton.addActionListener(actionListener);
    
    
    
    /*Panel for the Label, Text-Field, and Button*/
    JPanel balancePanel = new JPanel();
    balancePanel.add(labelInputFrog);
    balancePanel.add(inputFrogAmount);
    balancePanel.add(startButton);
    
    JPanel rulesPanel1 = new JPanel();
    rulesPanel1.add(rules1);
    JPanel rulesPanel2 = new JPanel();
    rulesPanel2.add(rules2);
    
    
    /*Add Panels to Frame*/
    add(balancePanel);
    add(rulesPanel1);
    add(rulesPanel2);
    
  }


}/*END Menu CLASS*/