<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags"  prefix="b"%>

<h3>v0.4</h3>

<div>
	DELIVERY = ${address}
</div>

<hr/>
<c:url var="setUrl" value="/address/set" />
<form action="${setUrl }" method="post">
  <b:input name="street" />  
  <b:input name="zip" />  
  <b:input name="city" />  

  <button type="submit">Set</button>
  
</form>
