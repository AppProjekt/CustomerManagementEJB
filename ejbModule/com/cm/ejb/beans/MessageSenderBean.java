package com.cm.ejb.beans;

import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.cm.ejb.interfaces.MessageSender;
import com.cm.persistence.entities.Communication;
import com.cm.persistence.entities.Customer;
import com.cm.persistence.enums.CommunicationType;

@Stateless
@Remote(MessageSender.class)
public class MessageSenderBean implements MessageSender {
	
	private static final String DEFAULT_SENDER = "me@myself.tld";

	@Resource(name = "java:jboss/mail/GoogleMail")
	private Session mailSession;
	
	@Override
	public Future<Boolean> send(String subject, String body, Customer customer) {

		boolean result = false;
		
		Communication email = null;
		for(Communication communication : customer.getCommunications()) {
			if(communication.getCommunicationType() == CommunicationType.Email) {
				email = communication;
				break;
			}
		}
		
		if(email != null) {
			
			javax.mail.Message mail = new MimeMessage(mailSession);
			
			try {
				
				mail.setRecipients(
						javax.mail.Message.RecipientType.TO, 
						InternetAddress.parse(email.getValue()));
				
		        mail.setFrom(
		        		InternetAddress.parse(DEFAULT_SENDER)[0]);
				
		        mail.setSubject(subject);
		        mail.setText(body);
		        
		        Transport.send(mail);
		        
		        result = true;
		        
			} catch(MessagingException e) {
				e.printStackTrace();
			}
			
		}

		return new AsyncResult<Boolean>(result);
	}

}
