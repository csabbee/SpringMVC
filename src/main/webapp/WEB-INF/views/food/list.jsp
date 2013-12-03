<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib tagdir="/WEB-INF/tags"  prefix="b" %>
<ul>
<c:forEach var="food" items="${foodList}">
<c:url var="deleteUrl"  value="/food/delete">
  <c:param name="foodId" value="${food.id}"></c:param>
</c:url>
<c:url var="editUrl"  value="/food/edit">
  <c:param name="foodId" value="${food.id}"></c:param>
</c:url>

  <li>${food.name} : ${food.price} <a href="${editUrl}">Edit</a> <a href="${deleteUrl}">Delete</a> </li>
</c:forEach>
</ul>


<c:url var="saveUrl" value="/food/${action}" />
<form:form modelAttribute="food" action="${saveUrl}">
  <b:input name="id" hidden="true"/>
  <b:input name="name" />
  <b:input name="price" />
  <form:button>${action }</form:button>
</form:form>