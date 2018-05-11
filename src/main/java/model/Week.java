package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.enumerators.EWeekDayTypes;
import model.interfaces.IWeekLogic;

import java.util.*;

/**
 * @author valicsekdavid
 */
public class Week implements IWeekLogic
{
	private final HashMap<EWeekDayTypes, List<Food>> daysWithFoods;

	/**
	 * Constructor of the Week's class
	 */
	public Week()
	{
		this.daysWithFoods = new HashMap<>();
	}

	/**
	 * Overriding of the Week's constructor
	 *
	 * @param days A hashmap dictionary which contains the days
	 */
	public Week(HashMap<EWeekDayTypes, List<Food>> days)
	{
		this.daysWithFoods = days;
	}

	/**
	 * This function add foodlists to a specific date
	 *
	 * @param day      The day where we want to add the list of foods
	 * @param foodList The list of foods what we want to add
	 */
	public void AddFoodsToDay(EWeekDayTypes day, List<Food> foodList)
	{
		this.daysWithFoods.put(day, foodList);
	}

	/**
	 * Returns the list of foods according to the day
	 *
	 * @param day The day itself
	 * @return list of foods
	 */
	public List<Food> GetFoodsFromDay(EWeekDayTypes day)
	{
		return this.daysWithFoods.get(day);
	}

	/**
	 * This function generates a list of Foods according to nuOfCalories
	 *
	 * @param listOfSelectableFoods The list of foods what generated.
	 * @param nuOfCalories          The number of calories
	 */
	@Override
	public void GenerateAWeekWithFoods(List<Food> listOfSelectableFoods, int nuOfCalories)
	{
		/*
		List<Food> listOfSelectableFoods = new ArrayList<>();
		listOfSelectableFoods.add(new Food(68, "Alma", EFoodTypes.Dinner));
		listOfSelectableFoods.add(new Food(121, "Sajt", EFoodTypes.Dinner));
		listOfSelectableFoods.add(new Food(28, "Tojas", EFoodTypes.Dinner));
		listOfSelectableFoods.add(new Food(58, "Turo rudi", EFoodTypes.Dinner));
		listOfSelectableFoods.add(new Food(204, "Csirkemell", EFoodTypes.Dinner));
		listOfSelectableFoods.add(new Food(81, "Csoki", EFoodTypes.Dinner));
		listOfSelectableFoods.add(new Food(206, "Karamella", EFoodTypes.Dinner));
		*/

		int min = 0;
		int max = listOfSelectableFoods.size() - 1;

		Random r = new Random();

		for (EWeekDayTypes day : EWeekDayTypes.values())
		{
			ObservableList<Food> listOfFoods = FXCollections.observableArrayList();
			int consumedCalories = 0;
			while (consumedCalories < nuOfCalories)
			{
				int randomIndexForListOfSelectableFoods = r.nextInt(max - min + 1) + min;
				Food food = listOfSelectableFoods.get(randomIndexForListOfSelectableFoods);
				consumedCalories += food.GetCalorie();
				if (consumedCalories < nuOfCalories)
				{
					listOfFoods.add(food);
				}
			}
			this.AddFoodsToDay(day, listOfFoods);
		}
	}

	/**
	 * This function calculates the bmi value according to the height and weight.
	 *
	 * @param height
	 * @param weight
	 * @return
	 */
	@Override
	public float calculateTheBmiValueAccording(float height, float weight)
	{
		if (height == 0) return 0;
		return (weight / (height * height)) * 10000;
	}

	/**
	 * This function calculates the calories value according to the bmi's value.
	 *
	 * @param bmiValue
	 * @return
	 */
	@Override
	public int calculateTheCaloriesValueAccording(float bmiValue)
	{
		if (bmiValue == 0) return 0;
		if (bmiValue > 30)
		{
			return 1000;
		}
		return (int) (bmiValue * bmiValue);
	}
}