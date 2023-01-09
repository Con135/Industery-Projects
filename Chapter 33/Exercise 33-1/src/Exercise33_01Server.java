// Exercise31_01Server.java: The server can communicate with
// multiple clients concurrently using the multiple threads
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Exercise33_01Server extends Application {
  // Text area for displaying contents
  private TextArea ta = new TextArea();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
   
    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 400, 200);
    primaryStage.setTitle("Exercise31_01Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
  new Thread(() -> {
	  try {
		  //Create a server socket
		  ServerSocket serverSocket = new ServerSocket(8000);
		  Platform.runLater(() ->
		  ta.appendText("Exercise31_01Server started at " + new Date() + '\n'));
  
		  //Listen for a connection request
		  Socket socket = serverSocket.accept();
  
		  //Create data input and output streams
		  DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
		  
		  DataOutputStream outputFromClient = new DataOutputStream(socket.getOutputStream());
  
		  while (true) {
			  //Receive annualInterestRate from the client
			  double annualInterestRate = inputFromClient.readDouble();

			  //Receive numOfYears from the client
			  int numOfYears = inputFromClient.readInt();

			  //Receive loanAmount from the client
			  double loanAmount = inputFromClient.readDouble();
			  
			  Loan loan = new Loan(annualInterestRate, numOfYears, loanAmount);
			  
			  
			  double monthlyPayment = loan.getMonthlyPayment();
			  double totalPayment = loan.getTotalPayment();
			  
			  //Send monthlyPayment back to client
			  outputFromClient.writeDouble(monthlyPayment);
	  
			  //Send totalPayment back to client
			  outputFromClient.writeDouble(totalPayment);
	  
			  Platform.runLater(() -> {
				  ta.appendText("annualInterestRate received from client: " + annualInterestRate + '\n');
				  ta.appendText("numOfYears received from client: " + numOfYears + '\n');
				  ta.appendText("loanAmount received from client: " + loanAmount + '\n');
		  
				  ta.appendText("monthlyPayment is: " + monthlyPayment + '\n');
				  ta.appendText("totalPayment is: " + totalPayment + '\n');
			  });
		  }
	  } 
	  catch(IOException ex) {
		  ex.printStackTrace();
	  }
  }).start();
  }
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
	  public static void main(String[] args) {
		  launch(args);
  }
}