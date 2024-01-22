package eventmanagement_DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import eventmanagement_DTO.Client;


public class ClientDAO 
{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("eventmanagement");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	public Client insertClient(Client client)
	{
		et.begin();
		em.persist(client);
		et.commit();
		
		return client;		
	}
	public Client findClient(int id)
	{
		Client client = em.find(Client.class, id);
		
		if(client != null)
		{
			return client;
		}
		return null;
	}
	
	public Client updateClient(Client client,int id)
	{
		Client s = em.find(Client.class, id);
		
		if(s != null)
		{
			et.begin();
			em.merge(client);
			et.commit();
		}
		
		return s;
	}
	
	public Client loginClient(String email,String password)
	{
		Query query = em.createQuery("select c from Client c where clientEmail=?1");
	    query.setParameter(1, email);
	    
	    Client client = (Client)query.getSingleResult();
	    
	    if(client != null)
	    {
	    	if(client.getClientPassword().equals(password))
	    	{
	    		return client;
	    	}
	    	return null;
	    }
	    
	    return null;
	}
}
