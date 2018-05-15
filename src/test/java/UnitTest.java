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
import model.Week;
import model.enumerators.EFoodTypes;
import model.enumerators.EWeekDayTypes;
import model.interfaces.IWeekLogic;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The IWeekLogic tester class
 */
public class UnitTest
{
	IWeekLogic weekLogic;
	List<Food> listOfFoods;

	@Before
	public void setUp() throws Exception
	{
		this.weekLogic = new Week();
		this.listOfFoods = new ArrayList<>();
		this.listOfFoods.add(new Food(123, "Macisajt", EFoodTypes.Dinner));

		this.weekLogic.AddFoodsToDay(EWeekDayTypes.Monday, this.listOfFoods);
	}

	@Test
	public void TestThatGetFoodsFromMondayIsExactlyEqualsToOne()
	{
		this.weekLogic.GetFoodsFromDay(EWeekDayTypes.Monday);
		Assert.assertEquals(1, this.weekLogic.GetFoodsFromDay(EWeekDayTypes.Monday).size());
	}


	@Test
	public void TestThatGetFoodsFromTuesday()
	{
		this.weekLogic.GetFoodsFromDay(EWeekDayTypes.Monday);
		Assert.assertEquals(null, this.weekLogic.GetFoodsFromDay(EWeekDayTypes.Tuesday));
	}

	@Test
	public void TestThatGenerateAWeekWithFoodsWith0Calories()
	{
		this.weekLogic.GenerateAWeekWithFoods(this.listOfFoods, 0);
		Assert.assertEquals(0, this.weekLogic.GetFoodsFromDay(EWeekDayTypes.Monday).size());
	}

	@Test
	public void TestThatGenerateAWeekWithFoodsWith2000CaloriesShouldBeGreaterThanZero()
	{
		this.weekLogic.GenerateAWeekWithFoods(this.listOfFoods, 2000);
		Assert.assertTrue(this.listOfFoods.size() > 0);
	}

	@Test
	public void TestThatCalculateTheBmiValueAccordingZeroValues()
	{
		float value = this.weekLogic.calculateTheBmiValueAccording(0, 0);
		Assert.assertTrue(value == 0);
	}
}
