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
public class IWeekLogicTest
{
	IWeekLogic weekLogic;
	List<Food> listOfFoods;

	@Before
	public void setUp() throws Exception
	{
		this.weekLogic = new Week();
		this.listOfFoods = new ArrayList<>();
		this.listOfFoods.add(new Food(123, "Bear", EFoodTypes.Dinner));

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