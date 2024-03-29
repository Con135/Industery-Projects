
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise31_20 extends Application {   
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {   
    TabPane tabPane = new TabPane();
    Tab tab1 = new Tab("Line");
    StackPane pane1 = new StackPane();
    pane1.getChildren().add(new Line(10, 10, 80, 80));
    tab1.setContent(pane1);
    Tab tab2 = new Tab("Rectangle");
    tab2.setContent(new Rectangle(10, 10, 200, 200));
    Tab tab3 = new Tab("Circle");
    tab3.setContent(new Circle(50, 50, 20));    
    Tab tab4 = new Tab("Ellipse");
    tab4.setContent(new Ellipse(10, 10, 100, 80));
    tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);
    BorderPane borderPane = new BorderPane();
    
    HBox paneForRadioButtons = new HBox(20);
    paneForRadioButtons.setPadding(new Insets(5, 5, 5, 5));
    RadioButton rbTop = new RadioButton("Top");
    RadioButton rbLeft = new RadioButton("Left");
    RadioButton rbBottom = new RadioButton("Bottom");
    RadioButton rbRight = new RadioButton("Right");
    paneForRadioButtons.getChildren().addAll(rbTop, rbLeft, rbBottom, rbRight);
    borderPane.setBottom(paneForRadioButtons);
    pane1.getChildren().add(borderPane);
    
    ToggleGroup toggleGroup = new ToggleGroup();
    rbTop.setToggleGroup(toggleGroup);
    rbLeft.setToggleGroup(toggleGroup);
    rbBottom.setToggleGroup(toggleGroup);
    rbRight.setToggleGroup(toggleGroup);
    
    rbTop.setOnAction(e -> {
    	if (rbTop.isSelected()) {
    		tabPane.setSide(Side.TOP);
    	}
    });
    
    rbLeft.setOnAction(e -> {
    	if (rbLeft.isSelected()) {
    		tabPane.setSide(Side.LEFT);
    	}
    });
    
    rbBottom.setOnAction(e -> {
    	if (rbBottom.isSelected()) {
    		tabPane.setSide(Side.BOTTOM);
    	}
    });
    
    rbRight.setOnAction(e -> {
    	if (rbRight.isSelected()) {
    		tabPane.setSide(Side.RIGHT);
    	}
    });
    
    Scene scene = new Scene(tabPane, 300, 250);  
    primaryStage.setTitle("DisplayFigure"); // Set the window title
    primaryStage.setScene(scene); // Place the scene in the window
    primaryStage.show(); // Display the window
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   * line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}