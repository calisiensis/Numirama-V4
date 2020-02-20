<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>

<head>
 <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
	<title>Dodawanie monety</title>

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css">

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/add-coin-style.css">
	<style>
		.error {color: red}
	</style>
</head>

<body>
	
	<div id="wrapper">
		<div id="header">
			<h2>Numirama - portal numizmatyczny</h2>
		</div>
	</div>

	<div id="container">
		<h3>Zapisz monetę</h3>
	
		<form:form action="saveCoin" modelAttribute="coin" method="POST">

			<!-- need to associate this data with customer id -->
			<form:hidden path="id" />
					
			<table>
				<tbody>
					<tr>
						<td><label>Kraj</label></td>
						<td><form:input path="country" />
						<form:errors path="country" cssClass="error" />
						</td>
					</tr>

					<tr>
						<td><label>Waluta</label></td>
						<td><form:input path="currency" />
						<form:errors path="currency" cssClass="error" />						
						</td>
					</tr>

					<tr>
						<td><label>Nominał</label></td>
						<td><form:input path="nominal" />
						<form:errors path="nominal" cssClass="error" />
						</td>
						
					</tr>

					<tr>
						<td><label>Rok wybicia</label></td>
						<td><form:input path="year" />
						<form:errors path="year" cssClass="error" />
						</td>
					</tr>

					<tr>
						<td><label>Materiał</label></td>
						<td><form:input path="material" />
						<form:errors path="material" cssClass="error" />
						</td>
					</tr>

					<tr>
						<td><label>Średnica (mm)</label></td>
						<td><form:input path="radius" />
						<form:errors path="radius" cssClass="error" />
						</td>
					</tr>

					<tr>
						<td><label>Waga (g)</label></td>
						<td><form:input path="weight" />
						<form:errors path="weight" cssClass="error" />
						</td>
					</tr>

					<tr>
						<td><label>Stan</label></td>
						<td><form:input path="state" />
						<form:errors path="state" cssClass="error" />						
						</td>
					</tr>

					<tr>
						<td><label>Uwagi</label></td>
						<td><form:input path="comments" />
						<form:errors path="comments" cssClass="error" />							
						</td>
					</tr>

					<tr>
						<td><label>Cena</label></td>
						<td><form:input path="price" />
						<form:errors path="price" cssClass="error" />							
						</td>
					</tr>

					<tr>
						<td><label>Status</label></td>
						<td><form:input path="status" />
						<form:errors path="status" cssClass="error" />	
						</td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Zapisz" class="save" /></td>
					</tr>
				</tbody>
			</table>
		
		
		</form:form>
	
		<div style="clear; both;"></div>
		
		<p>
			<a href="${pageContext.request.contextPath}/coin/list">Powrót do spisu</a>
		</p>
	
	</div>

</body>

</html>










