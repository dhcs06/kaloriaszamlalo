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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Food;
import model.Week;
import model.enumerators.EFoodTypes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FoodController implements Initializable {
    @FXML
    private VBox vbox;
    @FXML
    private ListView<Food> listViewFood;

    @FXML
    private TextField foodname;

    @FXML
    private TextField foodcalorie;

    private Stage stage;

    private DataAccessObject<Food> foodDao;

    private ObservableList<Food> listOfFoods;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.listOfFoods = FXCollections.observableArrayList();
        this.foodDao = new DataAccessObject<>(Food.class, "PROGTECH");
        this.listOfFoods.addAll(this.foodDao.All());
        this.listViewFood.setItems(this.listOfFoods);

        this.Refresh();
    }

    @FXML
    private void onRemoveClick(ActionEvent e) {
        Food selectedFood = this.listViewFood.getSelectionModel().getSelectedItem();
        if (selectedFood != null) {
            this.foodDao.Remove(selectedFood);
            this.listOfFoods.remove(selectedFood);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("You have successfully deleted the Food!");
            alert.show();
            this.Refresh();
        }
    }

    @FXML
    private void onAddClick(ActionEvent e) {
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("Ups...");
        alert2.setHeaderText("Information");
        alert2.setContentText("Something is missing!!");

        if ((foodcalorie.getText().isEmpty() || foodname.getText().isEmpty())) {
            alert2.show();
        } else {
            try {
                String food = (this.foodname.getText());
                float calorie = Float.parseFloat(this.foodcalorie.getText());
                Food newFood = new Food(calorie, food, EFoodTypes.Dinner);
                this.foodDao.Add(newFood);
                this.listOfFoods.add(newFood);

                this.Refresh();


            } catch (NumberFormatException nfe) {
                alert2.setContentText("Invalid number");
                alert2.show();
            }
        }
    }

    @FXML
    private void onCancelClick(ActionEvent e) {
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/view/mainView.fxml"));
            this.stage = (Stage) this.vbox.getScene().getWindow();
            this.stage.setTitle("My Week");
            this.stage.setScene(new Scene(root));
            this.stage.getScene().getStylesheets().add("/styles/style.css");
            this.stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void Refresh() {
        this.listViewFood.refresh();
    }
}
