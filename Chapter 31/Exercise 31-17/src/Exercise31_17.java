import java.io.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise31_17 extends Application{
	// Text fields
	private TextField tfInvestmentAmount = new TextField();
	private TextField tfNumOfYears = new TextField();
	private TextField tfAnnualInterestRate = new TextField();
	private TextField tfFutureValue = new TextField();
	private Button btCalculate= new Button("Calculate");
	  
	  @Override // Override the start method in the Application class
	  public void start(Stage primaryStage) throws IOException {
	   
		MenuBar menuBar = new MenuBar();  
		
		Menu menuOperation = new Menu("Operation");
		menuBar.getMenus().addAll(menuOperation);
		
		MenuItem menuItemCalculate = new MenuItem("Calculate");
		MenuItem menuItemExit = new MenuItem("Exit");
		menuOperation.getItems().addAll(menuItemCalculate, menuItemExit);
		
	    GridPane gridPane = new GridPane();
	    gridPane.add(new Label("Investment Amount:"), 0, 1);
	    gridPane.add(new Label("Number of Years:"), 0, 2);
	    gridPane.add(new Label("Annual Interest Rate:"), 0, 3);
	    gridPane.add(new Label("Future Value:"), 0, 4);
	    gridPane.add(tfInvestmentAmount, 1, 1);
	    gridPane.add(tfNumOfYears, 1, 2);
	    gridPane.add(tfAnnualInterestRate, 1, 3);
	    gridPane.add(tfFutureValue, 1, 4);
	    gridPane.add(menuBar, 0, 0);
	    gridPane.add(btCalculate, 0, 5);
	    
	    tfInvestmentAmount.setAlignment(Pos.BASELINE_RIGHT);
	    tfNumOfYears.setAlignment(Pos.BASELINE_RIGHT);
	    tfAnnualInterestRate.setAlignment(Pos.BASELINE_RIGHT);
	    tfFutureValue.setAlignment(Pos.BASELINE_RIGHT);
	    
	    tfInvestmentAmount.setPrefColumnCount(5);
	    tfNumOfYears.setPrefColumnCount(5);
	    tfAnnualInterestRate.setPrefColumnCount(5);
	    tfFutureValue.setPrefColumnCount(5);
	            
	    BorderPane pane = new BorderPane();
	    pane.setTop(gridPane);
	    
	    // Create a scene and place it in the stage
	    Scene scene = new Scene(pane, 200, 175);
	    primaryStage.setTitle("Exercise31_17"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	  
	    //Handle menu actions
	    menuItemCalculate.setOnAction(e -> {
			  //Get InvestmentAmount from text field
			  double investmentAmount = Double.parseDouble(tfInvestmentAmount.getText().trim());
			  
			  //Get NumberOfYears from text field
			  double numOfYears = Double.parseDouble(tfNumOfYears.getText().trim());
			  
			  //Get AnnualInterestRate from text field
			  double annualInterestRate = Double.parseDouble(tfAnnualInterestRate.getText().trim());
			  
			  double futureAmount = calculateFutureAmount(investmentAmount, numOfYears, annualInterestRate);
			  
			  //Shows futureAmount in tfFutureValue
			  tfFutureValue.setText(String.format("$%.2f", futureAmount));
		});
	    
	    menuItemExit.setOnAction(e -> {
	    	System.exit(0);
	    });
	    
	    btCalculate.setOnAction(e -> {
	    	//Get InvestmentAmount from text field
	    	double investmentAmount = Double.parseDouble(tfInvestmentAmount.getText().trim());
		  
	    	//Get NumberOfYears from text field
	    	double numOfYears = Double.parseDouble(tfNumOfYears.getText().trim());
		  
	    	//Get AnnualInterestRate from text field
	    	double annualInterestRate = Double.parseDouble(tfAnnualInterestRate.getText().trim());
		  
	    	double futureAmount = calculateFutureAmount(investmentAmount, numOfYears, annualInterestRate);
		  
	    	//Shows futureAmount in tfFutureValue
	    	tfFutureValue.setText(String.format("$%.2f", futureAmount));
	    });
	  
	 }
	  public double calculateFutureAmount(double investmentAmount, double numOfYears, double annualInterestRate) {
		  //Calculate monthlyInterestRate
		  double monthlyInterestRate = annualInterestRate / 1200;
		  
		  return investmentAmount * (Math.pow((1 + monthlyInterestRate), (numOfYears * 12)));
	  }
	  
	  /**
	   * The main method is only needed for the IDE with limited
	   * JavaFX support. Not needed for running from the command line.
	   */
	  public static void main(String[] args) {
	    launch(args);
	  }
}