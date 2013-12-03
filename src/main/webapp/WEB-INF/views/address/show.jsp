<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags"  prefix="b"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<h3>v0.4</h3>

<div>
	DELIVERY = ${sessionScope.address}
</div>

<hr/>
<c:url var="setUrl" value="/address/set" />

<form:form modelAttribute="address" action="${setUrl}">

  
  <b:input name="street" />
  <b:input name="city" />
  <b:input name="zip" />
	<br/><form:button >set</form:button>
</form:form>
  
<b:debug map="${requestScope}" title="REQ"></b:debug>