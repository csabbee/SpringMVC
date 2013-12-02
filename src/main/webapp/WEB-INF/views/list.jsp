<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<link href="https://gist.github.com/lalyos/06fe35683579fb60b440/raw/alert.css" rel="stylesheet" />

<div class="alert">
	Minutes left: ${minutes }
</div>

<h2>list</h2>
<ul>
  <c:forEach var="next" items="${breakList}">
  <c:url  var="deleteUrl" value="/delete">
  	<c:param name="break" value="${next }" />
  </c:url>
  
    <li>${next } <a href="${deleteUrl }">x</a></li>
  </c:forEach>  
</ul>

<c:url var="addUrl" value="/add" />
<form action="${addUrl }" method="post">
  <fieldset>
    <label for="break">Break:</label>
    <input id="break" type="text" name="break" />
    <button type="submit">Add</button>
  </fieldset>
</form>
