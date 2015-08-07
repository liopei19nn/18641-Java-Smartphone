<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	double totalCost = 0;
	String model = ""; /*store model name*/
	String basePrice = ""; /*store base price*/

	/*store choice name and price*/
	String color = "", transmission = "", abs = "", airbags = "", moonroof = "", colorPrice = "",
			transmissionPrice = "", absPrice = "", airbagsPrice = "", moonroofPrice = "";

	String[] storeString = null; /* store name-valuePair split output */
	/*get model=baseprice pair from session*/
	String modelandbaseprice = (String) session.getAttribute("modelbaseprice");
	if (modelandbaseprice != null) {
		storeString = modelandbaseprice.split("=");
		model = storeString[0];
		basePrice = storeString[1];

		totalCost = Float.parseFloat(basePrice);
	} else {
		model = "";
		basePrice = "";
		totalCost = Float.parseFloat(basePrice);
	}
	
	
	/*get color and price from form action*/
	String colorPair = request.getParameter("color");
	storeString = colorPair.split("=");
	color = storeString[0];
	colorPrice = storeString[1];
	totalCost += Float.parseFloat(colorPrice);
	
	
	/*get transmission and price from form action*/
	String transmissionPair = request.getParameter("transmission");
	storeString = transmissionPair.split("=");
	transmission = storeString[0];
	transmissionPrice = storeString[1];
	totalCost += Float.parseFloat(transmissionPrice);
	
	/*get abs-traction and price from form action*/
	String absPair = request.getParameter("abs");
	storeString = absPair.split("=");
	abs = storeString[0];
	absPrice = storeString[1];
	totalCost += Float.parseFloat(absPrice);

	/*get side impact air bag and price from form action*/
	String airbagPair = request.getParameter("airbag");
	storeString = airbagPair.split("=");
	airbags = storeString[0];
	airbagsPrice = storeString[1];
	totalCost += Float.parseFloat(airbagsPrice);
	
	/*get moonroof and price from form action*/
	String moonroofPair = request.getParameter("moonroof");
	storeString = moonroofPair.split("=");
	moonroof = storeString[0];
	moonroofPrice = storeString[1];
	totalCost += Float.parseFloat(moonroofPrice);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Get Price</title>
</head>
<body BGCOLOR="#FDF5E6">
	<CENTER>
		<h3>You have successfully configured a car!</h3>
		<table border="1" cellpadding="10" width="400">
			<tr>
				<td><%=model%></td>
				<td>Base Price</td>
				<td><%=basePrice%></td>
			</tr>
			<tr>
				<td>Color</td>
				<td><%=color%></td>
				<td><%=colorPrice%></td>
			</tr>
			<tr>
				<td>Transmission</td>
				<td><%=transmission%></td>
				<td><%=transmissionPrice%></td>
			</tr>
			<tr>
				<td>ABS/Traction Control</td>
				<td><%=abs%></td>
				<td><%=absPrice%></td>
			</tr>
			<tr>
				<td>Side Impact Air Bags</td>
				<td><%=airbags%></td>
				<td><%=airbagsPrice%></td>
			</tr>
			<tr>
				<td>Power Moonroof</td>
				<td><%=moonroof%></td>
				<td><%=moonroofPrice%></td>
			</tr>
			<tr>
				<td><b>Total Cost</b></td>
				<td></td>
				<td><%=totalCost%></td>
			</tr>
		</table>
	</CENTER>
</body>
</html>