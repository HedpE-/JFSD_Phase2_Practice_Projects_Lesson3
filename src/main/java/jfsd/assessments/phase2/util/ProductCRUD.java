package jfsd.assessments.phase2.util;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import jfsd.assessments.phase2.entities.Product;

public class ProductCRUD {
	private static SessionFactory factory = HibernateUtility.getSessionFactory();

	ProductCRUD() {
	}
	
	public static long count()
	{
		Session session = factory.openSession();
		long c = (long)session.createQuery("select count(pid) from Product").getSingleResult();
		return c;
	}

	public static Product getProductById(int pid)
	{
		Session session = factory.openSession();
		Product prod = session.get(Product.class, pid);
		session.close();
		return prod;
	}
	
	public static Product getProductByName(String pname)
	{
		Product prod = null;
		Session session = null;
		try {
			session = factory.openSession();
			Query<Product> query = session.createQuery("from Product where pname=:pname", Product.class);
			query.setParameter("pname", pname);
			prod = query.getSingleResult();	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(session != null && session.isOpen())
					session.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}	
		return prod;
	}
	
	public static List<Product> getProducts()
	{
		Session session = factory.openSession();
		List<Product> emps = session.createQuery("from Product", Product.class).list();
		session.close();
		return emps;
	}
	
	public static void addProduct(Product product)
	{
		SessionFactory factory = HibernateUtility.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(product);
		tx.commit();
		session.close();
	}
	
	public static void deleteProduct(Product product)
	{
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(product);
		tx.commit();
		session.close();
	}
	
	public static void updateProduct(Product product)
	{
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(product);
		tx.commit();
		session.close();
	}
}
