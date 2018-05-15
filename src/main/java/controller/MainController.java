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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
	@FXML
	private VBox vbox;
	private Stage stage;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{

	}

	@FXML
	private void onMyWeekClick(ActionEvent e)
	{
		Parent root = null;

		try
		{
			root = FXMLLoader.load(getClass().getResource("/view/weekView.fxml"));
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
	private void onFoodListClick(ActionEvent e)
	{
		Parent root = null;

		try
		{
			root = FXMLLoader.load(getClass().getResource("/view/food/foodView.fxml"));
			this.stage = (Stage) this.vbox.getScene().getWindow();
			this.stage.setTitle("My Foods");
			this.stage.setScene(new Scene(root));
			this.stage.getScene().getStylesheets().add("/styles/style.css");
			this.stage.show();
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}


	@FXML
	private void onExitClick(ActionEvent e)
	{
		System.exit(1);
	}
}
