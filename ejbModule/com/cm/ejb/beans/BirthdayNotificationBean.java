package com.cm.ejb.beans;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import com.cm.ejb.interfaces.CustomerDAO;
import com.cm.persistence.entities.Customer;

@Singleton
@Startup
public class BirthdayNotificationBean {

	@EJB
	private CustomerDAO customerDAO;
	
	@Inject
	private JMSContext context;
	
	@Resource(mappedName="java:/jms/queue/CustomerManagementQueue")
	private Destination birthdayDestination;
	
	@Schedule(hour="9")
	private void checkBirthday() {
		
		List<Customer> birthdays = customerDAO.getCustomersHavingBirthday();
		
		for(Customer customer : birthdays) {
			context.createProducer().send(birthdayDestination, customer);
		}

	}
	
}

