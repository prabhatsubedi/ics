<%@page import="java.awt.print.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <p>It seems that Server is running Good !!!</p>
 <a href="screenCapture">Screen Capture</a>
 <br>
 <h1>Shopping Cart</h1>
 <form action="shoppingCart">
 	<p>Item : <select name="item"><option value="car">Car</option><option value="bike">Bike</option></select></p>
 	<input type="hidden" value="1" name="choice"> 
 	<input type="submit" value="Submit">
 </form>
 <a href="shoppingCart?choice=2&client=hari">Check List</a>
 <% 
 if(request.getSession().getAttribute("cartList") != null){
	 List<String> bookList = (List<String>) session.getAttribute("cartList"); %>
	<ol><% for(int i =0; i<bookList.size(); i++){%>
		<li> <%=bookList.get(i) %> </li>
	<% }  %></ol> 
 <% } %>
</body>
</html>

