import javafx.application.Application;
import javafx.application.Platform;
import static javafx.application.Application.launch;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise33_09Client extends Application {
  DataOutputStream toServer = null;
  DataInputStream fromServer = null;
  private TextArea taServer = new TextArea();
  private TextArea taClient = new TextArea();
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    taServer.setWrapText(true);
    taClient.setWrapText(true);
    //taServer.setDisable(true);

    BorderPane pane1 = new BorderPane();
    pane1.setTop(new Label("History"));
    pane1.setCenter(new ScrollPane(taServer));
    BorderPane pane2 = new BorderPane();
    pane2.setTop(new Label("New Message"));
    pane2.setCenter(new ScrollPane(taClient));
    
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(pane1, pane2);

    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 200, 200);
    primaryStage.setTitle("Exercise31_09Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    // To complete later
    new Thread (() -> {
    	try {
    		//Create a server socket
    		ServerSocket serverSocket = new ServerSocket(8000);
    		//Open connection for a request
    		Socket socket = serverSocket.accept();
    		
    		//Create an input stream to receive data from the client
			DataInputStream fromServer = new DataInputStream(socket.getInputStream());
			
			//Create an output stream to send data to the client
			DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
  		
			while (true) {
	  			//Receives message from server
	  			String textMessage = fromServer.readUTF();
	  			toClient.writeUTF(textMessage);
	  			
	  			Platform.runLater(() -> {
	  				taServer.appendText(textMessage + "\n");
	  			});
	  		}
	  		}catch (Exception err) {
	  		err.printStackTrace();
	  	}
	  }).start();
	  
	  taClient.setOnKeyPressed(e -> {
		  
		  if (e.getCode() == KeyCode.ENTER) {
			  String textMessage = null;
			  textMessage = taClient.getText().trim();
			  if (toServer == null) {
				  try {
					  Socket socket = new Socket("localhost", 8000);
					  
					  fromServer = new DataInputStream(socket.getInputStream());
					  toServer = new DataOutputStream(socket.getOutputStream());
					  
					  InetAddress inetAddress = socket.getInetAddress();
				  }catch(Exception er) {
					  er.printStackTrace();
				  }
			  }
			  
			  try {
				  toServer.writeUTF(textMessage);
				  toServer.flush();
				  
			  }catch(Exception err) {
				  err.printStackTrace();
			  }
			  taClient.clear();
		  } 
	  });
	  }
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}