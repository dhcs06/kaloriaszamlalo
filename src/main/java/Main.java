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
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class, initializing the interfaces and the JavaFX application.
 *
 *@author Niki
 */
public class Main extends Application
{
    /**
     * The starting method of the application.
     * It loads the starting screen.
     * @param primaryStage starting screen
     * @throws Exception If any error occurs while loading screen
     */
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("/view/mainView.fxml"));
		primaryStage.setTitle("Main");
		primaryStage.setScene(new Scene(root));
		primaryStage.getScene().getStylesheets().add("/styles/style.css");
		primaryStage.show();
	}

	/**
	 * The main method which starts the application.
	 *
	 * @param args command arguments
	 */
	public static void main(String[] args)
	{
		launch(args);
	}
}

