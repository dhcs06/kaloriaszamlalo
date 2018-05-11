package controller;

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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Food;
import model.enumerators.EFoodTypes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FoodController implements Initializable
{
	@FXML
	private VBox vbox;
	@FXML
	private ListView<Food> listViewFood;

	private Stage stage;

	private DataAccessObject<Food> foodDao;

	private ObservableList<Food> listOfFoods;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		// this.stage = (Stage) this.vbox.getScene().getWindow(); Not initialized yet.
		this.listOfFoods = FXCollections.observableArrayList();
		this.foodDao = new DataAccessObject<>(Food.class, "PROGTECH");
		this.listOfFoods.addAll(this.foodDao.All());
		this.listViewFood.setItems(this.listOfFoods);
		this.listViewFood.setCellFactory(new Callback<ListView<Food>, ListCell<Food>>()
		{

			@Override
			public ListCell<Food> call(ListView<Food> p)
			{

				ListCell<Food> cell = new ListCell<Food>()
				{

					@Override
					protected void updateItem(Food t, boolean bln)
					{
						super.updateItem(t, bln);
						if (t != null)
						{
							this.getStyleClass().add("test");
							this.setText(String.format("%s %s kcal", t.GetName(), t.GetCalorie()));
						}
					}
				};

				return cell;
			}
		});
		this.Refresh();
	}

	@FXML
	private void onRemoveClick(ActionEvent e)
	{
		Food selectedFood = this.listViewFood.getSelectionModel().getSelectedItem();
		if (selectedFood != null)
		{
			this.foodDao.Remove(selectedFood);
			// Instead of recalling the database, just delete the item from the list, and refresh it.
			this.listOfFoods.remove(selectedFood);

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setContentText("You have successfully deleted the Food!");
			alert.show();
			this.Refresh();
		}
	}

	@FXML
	private void onAddClick(ActionEvent e)
	{
		Food newFood = new Food(995, "Medvesajt", EFoodTypes.Dinner);
		if (newFood != null)
		{
			this.foodDao.Add(newFood);
			// Instead of recalling the database, just delete the item from the list, and refresh it.
			this.listOfFoods.add(newFood);

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setContentText("You have successfully added the Food!");
			alert.show();
			this.Refresh();
		}
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

	private void Refresh()
	{
		this.listViewFood.refresh();
	}
}
