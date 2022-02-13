package jfsd.assessments.phase2.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jfsd.assessments.phase2.entities.Product;
import jfsd.assessments.phase2.util.HibernateUtility;
import jfsd.assessments.phase2.util.ProductCRUD;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCRUD_UnitTests {
	@BeforeClass
	public static void setup() {
		Session session = HibernateUtility.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();
		session.createSQLQuery("DROP TABLE IF EXISTS products").executeUpdate();
		session.createSQLQuery("CREATE TABLE `jfsd`.`products` (\n"
				+ "  `pid` INT NOT NULL AUTO_INCREMENT,\n"
				+ "  `pname` VARCHAR(50) NOT NULL,\n"
				+ "  `avgweight` FLOAT NULL,\n"
				+ "  `price` FLOAT NULL,\n"
				+ "  PRIMARY KEY (`pid`),\n"
				+ "  UNIQUE INDEX `pid_UNIQUE` (`pid` ASC) VISIBLE,\n"
				+ "  UNIQUE INDEX `pname_UNIQUE` (`pname` ASC) VISIBLE);").executeUpdate();
		tx.commit();

		tx.begin();
		Product p1 = new Product("Apple", 0.1f, 0.35f);
		Product p2 = new Product("Orange", 0.19f, 0.45f);
		Product p3 = new Product("Pear", 0.15f, 0.25f);
		Product p4 = new Product("Melon", 2f, 2.5f);

		session.save(p1);
		session.save(p2);
		session.save(p3);
		session.save(p4);
		tx.commit();

		session.close();
	}

	@Test
	public void TC01_getProductById_Test() {
		
		Product p1 = ProductCRUD.getProductById(2);

		assertTrue(p1.getPid() == 2 && p1.getPname().equals("Orange"));
	}

	@Test
	public void TC02_getProductByName_Test() {
		
		Product p1 = ProductCRUD.getProductByName("Pear");

		assertTrue(p1.getPid() == 3 && p1.getPname().equals("Pear"));
	}

	@Test
	public void TC03_getAndCountProducts_Test() {
		
		List<Product> prods = ProductCRUD.getProducts();
		Long count = ProductCRUD.count();

		assertTrue(count == prods.size());
	}

	@Test
	public void TC04_addProduct_Test() {

		Product p1 = new Product("Carrot", 0.10f, 0.05f);

		ProductCRUD.addProduct(p1);

		assertTrue(p1.getPid() > 0);
	}

	@Test
	public void TC05_updateProduct_Test() {

		Product p1 = ProductCRUD.getProductByName("Carrot");
		
		p1.setAvgWeight(0.25f);
		p1.setPrice(0.15f);

		ProductCRUD.updateProduct(p1);
		
		Product p2 = ProductCRUD.getProductByName("Carrot");

		assertTrue(p2 != null && p1.equals(p2));
	}

	@Test
	public void TC06_deleteProduct_Test() {

		Product p1 = ProductCRUD.getProductByName("Carrot");

		Long countBefore = ProductCRUD.count();
		ProductCRUD.deleteProduct(p1);
		Long countAfter = ProductCRUD.count();

		assertTrue(countBefore == countAfter+1);
	}
}
