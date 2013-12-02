<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<h2>list</h2>

<ul>
  <c:forEach var="next" items="${breakList}">
    <li>${next }</li>
  </c:forEach>  
</ul>

