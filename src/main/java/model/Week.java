package model;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.enumerators.EWeekDayTypes;
import model.interfaces.IWeekLogic;

import java.util.*;

/**
 * Represents the days of the week.
 */
public class Week implements IWeekLogic {
	/**
	 * Adds the meals to the days of the week.
	 */
	private final HashMap<EWeekDayTypes, List<Food>> daysWithFoods;

	/**
	 * Constructor of the Week's class.
	 */
	public Week() {
		this.daysWithFoods = new HashMap<>();
	}

	/**
	 * Overriding of the Week's constructor.
	 *
	 * @param days A hashmap dictionary which contains the days
	 */
	public Week(HashMap<EWeekDayTypes, List<Food>> days) {
		this.daysWithFoods = days;
	}

	/**
	 * This function add foodlists to a specific date.
	 *
	 * @param day      The day where we want to add the list of foods
	 * @param foodList The list of foods what we want to add
	 */
	public void AddFoodsToDay(EWeekDayTypes day, List<Food> foodList) {
		this.daysWithFoods.put(day, foodList);
	}

	/**
	 * Returns the list of foods according to the day.
	 *
	 * @param day The day itself
	 * @return list of foods
	 */
	public List<Food> GetFoodsFromDay(EWeekDayTypes day) {
		return this.daysWithFoods.get(day);
	}

	/**
	 * This function generates a list of Foods according to nuOfCalories.
	 *
	 * @param listOfSelectableFoods The list of foods what generated
	 * @param nuOfCalories          The number of calories
	 */
	@Override
	public void GenerateAWeekWithFoods(List<Food> listOfSelectableFoods, int nuOfCalories) {

		int min = 0;
		int max = listOfSelectableFoods.size() - 1;

		Random r = new Random();

		for (EWeekDayTypes day : EWeekDayTypes.values()) {
			ObservableList<Food> listOfFoods = FXCollections.observableArrayList();
			int consumedCalories = 0;
			while (consumedCalories < nuOfCalories) {
				int randomIndexForListOfSelectableFoods = r.nextInt(max - min + 1) + min;
				Food food = listOfSelectableFoods.get(randomIndexForListOfSelectableFoods);
				consumedCalories += food.GetCalorie();
				if (consumedCalories < nuOfCalories) {
					listOfFoods.add(food);
				}
			}
			this.AddFoodsToDay(day, listOfFoods);
		}
	}

	/**
	 * This function calculates the bmi value according to the height and weight.
	 *
	 * @param height The height of the user
	 * @param weight The weight of the user
	 * @return the calculated value from the weight and height
	 *
	 */
	@Override
	public float calculateTheBmiValueAccording(float height, float weight) {
		if (height == 0) return 0;
		return (weight / (height * height)) * 10000;
	}

	/**
	 * This function calculates the calories value according to the bmi's value.
	 *
	 * @param bmiValue The value we calculate from the user's weight and height
	 * @return return with the kCal value the user should eat
	 */
	@Override
	public int calculateTheCaloriesValueAccording(float bmiValue) {
		Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
		alert2.setTitle("Ups...");
		alert2.setHeaderText("Information");
		alert2.setContentText("Wrong data!");


		if (bmiValue <= 10 || bmiValue >= 40) {
			alert2.show();
		} else {

			if (bmiValue > 11 && bmiValue <= 18.5) {
				return 2500;
			} else if (bmiValue >= 18.6 && bmiValue < 25) {
				return 2000;
			} else if (bmiValue >= 25 && bmiValue < 30) {
				return 1500;
			} else if (bmiValue >= 30 && bmiValue < 40) {
				return 1000;
			}
		}
		return 0;
	}
}
