package dao.interfaces;

import java.util.List;
import java.util.function.Predicate;

/**
 * Interface which describes the possible functionality of a DataAccessObject
 *
 * @param <T>
 * @author valicsekdavid
 */
public interface IDataAccessObject<T>
{
	void Add(T entity);

	void Edit(T entity);

	void Remove(T entity);

	List<T> All();

	List<T> Find(Predicate<T> predicate);
}
