package eventmanagement_DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import eventmanagement_DTO.Client;
import eventmanagement_DTO.ClientEvent;
import eventmanagement_DTO.ClientService;

public class ClientServiceDAO 
{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("eventmanagement");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	public ClientService insertClientService(ClientService clientservice)
	{
		et.begin();
		em.persist(clientservice);
		et.commit();
		
		return clientservice;		
	}
	public ClientService findClientService(int id)
	{
		ClientService service = em.find(ClientService.class, id);
		
		if(service != null)
		{
			return service;
		}
		return null;
	}
	
	public ClientService updateClientService(ClientService service,int id)
	{
		ClientService s = em.find(ClientService.class, id);
		
		if(s != null)
		{
			et.begin();
			em.merge(service);
			et.commit();
		}
		
		return s;
	}
	
	public List<ClientService> getAllClientService()
	{
		Query query = em.createQuery("select c from ClientService c");
		
		List<ClientService> list = query.getResultList();
		if(list != null)
		{
			return list;
		}
		return null;
	}
	
	public List<ClientService> deleteClientService(int id,int csId)
	{
		ClientEvent client = em.find(ClientEvent.class, csId);
		List<ClientService> exservice=client.getClientServices();
		List<ClientService> newservice= new ArrayList<ClientService>();
		
		ClientService oldservice=em.find(ClientService.class, id);
		
		if(exservice != null)
		{
			for(ClientService s : exservice)
			{
				if(!oldservice.getClientServiceName().equals(s.getClientServiceName()))
				{
					newservice.add(s);
				}
			}
			
			et.begin();
			client.setClientServices(newservice);
			em.merge(client);
			et.commit();
			
			if(oldservice != null)
			{
				et.begin();
				em.remove(oldservice);
				et.commit();
			}
		}
		
		return newservice;
	}
}
