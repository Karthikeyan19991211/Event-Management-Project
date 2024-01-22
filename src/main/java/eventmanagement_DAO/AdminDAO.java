package eventmanagement_DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import eventmanagement_DTO.Admin;

public class AdminDAO 
{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("eventmanagement");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	public Admin insertAdmin(Admin admin)
	{
		et.begin();
		em.persist(admin);
		et.commit();
		
		return admin;		
	}
	
	public Admin findAdmin(int id)
	{
		Admin a = em.find(Admin.class, id);
		if(a != null)
		{
			return a;
		}
		return null;
	}
	
	
	public Admin findAdmin(String email,String password)
	{
		String dpql = "select a from Admin a where a.adminEmail = ?1";
		Query query = em.createQuery(dpql);
		query.setParameter(1, email);
		
		Admin a = (Admin) query.getSingleResult();
		
		if(a != null)
		{
			if(password.equals(a.getAdminPassword()))
			{
				return a;
			}
			return null;
		}
		
		return null; 
	}
	
	public Admin updateAdmin(Admin admin,int id)
	{
		Admin a = em.find(Admin.class, id);
		
		if(a != null)
		{
			et.begin();
			em.merge(admin);
			et.commit();
		}
		
		return a;
	}
	public Admin deleteAdmin(int id)
	{
		Admin admin = em.find(Admin.class, id);
		
		if(admin != null)
		{
			et.begin();
			em.remove(admin);
			et.commit();
		}
		
		return null;
	}

}
