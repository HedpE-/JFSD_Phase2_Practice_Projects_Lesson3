package jfsd.assessments.phase2.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;

import jfsd.assessments.phase2.entities.Product;
import jfsd.assessments.phase2.util.ProductCRUD;

/**
 * Servlet Filter implementation class AddProductFormDataValidationFilter
 */
public class AddProductFormDataValidationFilter implements Filter {

	/**
	 * Default constructor. 
	 */
	public AddProductFormDataValidationFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			String pname = request.getParameter("pname");
			String avgWeightStr = request.getParameter("avgWeight");
			String priceStr = request.getParameter("price");

			List<String> errors = validateFormData(pname, avgWeightStr, priceStr);

			if(errors.size() > 0)
				throw new Exception("- " + String.join("<br/>- ", errors));

			request.setAttribute("validProduct",  new Product(pname, Float.parseFloat(avgWeightStr), Float.parseFloat(priceStr)));
			chain.doFilter(request, response);
		}
		catch(Exception e) {
			PrintWriter out = response.getWriter();

			request.getRequestDispatcher("index.jsp").include(request, response);
			out.println("<span style='color:red'>Invalid Form data detected:<br/>"+e.getMessage()+"</span>");
		}
	}

	private List<String> validateFormData(String pname, String avgWeightStr, String priceStr) {
		List<String> errors = new ArrayList<String>();
		
		float avgWeight = tryParseFloat(avgWeightStr, -1);
		float price = tryParseFloat(priceStr, -1);
		
		if(pname == null || pname.equals(""))
			errors.add("Name cannot be empty");
		else {
			Product existing = ProductCRUD.getProductByName(pname);
			if(existing != null)
				errors.add("A Product with the same name already exists. "+existing);
		}
		if(avgWeightStr == null ||  avgWeightStr == "" || avgWeight <= 0)
			errors.add("Average weight value must be greater than 0");
		if(priceStr == null ||  priceStr == "" || price <= 0)
			errors.add("Price value must be greater than 0");
		
		return errors;
	}
	
	private float tryParseFloat(String value, float defaultValue) {
		try {
			float parsed = Float.parseFloat(value);
			return parsed;
		}
		catch (Exception e) {
			e.printStackTrace();
			return defaultValue;
		}
	}
}
