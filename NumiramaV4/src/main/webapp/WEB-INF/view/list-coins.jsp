<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html>

<head>
 <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
	<title>Numirama - Spis monet</title>

	
	<!-- reference our style sheet -->

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Numirama - portal numizmatyczny - wersja 3.0 by TomIT</h2>
			<br/>
			<h3>Liczba monet w bazie: ${fn:length(totalCoins)}</h3>
			</br>
			<form:form action="${pageContext.request.contextPath}/logout" method="POST">
				<input type="submit" value="Wyloguj" class="logout-button" />
			</form:form>

		</div>
	</div>
	
	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add Coin -->
		
			<input type="button" value="Dodaj monetę"
				   onclick="window.location.href='showFormForAdd'; return false;"
				   class="add-button"/>
			<form:form action="search" method="GET">
				<input type="submit" value="Szukaj" class="add-button" />
				<input type="text" name="searchedText" />
			</form:form>
		
			<!--  add our html table here -->
		
			<table>
				<tr>
					<th>ID</th>
					<th>Kraj</th>
					<th>Waluta</th>
					<th>Nominał</th>
					<th>Rok wybicia</th>
					<th>Materiał</th>
					<th>Średnica (mm)</th>
					<th>Waga (g)</th>
					<th>Stan</th>
					<th>Uwagi</th>
					<th>Cena</th>
					<th>Status</th>
					<th>Czynność</th>

				</tr>
				
				<!-- loop over and print our coins -->
				<c:forEach var="tempCoin" items="${coins}">
				
					<!-- construct an "update" link with customer id -->
					<c:url var="updateLink" value="/coin/showFormForUpdate">
						<c:param name="coinId" value="${tempCoin.id}" />
					</c:url>					

					<!-- construct an "delete" link with customer id -->
					<c:url var="deleteLink" value="/coin/delete">
						<c:param name="coinId" value="${tempCoin.id}" />
					</c:url>					
					
					<tr>
						<td> ${tempCoin.id} </td>
						<td> ${tempCoin.country} </td>
						<td> ${tempCoin.currency} </td>
						<td><fmt:formatNumber type="number"	value="${tempCoin.nominal}" minFractionDigits="2" maxFractionDigits="2" /></td>
						<td> ${tempCoin.year} </td>
						<td> ${tempCoin.material} </td>
						<td><fmt:formatNumber type="number"	value="${tempCoin.radius}" minFractionDigits="2" /></td>
						<td><fmt:formatNumber type="number" value="${tempCoin.weight}" minFractionDigits="2" /></td>
						<td> ${tempCoin.state} </td>
						<td> ${tempCoin.comments} </td>
						<td><fmt:formatNumber type="number" value="${tempCoin.price}" minFractionDigits="2" maxFractionDigits="2" /> </td>
						<td> ${tempCoin.status} </td>
						
						<td>
							<!-- display the update link -->
							<a href="${updateLink}">Edytuj</a>
							|
							<a href="${deleteLink}"
							   onclick="if (!(confirm('Na pewno chcesz usunąć pozycję?'))) return false">Usuń</a>
						</td>
						
					</tr>
				
				</c:forEach>
						
			</table>
			
					<!-- Add Pagination Controls -->
			
			<!-- Handle "First" and "Prev" links -->
			<c:choose>
				<c:when test="${currentPage == 1}">
					<a href="#" class="disabled">Pierwsza</a>
					<a href="#" class="disabled">Poprzednia</a>
				</c:when>
				<c:otherwise>
					<c:url var="firstPageLink" value="/coin/list">
						<c:param name="pageNumber" value="1" />
					</c:url>		
					<a href="${firstPageLink}">Pierwsza</a>

					<c:url var="prevPageLink" value="/coin/list">
						<c:param name="pageNumber" value="${currentPage - 1}" />
					</c:url>
					<a href="${prevPageLink}">Poprzednia</a>
				</c:otherwise>
			</c:choose>
			
			<!-- Handle Page Number links -->
			<c:forEach begin="1" end="${totalCoinsQuantity}" step="${pageSize}" varStatus="loop">
				<c:choose>
				
					<c:when test="${loop.count > (currentPage - 5) && loop.count <= (currentPage + 5) || (currentPage <= 5 && loop.count <= 10) || pages <=10}">

						<c:url var="pageLink" value="/coin/list">
							<c:param name="pageNumber" value="${loop.count}" />
						</c:url>

						<c:choose>
							<c:when test="${loop.count == currentPage}">
								<a href="${pageLink}" class="selected-page">${loop.count}</a>
							</c:when>
							<c:otherwise>
								<a href="${pageLink}">${loop.count}</a>
							</c:otherwise>
						</c:choose>

					</c:when>
				
				</c:choose>
			</c:forEach>
			
			<!-- Handle "..." if there are more pages -->
			<c:choose>
				<c:when test="${currentPage < (pages - 5) && (pages > 10)}">
					...
				</c:when>
			</c:choose>
			
			<!-- Handle "Next" and "Prev" links -->
			<c:choose>
			
				<c:when test="${currentPage == pages}">
					<a href="#" class="disabled">Następna</a>
					<a href="#" class="disabled">Ostatnia</a>
				</c:when>
				
				<c:otherwise>
					<c:url var="nextPageLink" value="/coin/list">
						<c:param name="pageNumber" value="${currentPage + 1}" />
					</c:url>
					<a href="${nextPageLink}">Następna</a>

					<c:url var="lastPageLink" value="/coin/list">
						<c:param name="pageNumber" value="${pages}" />
					</c:url>		
					<a href="${lastPageLink}">Ostatnia</a>
				</c:otherwise>
				
			</c:choose>
				
		</div>
	
	</div>
	

</body>

</html>









