package eventmanagement_DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import eventmanagement_DTO.Admin;
import eventmanagement_DTO.Service;

public class ServiceDAO 
{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("eventmanagement");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	public Service insertService(Service service)
	{
		et.begin();
		em.persist(service);
		et.commit();
		
		return service;		
	}
	public Service findService(int id)
	{
		Service service = em.find(Service.class, id);
		
		if(service != null)
		{
			return service;
		}
		return null;
	}
	
	public Service updateService(Service service,int id)
	{
		Service s = em.find(Service.class, id);
		
		if(s != null)
		{
			et.begin();
			em.merge(service);
			et.commit();
		}
		
		return s;
	}
	
	public List<Service> deleteService(int id,int adminId)
	{
		
		Admin a = em.find(Admin.class, adminId);
		List<Service> oldService = a.getServices();
		List<Service> newService = new ArrayList<Service>();
		
		Service service = em.find(Service.class, id);
		
		if(oldService != null)
		{
			for(Service s : oldService)
			{
				if(!s.getServiceName().equals(service.getServiceName()))
				{
					newService.add(s);					
				}
			}
			
			et.begin();
			a.setServices(newService);
			em.merge(a);
			et.commit();
			
			for(Service ser: oldService)
			{
				if(ser.getServiceName().equals(service.getServiceName()))
				{
					int idservice = ser.getServiceId();
					deleteService(idservice);
				}
			}
		}
		
		return newService;	
	}
	
	public List<Service> getAllService()
	{
		String jpql = "select s from Service s";
		Query query = em.createQuery(jpql);
		
		List<Service> service=query.getResultList();
		if(service != null)
		{
			return service;
		}
		
		return null;
		
	}
	
	public Service deleteService(int id)
	{
		Service service = em.find(Service.class, id);
		
		if(service != null)
		{
			et.begin();
			em.remove(service);
			et.commit();
		}
		
		return null;
	}


}
