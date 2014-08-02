package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejb.shoppingcart.service.IItemServiceDAO;

public class ShoppingCart extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String itemName = request.getParameter("item");
		System.out.println("Item Name: "+itemName);
		try{
			//http://www.baeldung.com/2011/12/08/simplifying-the-data-access-layer-with-spring-and-java-generics/
			//http://www.baeldung.com/2011/12/22/the-persistence-layer-with-spring-data-jpa/
			  final String CART_SESSION_KEY = request.getSession(true).getId();
			  IItemServiceDAO itemServiceDAO = (IItemServiceDAO) request.getSession().getAttribute(CART_SESSION_KEY);
			  if(itemServiceDAO == null){
				      // EJB is not yet in the HTTP session
	 	              // This means that the client just sent his first request
	 	              // We obtain a CartBean instance and add it to the session object.
	 	              try {
	 	            	  InitialContext ic = new InitialContext();
	 	            	 itemServiceDAO = (IItemServiceDAO) ic.lookup("java:comp/ItemServiceDAO");
		 	              request.getSession().setAttribute(CART_SESSION_KEY, itemServiceDAO);
		 	              System.out.println("Shopping Cart created");
	 	 
	 	              } catch (NamingException e) {
	 	                throw new ServletException(e);
	 	              }
	 	         }
			  List<String> bookList = null;
			    Integer choice = 0;
				choice = Integer.parseInt(request.getParameter("choice"));
				if(choice == 1){
					System.out.println("Client: "+CART_SESSION_KEY);
					itemServiceDAO.addToList(itemName);
					bookList = itemServiceDAO.itemsFromList();
					
				}else if(choice == 2){
					bookList = itemServiceDAO.itemsFromList();
					System.out.println("No. Of Books: "+bookList.size());
				}else{
					System.out.println("Bad Choice...");
				}
				request.getSession().setAttribute("cartList", bookList);
				response.sendRedirect("../ServletPlusEJB/index.jsp");
				
			}catch(Exception e){
				System.out.println("Exception : "+e);
			}		
	}
}
