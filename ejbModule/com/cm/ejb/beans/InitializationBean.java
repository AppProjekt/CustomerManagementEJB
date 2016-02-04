package com.cm.ejb.beans;

import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.cm.ejb.interfaces.CustomerDAO;
import com.cm.persistence.entities.Address;
import com.cm.persistence.entities.Communication;
import com.cm.persistence.entities.Customer;
import com.cm.persistence.enums.CommunicationType;
//import com.cm.persistence.enums.Gender;
import com.cm.persistence.enums.Kind;

@Singleton
@Startup
public class InitializationBean {

	@EJB
	private CustomerDAO customerDAO;

	@PostConstruct
	private void initialize() {
		
		if(customerDAO.getAllCustomers().size() == 0) {
			
			// Customer anlegen
			Customer customer = new Customer();
			customer.setFirstName("Karsten");
			customer.setLastName("Samaschke");
			customer.setCity("Berlin");
			customer.setCountry("Germany");
			customer.setStreet("Warschauer Strasse 58a");
			customer.setZip("10243");
			customer.setKontakt("info@samaschke.de");
//			customer.setGender(Gender.Male);
			
//			Calendar birthday = Calendar.getInstance();
//			birthday.set(Calendar.DAY_OF_MONTH, 1);
//			birthday.set(Calendar.MONTH, 1);
//			birthday.set(Calendar.YEAR, 1975);
//			customer.setBirthday(birthday.getTime());
			
			// Adresse definieren
//			Address address = new Address();
//			address.setCity("Berlin");
//			address.setCountry("Germany");
//			address.setStreet("Warschauer Strasse 58a");
//			address.setZip("10243");
//			address.setKind(Kind.Business);
//			customer.getAddresses().add(address);
			
			// E-Mail-Adresse definieren
//			Communication communication = new Communication();
//			communication.setName("Geschäft");
//			communication.setValue("info@samaschke.de");
//			communication.setCommunicationType(CommunicationType.Email);
//			communication.setKind(Kind.Business);
//			customer.getCommunications().add(communication);
			
			customerDAO.create(customer);
		}
	}
}
