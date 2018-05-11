package dao;

import dao.interfaces.IDataAccessObject;

import javax.persistence.*;
import java.util.List;
import java.util.function.Predicate;

/**
 * The standard DataAccessObject
 *
 * @author Valicsek David
 * @version %I%, %G%
 * @since 1.0
 */
public class DataAccessObject<T> implements IDataAccessObject
{
	// Erasure
	private final Class<T> type;
	private final EntityManager entityManager;
	private final EntityManagerFactory entityManagerFactory;
	private final EntityTransaction entityTransaction;

	public DataAccessObject(Class<T> typeParameterClass, String persistenceUnitName)
	{
		this.type = typeParameterClass;
		this.entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
		this.entityManager = this.entityManagerFactory.createEntityManager();
		this.entityTransaction = this.entityManager.getTransaction();
	}

	@Override
	public void Add(Object entity)
	{
		this.entityTransaction.begin();
		this.entityManager.persist(entity);
		this.entityTransaction.commit();
	}

	@Override
	public void Edit(Object entity)
	{
		this.entityTransaction.begin();
		this.entityManager.persist(entity);
		this.entityTransaction.commit();
	}

	@Override
	public void Remove(Object entity)
	{
		this.entityTransaction.begin();
		this.entityManager.remove(entity);
		this.entityTransaction.commit();
	}

	@Override
	public List<T> All()
	{
		String sql = String.format("SELECT x FROM %s x", this.type.getSimpleName());
		// TypedQuery<T> query = this.entityManager.createNamedQuery(sql, this.type).setParameter("tableName", this.type.getName());
		TypedQuery<T> query = this.entityManager.createQuery(sql, this.type);
		System.out.println(query.toString());
		return query.getResultList();
	}

	@Override
	public List<T> Find(Predicate predicate)
	{
		return (List<T>) this.All().stream().filter(predicate);
	}
}
    
