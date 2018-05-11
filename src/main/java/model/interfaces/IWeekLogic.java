/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.interfaces;


import model.Food;
import model.enumerators.EWeekDayTypes;

import java.util.List;

/**
 * @author valicsekdavid
 */
public interface IWeekLogic
{
	void AddFoodsToDay(EWeekDayTypes day, List<Food> foodList);

	List<Food> GetFoodsFromDay(EWeekDayTypes day);

	void GenerateAWeekWithFoods(List<Food> listOfSelectableFoods, int nuOfCalories);

	float calculateTheBmiValueAccording(float height, float weight);

	int calculateTheCaloriesValueAccording(float bmi);
}
