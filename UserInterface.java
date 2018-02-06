import java.awt.*; 
import java.awt.event.*; 
import java.awt.image.*; 
import javax.swing.*; 
import java.io.*; 
import javax.imageio.*; 
import javax.swing.Timer;
import java.util.*;
import sun.audio.*;


/** Class that renders the game environment */

public class UserInterface
  extends JPanel {
  
  //////////////////////////////// fields //////////////////////////////////////
  
  // images
  private static BufferedImage background;
  private static BufferedImage frog;
  private static BufferedImage frogEnemy;
  private static BufferedImage lilypad;
  // quantities
  private static int numOfFrogs;
  private static int numOfLilypads;
  private static int lilypadLength; 
  // parameter constants
  private static final int BG_SIZE = 1020;
  private static final int FROG_LENGTH = 97;
  private static final int FROG_HEIGHT = 59;
  private static final int LILYPAD_HEIGHT = 58;
  private static final int START_Y_LILYPAD = 520;
  private static final int START_Y_FROG = 500;
  // data structures
  private static Queue<Integer> lilyLocationData;
  private static int[] frogLocationData;
  private static List<Lilypad> lilypadList;
  // Jframe
  private static JFrame jf;
  // Reset Button
  private JButton resetButton;
  
  ////////////////////////////// constructors ///////////////////////////////////

  public UserInterface(double frogCount) {
    // initialize images
    try{
      background = ImageIO.read(getClass().getResource("bg2.jpg"));
      frog = ImageIO.read(getClass().getResource("frog.png"));
      lilypad = ImageIO.read(getClass().getResource("lilypad3.png"));
      frogEnemy = ImageIO.read(getClass().getResource("frog2.png"));
    }catch (IOException e){e.printStackTrace();}
    
    // initialize quantities
    numOfFrogs = (int)(frogCount);
    numOfLilypads = numOfFrogs + 1;
    
    //initialize data structures
    lilyLocationData = new ArrayQueue<Integer>();
    frogLocationData = new int[numOfLilypads];
    lilypadList = new LinkedList<Lilypad>();
    for(int i = 0; i < numOfLilypads; i++) {
      lilypadList.add(new Lilypad<Frog>());
    }
    
    // create LinkedList of lilypad stacks.
    for (int i = 0; i != numOfLilypads; i++) {
      if (i != (numOfLilypads/2)) {
        if (i > (numOfLilypads/2)) {
          lilypadList.get(i).push(new Frog(false));
        }
        else {
          lilypadList.get(i).push(new Frog(true));
        }
      }
    }
    
    
    // Set JFrame parameters
    jf = new JFrame();
    jf.setSize(BG_SIZE,650); 
    jf.setLocationRelativeTo(null);
    jf.setResizable(true); 
    jf.setTitle("Frog Puzzle"); 
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    jf.setVisible(true);
    jf.add(this);
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if ((y >= 500) && (y <= 550)) {
          new Frog(x, frogLocationData, lilypadList, jf);
          repaint();

        }
        
      }
      
      public void mousePressed(MouseEvent e){}
      public void mouseEntered(MouseEvent e){}
      public void mouseExited(MouseEvent e){}
      public void mouseReleased(MouseEvent e){}
      
    });
    
    /* Reset Button ActionListener*/
  ActionListener actionListener = new ActionListener(){
      public void actionPerformed(ActionEvent event){
        refresh(frogCount);
        repaint();
      }
    };
  
    
    /*JButton*/
    resetButton = new JButton("Reset Puzzle");
    getRootPane().setDefaultButton(resetButton);
    resetButton.addActionListener(actionListener);
    
    /*Panel for the reset button*/
    JPanel panel = new JPanel();
    panel.add(resetButton);
    
    /*Add panel to Frame*/
    add(panel);
    
  }

  /////////////////////////////// methods ////////////////////////////////////
  
  // re-initialize variables for reset
  public static void refresh(double frogCount) {
   

    numOfFrogs = (int)(frogCount);
    numOfLilypads = numOfFrogs + 1;
    
    lilyLocationData = new ArrayQueue<Integer>();
    frogLocationData = new int[numOfLilypads];
    lilypadList = new LinkedList<Lilypad>();
    for(int i = 0; i < numOfLilypads; i++) {
      lilypadList.add(new Lilypad<Frog>());
    }
    
    for (int i = 0; i != numOfLilypads; i++) {
      if (i != (numOfLilypads/2)) {
        if (i > (numOfLilypads/2)) {
          lilypadList.get(i).push(new Frog(false));
        }
        else {
          lilypadList.get(i).push(new Frog(true));
        }
      }
    }
  }
  
  // method that paints the components into the JPanel
  public void paintComponent(Graphics g){ 
    super.paintComponent(g);
    g.drawImage(background, 0,0, getWidth(), getHeight(), null); 
    drawLilypads(g);
    drawFrogs(g);
    
    
  }/*END paint()*/
  
  // method that draws the lilypads (location depends on number of frogs and background size).
  public static void drawLilypads(Graphics g) { 
    int division = BG_SIZE/numOfLilypads;
    lilypadLength = BG_SIZE/(numOfLilypads + 1);
    int lilySpace = division - lilypadLength;
    int i = 0;
    int startX = lilySpace/2;
    while (i != numOfLilypads) {
      g.drawImage(lilypad, startX, START_Y_LILYPAD, lilypadLength, LILYPAD_HEIGHT, null);
      lilyLocationData.add(startX);
      startX += lilySpace + lilypadLength;
      i++;
    }
    
  }
  
  // method that draws the frogs (location depends on the lilypads' locations) and collects their location data
  public static void drawFrogs(Graphics g) {
    int i = 0;
    while (i != numOfLilypads) {
      int startX = (lilyLocationData.remove() + (lilypadLength/2)) - (FROG_LENGTH/2);
      if (!lilypadList.get(i).isEmpty()) {
        if (!((Frog)lilypadList.get(i).peek()).frogSide) {
          g.drawImage(frogEnemy, startX, START_Y_FROG, FROG_LENGTH, FROG_HEIGHT, null);
          frogLocationData[i] = startX;
          
        }
        else {
          g.drawImage(frog, startX, START_Y_FROG, FROG_LENGTH, FROG_HEIGHT, null);
          frogLocationData[i] = startX;
          
        }
      }
      else {
        frogLocationData[i] = startX;
      }
      i++;
      
    }
    
  }
  
  
}/*END UserInterface
 class*/