<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<div style="border: 2px solid black; font-size: xx-large;">
	Minutes left: ${minutes }
</div>

<h2>list</h2>
<ul>
  <c:forEach var="next" items="${breakList}">
    <li>${next }</li>
  </c:forEach>  
</ul>

