package jfsd.assessments.phase2.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity(name="Product")
@Table(name="products", schema="jfsd")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "pid", updatable = false, nullable = false)
	private int pid;
	@Column(name = "pname", nullable = false, unique = true)
	private String pname;
	@Column(name="avgweight")
	@ColumnDefault("0f")
	private float avgWeight;
	@ColumnDefault("0f")
	private float price;
	
	public Product() {
		
	}
	
	public Product(int pid, String pname, float avgWeight, float price) {
		this(pname, avgWeight, price);
		this.pid = pid;
	}
	
	public Product(String pname, float avgWeight, float price) {
		this.pname = pname;
		this.avgWeight = avgWeight;
		this.price = price;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public double getAvgWeight() {
		return avgWeight;
	}

	public void setAvgWeight(float avgWeight) {
		this.avgWeight = avgWeight;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getPid() {
		return pid;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", avgWeight=" + avgWeight + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(avgWeight, pid, pname, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return pid == other.pid && Objects.equals(pname, other.pname)
				&& Float.floatToIntBits(avgWeight) == Float.floatToIntBits(other.avgWeight)
				&& Float.floatToIntBits(price) == Float.floatToIntBits(other.price);
	}
}
