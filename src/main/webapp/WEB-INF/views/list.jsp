<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2>List</h2>

<div style="border: 2px solid black; font-size: xx-large;">
	Minutes left: ${minutes}
</div>

<ul>
  <c:forEach var="next" items="${breakList}">
  	<c:url var="deleteUrl" value="/delete">
  	  <c:param name="break" value="${next}"></c:param>
    </c:url>
    <li>${next} <a href="${deleteUrl}">x</a></li> 
  </c:forEach>
</ul>
todo...