package com.cm.ejb.beans;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cm.ejb.interfaces.CustomerDAO;
import com.cm.persistence.entities.Customer;

@Stateless
@Remote(CustomerDAO.class)
public class CustomerBean implements CustomerDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Customer create(Customer customer) {
		em.persist(customer);
		return customer;
	}

	@Override
	public Customer update(Customer customer) {
		return em.merge(customer);
	}

	@Override
	public void remove(int id) {
		Customer toBeDeleted = getCustomer(id);
		em.remove(toBeDeleted);
	}

	@Override
	public Customer getCustomer(int id) {
		return em.find(Customer.class, id);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return
				em.createNamedQuery(Customer.QUERY_GETALL, Customer.class)
				  .getResultList();
	}

	@Override
	public List<Customer> getCustomersHavingBirthday() {
		Calendar todaysCalendar = Calendar.getInstance();
		
		return em.createNamedQuery(Customer.QUERY_BIRTHDAYS, Customer.class)
				 .setParameter(1, todaysCalendar.get(Calendar.DAY_OF_MONTH))
				 .setParameter(2, todaysCalendar.get(Calendar.MONTH))
				 .getResultList();
	}
}