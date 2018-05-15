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
import model.enumerators.EFoodTypes;

import javax.persistence.*;

/**
 * Ancestor class for all possible foods.
 * We assume that the business logic go in the model.
 */
@Entity
@Table(name = "Food")
public class Food
{
	/**
	 * Id of the meal.
	 */
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	/**
	 * Calorie value of the meal.
	 */
	@Column(name = "calorie")
	private float calorie;
	/**
	 * Name of the meal.
	 */
	@Column(name = "name")
	private String name;
	/**
	 * Type of the meal.
	 */
	@Column(name = "type")
	private EFoodTypes type;

	/**
	 *Initializes the object.
	 */
	public Food()
	{
	}

	/**
	 * The constructor of the Food's class.
	 *
	 * @param calorie The calorie of the food
	 * @param name    The name of the food
	 * @param type    the type of the food
	 */
	public Food(float calorie, String name, EFoodTypes type)
	{
		this.calorie = calorie;
		this.name = name;
		this.type = type;
	}

	/**
	 * Returns the calorie value of the food.
	 *
	 * @return The calorie value
	 */
	public float GetCalorie()
	{
		return this.calorie;
	}

	/**
	 * Returns the name of the food.
	 *
	 * @return the name of the food
	 */
	public String GetName()
	{
		return this.name;
	}

	/**
	 * Returns the type of the food.
	 *
	 * @return the type of the food
	 */
	public EFoodTypes GetFoodType()
	{
		return this.type;
	}

	/**
	 * Gives the right format to the food.
	 *
	 * @return the suitable format of the food
	 */
	@Override
	public String toString()
	{
		return String.format("%s %s kcal \n", this.name, this.calorie);
	}
}
