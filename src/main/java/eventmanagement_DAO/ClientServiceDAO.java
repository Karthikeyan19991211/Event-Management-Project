package eventmanagement_DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
}
