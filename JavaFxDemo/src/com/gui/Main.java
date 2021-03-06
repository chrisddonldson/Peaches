package com.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.io.IOException;

import Work.ProductDevTicketHandler;
import bodyMeasurement.BodyMeasureGUI;
import bodyMeasurement.bodyMeasurementHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
	BorderPane borderPane = new BorderPane();
	Stage window;

	public ScrollPane sp = new ScrollPane();

	public void setSp(FlowPane fp) {
		sp.setContent(fp);
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {

		window = primaryStage;
		window.setOnCloseRequest(e -> {
			e.consume();
			closeProgram();
		});
		window.setTitle("Peaches v0.1");
		HBox header = new HBox();
		VBox toolkitWork = new VBox();
		VBox toolkitPersonal = new VBox();

		// Build the ScrollPane

		PublicElements.workRoot.getChildren().addAll(new Label("This is the work home page"));
		VBox personalRoot = new VBox();
		personalRoot.getChildren().addAll(new Label("This is the personal home page"));

		sp.setContent(PublicElements.workRoot);
		borderPane.setCenter(sp);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);

		// Set up the personal toolkit
		Button foodStockButton = new Button("Food Stock");
		Button expenseButton = new Button("Expenses");
		Button foodDairyButton = new Button("Food Diary");
		Button exerciseJornalButton = new Button("Exercise");
		Button bodyMeasurementsButton = new Button("Body Meas");
		bodyMeasurementsButton.setOnAction(e -> {
			sp.setContent(BodyMeasureGUI.getBodyMeasureGUI());
		});
		Button skinCareButton = new Button("Skin Care");
		Button sleepJournalButton = new Button("Sleep Jornal");
		Button addressBookButton = new Button("Address Book");
		Button clothingButton = new Button("Clothing");
		toolkitPersonal.setSpacing(10);

		toolkitPersonal.getChildren().addAll(foodStockButton, expenseButton, foodDairyButton, exerciseJornalButton,
				bodyMeasurementsButton, skinCareButton, sleepJournalButton, addressBookButton, clothingButton);
		// Set up the work toolkit
		Button addTicket = new Button("New Ticket");
		addTicket.setOnAction(e -> {
			ProductDevTicketHandler.addNewTicket();
		});
		toolkitWork.getChildren().addAll(addTicket);
		toolkitWork.setSpacing(10);

		// Set up the header that controls the toolkit display.
		ProductDevTicketHandler productDevTicketHandler = new ProductDevTicketHandler();

		ProductDevTicketHandler.load();
		Button work = new Button("Work");
		work.setOnAction(e -> {
			borderPane.setLeft(toolkitWork);
			ProductDevTicketHandler.displayTickets();
			sp.setContent(PublicElements.workRoot);
		});
		Button personal = new Button("Personal");
		personal.setOnAction(e -> {
			borderPane.setLeft(toolkitPersonal);
			sp.setContent(personalRoot);
		});
		header.setAlignment(Pos.CENTER);
		header.setSpacing(10);
		header.getChildren().addAll(work, personal);
		// Node appContent = new AppContentNode();
		borderPane.setTop(header);
		// borderPane.setCenter(appContent);
		borderPane.setLeft(toolkitWork);

		Scene scene = new Scene(borderPane, 1000, 620);
		window.setScene(scene);
		window.show();

		// Handler testing
		bodyMeasurementHandler BMH = new bodyMeasurementHandler();
		BMH.build();
		BodyMeasureGUI BMGui = new BodyMeasureGUI();
		// BMH.load();
	}

	private void closeProgram() {
		System.out.println("closing program");
		Boolean answer = ConfirmBox.display("Are you sure?", "Are you sure you want to quit?");
		if (answer)
			window.close();
	}

}
