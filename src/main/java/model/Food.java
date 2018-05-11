package model;

import model.enumerators.EFoodTypes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Ancestor class for all possible foods.
 * We assume that the business logic go in the model.
 */
@Entity
@Table(name = "Food")
public class Food
{
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "calorie")
	private float calorie;
	@Column(name = "name")
	private String name;
	@Column(name = "type")
	private EFoodTypes type;


	public Food()
	{
	}

	/**
	 * The constructor of the Food's class
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
	 * Returns the calorie value of the food
	 *
	 * @return The calorie value
	 */
	public float GetCalorie()
	{
		return this.calorie;
	}

	/**
	 * Returns the name of the food
	 *
	 * @return the name of the food
	 */
	public String GetName()
	{
		return this.name;
	}

	/**
	 * Returns the type of the food
	 *
	 * @return the type of the food
	 */
	public EFoodTypes GetFoodType()
	{
		return this.type;
	}

	@Override
	public String toString()
	{
		return String.format("%s %s kcal \n", this.name, this.calorie);
	}
}