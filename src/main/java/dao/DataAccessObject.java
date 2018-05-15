package dao;

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
import dao.interfaces.IDataAccessObject;

import javax.persistence.*;
import java.util.List;

/**
 * * The standard DataAccessObject.
 * @param <T> list of meals
 */
public class DataAccessObject<T> implements IDataAccessObject
{
	/**
	 * Class type variable.
	 * The food to work with.
	 */
	private final Class<T> type;
	/**
	 *  Variable to access the database.
	 */
	private final EntityManager entityManager;
	/**
	 * Creates the EntityManager.
	 */
	private final EntityManagerFactory entityManagerFactory;
	/**
	 * Manages the database transactions.
	 */
	private final EntityTransaction entityTransaction;
	/**
	 * Defines the logger.
	 */
	private final java.util.logging.Logger log;

	/**
	 * Defines the Data Access Objects.
	 * @param typeParameterClass Food entity
	 * @param persistenceUnitName the persistable data
	 */
	public DataAccessObject(Class<T> typeParameterClass, String persistenceUnitName)
	{
		this.type = typeParameterClass;
		this.log = java.util.logging.Logger.getLogger(this.type.getClass().getSimpleName());
		this.entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
		this.entityManager = this.entityManagerFactory.createEntityManager();
		this.entityTransaction = this.entityManager.getTransaction();
		this.entityManager.clear();
	}

	/**
	 * Adds the entity to the database.
	 * @param entity the meal
	 */
	@Override
	public void Add(Object entity)
	{
		try {
			this.entityTransaction.begin();
			this.entityManager.persist(entity);
			this.entityTransaction.commit();
		}
		catch (Exception e)
		{
			log.info(e.getMessage());
		}
	}

	/**
	 * Removes the meal from the database.
	 * @param entity the meal
	 */
	@Override
	public void Remove(Object entity)
	{
		try {
			this.entityTransaction.begin();
			this.entityManager.remove(entity);
			this.entityTransaction.commit();
		}
		catch (Exception e)
		{
			this.log.info(e.getMessage());
		}
	}

	/**
	 * Lists the meals.
	 * @return list of the meals
	 */
	@Override
	public List<T> All()
	{
		String sql = String.format("SELECT x FROM %s x", this.type.getSimpleName());
		TypedQuery<T> query = this.entityManager.createQuery(sql, this.type);
		return query.getResultList();
	}


}

