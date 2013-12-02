<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="map" description="" required="true"
	type="java.util.Map"%>
<%@ attribute name="title" description="" required="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty title}">
	<h2>${title}</h2>
</c:if>
<c:choose>
	<c:when test="${empty map}">
		is empty
	</c:when>
	<c:otherwise>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Key</th>
					<th>Value</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${map}" var="entry">
					<tr>
						<td>${entry.key}</td>
						<td>${entry.value}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:otherwise>
</c:choose>