<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib tagdir="/WEB-INF/tags"  prefix="b" %>
<%@include file="/WEB-INF/views/common/header.jsp" %>

<c:if test="${not empty flashMsg}">
<div class="alert alert-${flashType}">
  ${flashMsg}
</div>
	
</c:if>

<h3>table v0.91</h3>
<table class="table table-striped">
<thead>
    <tr>
      <th>food</th>
      <th>price</th>
      <th>action</th>
    </tr>
  </thead>
<c:forEach var="food" items="${foodList}">
<c:url var="deleteUrl"  value="/food">
  <c:param name="foodId" value="${food.id}"></c:param>  
</c:url>
<c:url var="editUrl"  value="/food-edit-form">
  <c:param name="foodId" value="${food.id}"></c:param>
</c:url>
  <tr>
  <td>${food.name} </td>
  <td> ${food.price} </td> 
  <td>
		    <a href="${editUrl }" class="btn btn-info" ><i class="icon-pencil icon-white"></i></a> 
    

    <form:form action="${deleteUrl}" method="delete">  
		    <button class="btn btn-danger"><i class="icon-remove icon-white"></i></button> 
    </form:form>

  </td>
  <tr>
</c:forEach>
</table>


<c:url var="saveUrl" value="/food" />
<form:form modelAttribute="food" action="${saveUrl}" method="${method}" >
  <b:input name="id" hidden="true"/>
  <b:input name="name" />
  <b:input name="price" />
  <button class="btn btn-success">${action }</button>
</form:form>

<%@include file="/WEB-INF/views/common/footer.jsp" %>
