import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;


public class Application {

	public static void main(String[] args) throws Exception {
		
		Properties propContainer = new Properties();
		
		//OPEN EJB
		propContainer.put("httpejbd", "cxf-rs");
		propContainer.put("openejb.embedded.remotable", "true");
		
		//DATASOURCE
		propContainer.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.core.LocalInitalContextFactory");
		
		//create some resources
		propContainer.put("dbUnit", "new://Resource?type=DataSource");
		propContainer.put("dbUnit.JdbcDriver", "org.hsqldb.jdbcDriver");
		propContainer.put("dbUnit.JdbcUrl", "jdbc:hsqldb:mem:moviedb");
		
		// override properties on your "moview"
		propContainer.put("dbUnit.hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		
		EJBContainer container = EJBContainer.createEJBContainer(propContainer);
		
		Context context = container.getContext();
		
		SimpleRestEJB object = (SimpleRestEJB) context.lookup("java:global/Teste/SimpleRestEJB");
		Assert.assertNotNull(object);
		
		String messageFromEJB = object.ejb();
		Assert.assertNotNull(messageFromEJB);
		System.out.println(messageFromEJB);
		
		String messageFromRESTService = WebClient.create("http://localhost:4204").path("/Teste/ejb/").get(String.class);
		
		Assert.assertEquals(messageFromEJB, messageFromRESTService);
		System.out.println(messageFromRESTService);
		
		
		
	}

}
