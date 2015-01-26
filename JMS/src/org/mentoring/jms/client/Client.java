package org.mentoring.jms.client;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Client {

	static Context ic = null;

	static ConnectionFactory cf = null;

	static Connection connection = null;

	private static final String JMS_QUEUE_JNDI_NAME = "jms/queue/test";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Properties properties = new Properties();
			properties.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.jboss.naming.remote.client.InitialContextFactory");
			properties.put(Context.PROVIDER_URL, "remote://localhost:4447");
			properties.put(Context.SECURITY_PRINCIPAL, "qqq");
			properties.put(Context.SECURITY_CREDENTIALS, "www");
			ic = new InitialContext(properties);
			cf = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");

			ic.lookup(JMS_QUEUE_JNDI_NAME);

		} catch (NamingException e) {

			e.printStackTrace();
		}

		String destinationName = "java:/jms/queue/test";

		try {
			connection = cf.createConnection();
			Queue queue = (Queue) ic.lookup(destinationName);
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			MessageProducer publisher = session.createProducer(queue);
			connection.start();
			TextMessage message = session
					.createTextMessage("Interesting message");
			// publish the message to the defined Queue
			publisher.send(message);

			System.out
					.println("---------Message sent to  the JMS Provider--------");

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {

			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
