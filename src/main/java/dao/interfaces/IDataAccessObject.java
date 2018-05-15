package dao.interfaces;

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
import java.util.List;
import java.util.function.Predicate;

/**
 * Interface which describes the possible functionality of a DataAccessObject.
 *
 * @param <T> The name of the entity we would like to use
 */
public interface IDataAccessObject<T>
{
	/**
	 * Adds the meal to the database.
	 * @param entity the meal
	 */
	void Add(T entity);


	/**
	 * Removes the meal to the database.
	 * @param entity the meal
	 */
	void Remove(T entity);

	/**
	 * Lists all the meal.
	 * @return list with all the meal
	 */
	List<T> All();
}
