package eventmanagement_DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import eventmanagement_DTO.Client;
import eventmanagement_DTO.ClientEvent;
import eventmanagement_DTO.ClientService;

public class ClientEventDAO 
{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("eventmanagement");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	public ClientEvent insertClientEvent(ClientEvent clientEvent)
	{
		et.begin();
		em.persist(clientEvent);
		et.commit();
		
		return clientEvent;		
	}
	public ClientEvent findClientEvent(int id)
	{
		ClientEvent clientEvent = em.find(ClientEvent.class, id);
		
		if(clientEvent != null)
		{
			return clientEvent;
		}
		return null;
	}
	
	public ClientEvent updateClientEvent(int clientEventid,int clientid)
	{
		Client s = em.find(Client.class, clientid);
		List<ClientEvent> list =s.getEvents();
		ClientEvent c=em.find(ClientEvent.class, clientEventid);
		
		if(c != null)
		{
			et.begin();
			list.add(c);
			em.merge(s);
			et.commit();
		}
		if(s != null)
		{
			et.begin();
			c.setClients(s);
			em.merge(c);
			et.commit();
		}
		
		return c;
	}
	
	public List<ClientEvent> getAllClientEvent()
	{
		Query query = em.createQuery("select c from ClientEvent c");
		
		List<ClientEvent> list = query.getResultList();
		if(list != null)
		{
			return list;
		}
		return null;
	}
	
	public ClientEvent updateClientService(int clienteventid,List<ClientService> service)
	{
		ClientEvent event = em.find(ClientEvent.class, clienteventid);
		List<ClientService> oldservice=event.getClientServices();
		
		if(event != null)
		{
			et.begin();
			event.setClientServices(oldservice);
			event.setClientServices(service);
			em.merge(event);
			et.commit();
			
			return event;
		}
		return null;
	}

}
