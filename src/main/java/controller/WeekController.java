package controller;

/*-
 * #%L
 * calorieWork
 * %%
 * Copyright (C) 2018 Debreceni Egyetem Informatikai Kar
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 * #L%
 */
import dao.DataAccessObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.Food;
import model.Week;
import model.enumerators.EWeekDayTypes;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class WeekController implements Initializable
{
	@FXML
	private VBox vbox;
	private Stage stage;

	@FXML
	private TableView<Week> weekTableView;
	//@FXML
	//private TableColumn<Week, String> partOfTheDayColumn;
	@FXML
	private TableColumn<Week, String> mondayColumn;
	@FXML
	private TableColumn<Week, String> tuesdayColumn;
	@FXML
	private TableColumn<Week, String> wednesdayColumn;
	@FXML
	private TableColumn<Week, String> thursdayColumn;
	@FXML
	private TableColumn<Week, String> fridayColumn;
	@FXML
	private TableColumn<Week, String> saturdayColumn;
	@FXML
	private TableColumn<Week, String> sundayColumn;

	private Week weekModel;

	private List<Food> listOfSelectableFoods;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		this.weekModel = new Week();

		DataAccessObject<Food> foodDao = new DataAccessObject<>(Food.class, "PROGTECH");
		this.listOfSelectableFoods = foodDao.All();

		mondayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().GetFoodsFromDay(EWeekDayTypes.Monday).toString().replace("[", "").replace("]", "").replace(",", "")));
		tuesdayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().GetFoodsFromDay(EWeekDayTypes.Tuesday).toString().replace("[", "").replace("]", "").replace(",", "")));
		wednesdayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().GetFoodsFromDay(EWeekDayTypes.Wednesday).toString().replace("[", "").replace("]", "").replace(",", "")));
		thursdayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().GetFoodsFromDay(EWeekDayTypes.Thursday).toString().replace("[", "").replace("]", "").replace(",", "")));
		fridayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().GetFoodsFromDay(EWeekDayTypes.Friday).toString().replace("[", "").replace("]", "").replace(",", "")));
		saturdayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().GetFoodsFromDay(EWeekDayTypes.Saturday).toString().replace("[", "").replace("]", "").replace(",", "")));
		sundayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().GetFoodsFromDay(EWeekDayTypes.Sunday).toString().replace("[", "").replace("]", "").replace(",", "")));

		this.weekModel.GenerateAWeekWithFoods(this.listOfSelectableFoods, 2000);
		ObservableList<Week> listOfWeeks = FXCollections.observableArrayList();
		listOfWeeks.add(this.weekModel);
		this.weekTableView.setItems(listOfWeeks);
	}

	@FXML
	private void onCancelClick(ActionEvent e)
	{
		Parent root = null;

		try
		{
			root = FXMLLoader.load(getClass().getResource("/view/mainView.fxml"));
			this.stage = (Stage) this.vbox.getScene().getWindow();
			this.stage.setTitle("My Week");
			this.stage.setScene(new Scene(root));
			this.stage.getScene().getStylesheets().add("/styles/style.css");
			this.stage.show();
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}


	@FXML
	private TextField weightTextField;
	@FXML
	private TextField heightTextField;
	@FXML
	private TextField caloriesTextField;
	@FXML
	private Label bmiValueLabel;

	@FXML
	private void onGenerateClick(ActionEvent e)
	{
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Ups...");
		alert.setHeaderText("Information");
		alert.setContentText("You should add an valid number!");

		if (weightTextField.getText().isEmpty())
		{
			alert.show();
		} else
		{
			try
			{
				float userWeight = Float.parseFloat(this.weightTextField.getText());
				float userHeight = Float.parseFloat(this.heightTextField.getText());
				float bmiValue = this.weekModel.calculateTheBmiValueAccording(userHeight, userWeight);
				int calories = this.weekModel.calculateTheCaloriesValueAccording(bmiValue);

				bmiValueLabel.setText(String.format("Your BMI value is %f", bmiValue));
				this.caloriesTextField.setText(String.valueOf(calories));
				this.weekModel.GenerateAWeekWithFoods(this.listOfSelectableFoods, calories);
				this.weekTableView.refresh();
			} catch (NumberFormatException nfe)
			{
				alert.show();
			}
		}
	}
}
