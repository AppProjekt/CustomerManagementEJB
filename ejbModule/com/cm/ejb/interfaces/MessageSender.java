package com.cm.ejb.interfaces;

import java.util.concurrent.Future;

import com.cm.persistence.entities.Customer;

public interface MessageSender 
{
	
	public Future<Boolean> send(
			String subject, String body, Customer customer);

}
