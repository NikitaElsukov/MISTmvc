package org.yonko.mist.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yonko.mist.domain.Employee;
import org.yonko.mist.domain.Passport;

@Repository
public class MistEmployeeDAO implements EmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addEmployee(Employee employee) {
		currentSession().save(employee);
	}

	@Override
	public void removeEmployee(Integer employeeID) {
		Employee employee = (Employee) currentSession().load(Employee.class,
				employeeID);
		if (employee != null) {
			currentSession().delete(employee);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployees() {
		return sessionFactory.getCurrentSession().createQuery("from Employee")
				.list();
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		return (Employee) currentSession().get(Employee.class, id);
	}

	@Override
	public void updateDescription(Employee employee) {
		currentSession().update(employee);
	}

	@SuppressWarnings("unchecked")
	public List<Employee> searchEmployee(String fio) {
		Query query = currentSession().createQuery(
				"from Employee where employeeFIO like '%" + fio + "%'");
		return query.list();
		// Без like делал так
		/*
		 * Query query =
		 * currentSession().createQuery("from Employee where employeeFIO = :fio"
		 * ); query.setParameter("fio", fio);
		 */
	}
	
	public void updatePassport(Passport passport) {
		currentSession().update(passport);
	}
	
	public Passport getPassportById(Integer id) {
		return (Passport) currentSession().get(Passport.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Passport> getAllPassports() {
		return currentSession().createQuery("from Passport").list();
	}
	
	public void removePassport(Integer id) {
		Passport passport = (Passport) currentSession().load(Passport.class, id);
		if (passport != null) {
			currentSession().delete(passport);	
		}	
	}

	@Override
	public void addPassport(Passport passport) {
		currentSession().save(passport);
	}
}
