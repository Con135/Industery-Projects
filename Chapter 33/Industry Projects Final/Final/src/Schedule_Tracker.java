import java.beans.EventHandler;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Schedule_Tracker extends Application  {
	@Override // Override the start method in the Application class
	  public void start(Stage primaryStage) {
		TableView<Event> tableView = new TableView<>();
		ObservableList<Event> dataSun = FXCollections.observableArrayList();
		ObservableList<Event> dataMon = FXCollections.observableArrayList();
		ObservableList<Event> dataTues = FXCollections.observableArrayList();
		ObservableList<Event> dataWed = FXCollections.observableArrayList();
		ObservableList<Event> dataThur = FXCollections.observableArrayList();
		ObservableList<Event> dataFri = FXCollections.observableArrayList();
		ObservableList<Event> dataSat = FXCollections.observableArrayList();
		
		//Create the Columns
		TableColumn eventNameColumn = new TableColumn("Event");
		eventNameColumn.setMinWidth(200);
		eventNameColumn.setCellValueFactory(
				new PropertyValueFactory<Event, String>("event"));
		
		TableColumn descriptionColumn = new TableColumn("Description");
		descriptionColumn.setMinWidth(200);
		descriptionColumn.setCellValueFactory(
				new PropertyValueFactory<Event, String>("description"));
		//Start Time
		TableColumn eventStartTimeColumn = new TableColumn("Start Time");
		eventStartTimeColumn.setMinWidth(200);
		eventStartTimeColumn.setCellValueFactory(
				new PropertyValueFactory<Event, String>("startTime"));
		
		TableColumn eventStartTimeAMPMColumn = new TableColumn("Start Time AM/PM");
		eventStartTimeAMPMColumn.setMinWidth(200);
		eventStartTimeAMPMColumn.setCellValueFactory(
				new PropertyValueFactory<Event, String>("startAMPM"));
		//End Time
		TableColumn eventEndTimeColumn = new TableColumn("End Time");
		eventEndTimeColumn.setMinWidth(200);
		eventEndTimeColumn.setCellValueFactory(
				new PropertyValueFactory<Event, String>("endTime"));
		
		TableColumn eventEndTimeAMPMColumn = new TableColumn("End Time AM/PM");
		eventEndTimeAMPMColumn.setMinWidth(200);
		eventEndTimeAMPMColumn.setCellValueFactory(
				new PropertyValueFactory<Event, String>("endAMPM"));
		
		tableView.getColumns().addAll(eventNameColumn, descriptionColumn, 
				eventStartTimeColumn, eventStartTimeAMPMColumn, eventEndTimeColumn, 
				eventEndTimeAMPMColumn);
		
		TabPane tabPane = new TabPane();
		tabPane.setTabMinWidth(155);
		Tab tabSun = new Tab("Sunday");
		Tab tabMon = new Tab("Monday");
		Tab tabTues = new Tab("Tuesday");
		Tab tabWed = new Tab("Wednesday");
		Tab tabThur = new Tab("Thursday");
		Tab tabFri = new Tab("Friday");
		Tab tabSat = new Tab("Saturday");
		tabPane.getTabs().addAll(tabSun, tabMon, tabTues, tabWed, tabThur, tabFri, tabSat);
		
		FlowPane eventFlowPane = new FlowPane(3, 3);
		TextField tfEventName = new TextField();
		TextField tfDescription = new TextField();
		TextField tfStartTime = new TextField();
		TextField tfEndTime = new TextField();
	    RadioButton rbStartAM = new RadioButton("AM");
	    RadioButton rbStartPM = new RadioButton("PM");
	    RadioButton rbEndAM = new RadioButton("AM");
	    RadioButton rbEndPM = new RadioButton("PM");
		Button btAddEvent = new Button("Add New Event");
		Button btDeleteEvent = new Button("Delete An Event");
		Button btConfirm = new Button("Confirm");
		Label labelEvent = new Label("Event: ");
		Label labelDescription = new Label("Description: ");
		Label labelStartTime = new Label("Start Time: ");
		Label labelEndTime = new Label("End Time: ");
		
		ToggleGroup startGroup = new ToggleGroup();
		rbStartAM.setToggleGroup(startGroup);
		rbStartPM.setToggleGroup(startGroup);
		
		ToggleGroup endGroup = new ToggleGroup();
		rbEndAM.setToggleGroup(endGroup);
		rbEndPM.setToggleGroup(endGroup);
		
		tfEventName.setPrefColumnCount(5);
		tfDescription.setPrefColumnCount(5);
		tfStartTime.setPrefColumnCount(5);
		tfEndTime.setPrefColumnCount(5);
		
		eventFlowPane.getChildren().addAll(btAddEvent, btDeleteEvent);
		
		//Add an Event
		btAddEvent.setOnAction(e -> {
			eventFlowPane.getChildren().addAll(labelEvent, tfEventName, 
					labelDescription, tfDescription, labelStartTime, tfStartTime, 
					rbStartAM, rbStartPM, labelEndTime, tfEndTime, rbEndAM, 
					rbEndPM, btConfirm);
		});
		//Delete an Event
		btDeleteEvent.setOnAction(e -> {
	    	Event selectedEvent = tableView.getSelectionModel().getSelectedItem();
	    	dataSun.remove(selectedEvent);
	    });
		//Confirm Adding the Event
		btConfirm.setOnAction(e -> {
			//Sets AM or PM
			String startAMPM = "N/A";
			String endAMPM = "N/A";
			if (rbStartAM.isSelected()) {
				startAMPM = "AM";
			}
			else if (rbStartPM.isSelected()) {
				startAMPM = "PM";
			}
			if (rbEndAM.isSelected()) {
				endAMPM = "AM";
			}
			else if (rbEndPM.isSelected()) {
				endAMPM = "PM";
			}
			//Adds the Events to the Day
			if (tabSun.isSelected()) {
				dataSun.add(new Event(tfEventName.getText(), tfDescription.getText(), 
				        tfStartTime.getText(), startAMPM, tfEndTime.getText(), endAMPM));
				tableView.setItems(dataSun);
			}
			else if (tabMon.isSelected()) {
				dataMon.add(new Event(tfEventName.getText(), tfDescription.getText(), 
				        tfStartTime.getText(), startAMPM, tfEndTime.getText(), endAMPM));
				tableView.setItems(dataMon);
			}
			else if (tabTues.isSelected()) {
				dataTues.add(new Event(tfEventName.getText(), tfDescription.getText(), 
				        tfStartTime.getText(), startAMPM, tfEndTime.getText(), endAMPM));
				tableView.setItems(dataTues);
			}
			else if (tabWed.isSelected()) {
				dataWed.add(new Event(tfEventName.getText(), tfDescription.getText(), 
				        tfStartTime.getText(), startAMPM, tfEndTime.getText(), endAMPM));
				tableView.setItems(dataWed);
			}
			else if (tabThur.isSelected()) {
				dataThur.add(new Event(tfEventName.getText(), tfDescription.getText(), 
				        tfStartTime.getText(), startAMPM, tfEndTime.getText(), endAMPM));
				tableView.setItems(dataThur);
			}
			else if (tabFri.isSelected()) {
				dataFri.add(new Event(tfEventName.getText(), tfDescription.getText(), 
				        tfStartTime.getText(), startAMPM, tfEndTime.getText(), endAMPM));
				tableView.setItems(dataFri);
			}
			else if (tabSat.isSelected()) {
				dataSat.add(new Event(tfEventName.getText(), tfDescription.getText(), 
				        tfStartTime.getText(), startAMPM, tfEndTime.getText(), endAMPM));
				tableView.setItems(dataSat);
			}
			//Reset the Textfields and Radiobuttons
			tfEventName.clear();
			tfDescription.clear();
			tfStartTime.clear();
			tfEndTime.clear();
			rbStartAM.setSelected(false);
			rbStartPM.setSelected(false);
			rbEndAM.setSelected(false);
			rbEndPM.setSelected(false);
			startAMPM = "N/A";
			endAMPM = "N/A";
			
			eventFlowPane.getChildren().removeAll(labelEvent, tfEventName, 
					labelDescription, tfDescription, labelStartTime, tfStartTime, 
					rbStartAM, rbStartPM, labelEndTime, tfEndTime, rbEndAM, 
					rbEndPM, btConfirm);
		});
		
		//Opens the Schedule for each Day
		tabSun.setOnSelectionChanged(e -> {
			if(tabSun.isSelected()) {
				tableView.setItems(dataSun);
			}
		});
		tabMon.setOnSelectionChanged(e -> {
			if(tabMon.isSelected()) {
				tableView.setItems(dataMon);
			}
		});
		tabTues.setOnSelectionChanged(e -> {
			if(tabTues.isSelected()) {
				tableView.setItems(dataTues);
			}
		});
		tabWed.setOnSelectionChanged(e -> {
			if(tabWed.isSelected()) {
				tableView.setItems(dataWed);
			}
		});
		tabThur.setOnSelectionChanged(e -> {
			if(tabThur.isSelected()) {
				tableView.setItems(dataThur);
			}
		});
		tabFri.setOnSelectionChanged(e -> {
			if(tabFri.isSelected()) {
				tableView.setItems(dataFri);
			}
		});
		tabSat.setOnSelectionChanged(e -> {
			if(tabSat.isSelected()) {
				tableView.setItems(dataSat);
			}
		});
		
		BorderPane pane = new BorderPane();
	    pane.setCenter(tableView);
	    pane.setBottom(eventFlowPane);
	    pane.setTop(tabPane);
	    Scene scene = new Scene(pane, 1200, 700);  
	    primaryStage.setTitle("Industry Projects Final"); // Set the window title
	    primaryStage.setScene(scene); // Place the scene in the window
	    primaryStage.show(); // Display the window
	}
	
	//Event Constructor
	public static class Event {
	    private final SimpleStringProperty event;
	    private final SimpleStringProperty description;
	    private final SimpleStringProperty startTime;
	    private final SimpleStringProperty startAMPM;
	    private final SimpleStringProperty endTime;
	    private final SimpleStringProperty endAMPM;

	    private Event(String event, String description, String startTime, 
	    		String startAMPM, String endTime, String endAMPM) {
	      this.event = new SimpleStringProperty(event);
	      this.description = new SimpleStringProperty(description);
	      this.startTime = new SimpleStringProperty(startTime);
	      this.startAMPM = new SimpleStringProperty(startAMPM);
	      this.endTime = new SimpleStringProperty(endTime);
	      this.endAMPM = new SimpleStringProperty(endAMPM);
	    }
	    //Event Name
	    public String getEvent() {
	      return event.get();
	    }

	    public void setEvent(String event) {
	      this.event.set(event);
	    }
	    //Event Description
	    public String getDescription() {
	      return description.get();
	    }

	    public void setDescription(String description) {
	      this.description.set(description);
	    }
	    //Start Time
	    public String getStartTime() {
	      return startTime.get();
	    }

	    public void setStartTime(String startTime) {
	      this.startTime.set(startTime);
	    }

		public String getStartAMPM() {
			  return startAMPM.get();
		}

		public void setStartAMPM(String startAMPM) {
			this.startAMPM.set(startAMPM);
		}
		//End Time
	    public String getEndTime() {
	      return endTime.get();
		}

		public void setEndTime(String endTime) {
		  this.endTime.set(endTime);
		}
		
		public String getEndAMPM() {
		  return endAMPM.get();
		}

		public void setEndAMPM(String endAMPM) {
		  this.endAMPM.set(endAMPM);
		}
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