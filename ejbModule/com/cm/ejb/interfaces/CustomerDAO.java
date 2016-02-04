package com.cm.ejb.interfaces;

import java.util.List;

import com.cm.persistence.entities.Customer;

public interface CustomerDAO {
	
	public Customer create(Customer customer);
	
	public Customer update(Customer customer);
	
	public void remove(int id);
	
	public Customer getCustomer(int id);
	
	public List<Customer> getAllCustomers();

//	public List<Customer> getCustomersHavingBirthday();
}

