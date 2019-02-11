/** @Author William Turner
  * Code for TootAndOtto game
 */
  
import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;

public class TootAndOtto extends Application {
  
  // the height of the grid
  static int gridheight = 6;
  // the length of the grid
  static int gridwidth = 6;
  // an array of all the buttons on the grid
  public Button[][] gridarray = new Button[gridwidth][gridheight];
  // the width of the screen
  static double screenwidth = 400;
  // the height of the screeen
  static double screenheight = 400;
  // a boolean to determine who's turn it is
  public boolean playerturn = true;
  
  public TootAndOtto() { 
  }
  
  public static void main(String[] args) {
    
    if (args.length != 0) {
    gridwidth = Integer.parseInt(args[0]);
    gridheight = Integer.parseInt(args[1]);
    }
      
    launch(args); 
  }
  
  /** Sets up the board for the game
    * @param stage the stage for the GUI
   */ 
  public void start(Stage stage) {
    
    stage.setTitle("Toot and Otto");
    GridPane gridpane = new GridPane();
    gridpane.setPrefSize(screenwidth, screenheight);
    for(int i = 0; i < gridwidth; i++) {
      
      for(int j = 0; j < gridheight; j++) {
        
        Button button = new Button();
        button.setOnAction(new ButtonHandler());
        button.setPrefSize(screenwidth/gridwidth, screenheight/gridheight);
        gridpane.add(button, i, j);
        gridarray[i][j] = button;
      }
    }
    
    Scene scene = new Scene(gridpane, screenwidth, screenheight);
    stage.setScene(scene);
    stage.show();   
  }
  /** class containing code to execute on button click
    * implements EventHandler
   */
  
  class ButtonHandler implements EventHandler<ActionEvent> {
   
    @Override
    public void handle(ActionEvent e) {
     
      Object button = e.getSource();
      
      if(button instanceof Button) {
        
        columnSetter((Button)button);
        playerturn = !playerturn;
        checkForWin();
      }
    }
  }
  
   /** sets the text in a column to the appropriate value if a button in that column has been clicked
    * @param b the button to check the column of
   */
  public void columnSetter(Button b) {
    
    int c = 0;
    
    // a double for loop running through the array to find the button that was clicked, to set the variable c to
     
    for(int i = 0; i < gridwidth; i++) {
      
      for(int j = 0; j< gridheight; j++) {
        
        if(gridarray[i][j] == b)
          c = i;
      }
    }
    
    // for loop to run through the column and set the lowest unnocupied button to the appropriate text
     
    for(int i = gridheight-1; i >= 0; i--) {
      
      if(gridarray[c][i].getText() == "") {
        
        if (playerturn) {
          gridarray[c][i].setText("T");
          i = 0;
        }
        else {
          gridarray[c][i].setText("O");
          i = 0;
        }
      }
    }
  }
  
  /** checks to see if a button is within range that it could be the start of a TOOT or OTTO
    * @return <code>true<code> if it can be,
    *         <code>false<code> if not
   */
  
  public boolean checkForPossible(int i, int j) {
    
   if (i+4 >= gridwidth || j+4 >= gridheight)
     return false;
   else
     return true;
  }
  
  /** checks to see if a point is the start of a TOOT or OTTO and sets the text to red if it is
   */
  
  public void checkPoint(int i, int j) {
    
    if (i+3 < gridwidth) {
      
      if (gridarray[i][j].getText().equals("T") && gridarray[i+1][j].getText().equals("O") &&
          gridarray[i+2][j].getText().equals("O") && gridarray[i+3][j].getText().equals("T")) {
        
        gridarray[i][j].setTextFill(Color.RED);
        gridarray[i+1][j].setTextFill(Color.RED);
        gridarray[i+2][j].setTextFill(Color.RED);
        gridarray[i+3][j].setTextFill(Color.RED);   
      }
      
      if (gridarray[i][j].getText().equals("O") && gridarray[i+1][j].getText().equals("T") &&
          gridarray[i+2][j].getText().equals("T") && gridarray[i+3][j].getText().equals("O")) {
        
        gridarray[i][j].setTextFill(Color.RED);
        gridarray[i+1][j].setTextFill(Color.RED);
        gridarray[i+2][j].setTextFill(Color.RED);
        gridarray[i+3][j].setTextFill(Color.RED);
      }
    }
    if(j+3 < gridheight) {
      if (gridarray[i][j].getText().equals("O") && gridarray[i][j+1].getText().equals("T") &&
          gridarray[i][j+2].getText().equals("T") && gridarray[i][j+3].getText().equals("O")) {
     
        gridarray[i][j].setTextFill(Color.RED);
        gridarray[i][j+1].setTextFill(Color.RED);
        gridarray[i][j+2].setTextFill(Color.RED);
        gridarray[i][j+3].setTextFill(Color.RED);
      }
    
      if (gridarray[i][j].getText().equals("T") && gridarray[i][j+1].getText().equals("O") &&
          gridarray[i][j+2].getText().equals("O") && gridarray[i][j+3].getText().equals("T")) {
     
        gridarray[i][j].setTextFill(Color.RED);
        gridarray[i][j+1].setTextFill(Color.RED);
        gridarray[i][j+2].setTextFill(Color.RED);
        gridarray[i][j+3].setTextFill(Color.RED); 
      }
    }
    if (i+3 < gridwidth && j+3 < gridheight) {
      if (gridarray[i][j].getText().equals("O") && gridarray[i+1][j+1].getText().equals("T") &&
          gridarray[i+2][j+2].getText().equals("T") && gridarray[i+3][j+3].getText().equals("O")) {
     
        gridarray[i][j].setTextFill(Color.RED);
        gridarray[i+1][j+1].setTextFill(Color.RED);
        gridarray[i+2][j+2].setTextFill(Color.RED);
        gridarray[i+3][j+3].setTextFill(Color.RED);
     }
    
      if (gridarray[i][j].getText().equals("T") && gridarray[i+1][j+1].getText().equals("O") &&
          gridarray[i+2][j+2].getText().equals("O") && gridarray[i+3][j+3].getText().equals("T")) {
     
        gridarray[i][j].setTextFill(Color.RED);
        gridarray[i+1][j+1].setTextFill(Color.RED);
        gridarray[i+2][j+2].setTextFill(Color.RED);
        gridarray[i+3][j+3].setTextFill(Color.RED);
      }
    }
  }
  
  /** calls the checkPoint method on all the buttons
   */
  
  public void checkForWin() {
	  
	/* Goal: run through entire grid and check each point for win condition
	 * Iteration subgoal: check spot at (i, j) for win condition*/
    
    for(int i = 0; i < gridwidth; i++) {
      
      for(int j = 0; j< gridheight; j++) {
     
        checkPoint(i,j);    
      }
    }
  }
}