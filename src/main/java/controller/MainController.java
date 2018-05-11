package controller;

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
		// this.stage = (Stage) this.vbox.getScene().getWindow(); Not initialized yet.
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
