package jfsd.assessments.phase2.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jfsd.assessments.phase2.entities.Product;
import jfsd.assessments.phase2.util.ProductCRUD;

/**
 * Servlet implementation class AddProductServlet
 */
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AddProductServlet() {
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Product prod = (Product)request.getAttribute("validProduct");
		PrintWriter writer = response.getWriter();
		RequestDispatcher rd = null;
		
		try{
			ProductCRUD.addProduct(prod);
			writer.println("<center><span style='color:green'>Product added successfully</span></center>");
//			writer.print(prod);
			
			rd = request.getRequestDispatcher("viewProduct.jsp");
			request.setAttribute("product", prod);
			rd.include(request, response);
			writer.print("<center><a href='index.html' class='btn btn-primary'>Add another product</a></center>");
		}
		catch(Exception e) {
			rd = request.getRequestDispatcher("index.html");
			rd.include(request,  response);
			writer.println("<br/><center><span style='color:red'>Failed to add product to database</span></center>");
			e.printStackTrace();
		}
	}
}
