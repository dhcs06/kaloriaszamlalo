/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.interfaces;

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

import model.Food;
import model.enumerators.EWeekDayTypes;

import java.util.List;

/**
 * Defines the operations of the calculations.
 */
public interface IWeekLogic
{
	/**
	 * Declarations of the operations.
	 * @param day type of the day
	 * @param foodList list with the foods for a day
	 */
	void AddFoodsToDay(EWeekDayTypes day, List<Food> foodList);

	/**
	 *
	 * Returns the list of foods according to the day.
	 *
	 * @param day The day itself
	 * @return list of foods
	 */
	List<Food> GetFoodsFromDay(EWeekDayTypes day);

	/**
	 * This function generates a list of Foods according to nuOfCalories.
	 *
	 * @param listOfSelectableFoods The list of foods what generated
	 * @param nuOfCalories          The number of calories
	 */
	void GenerateAWeekWithFoods(List<Food> listOfSelectableFoods, int nuOfCalories);

	/**
	 * This function calculates the bmi value according to the height and weight.
	 *
	 * @param height The height of the user
	 * @param weight The weight of the user
	 * @return the calculated value from the weight and height
	 *
	 */
	float calculateTheBmiValueAccording(float height, float weight);

	/**
	 * This function calculates the calories value according to the bmi's value.
	 *
	 * @param bmi The value we calculate from the user's weight and height
	 * @return return with the kCal value the user should eat
	 */
	int calculateTheCaloriesValueAccording(float bmi);
}
