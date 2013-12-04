<%@ tag language="java" pageEncoding="US-ASCII" dynamic-attributes="dynamo"%>
<%@ attribute name="name" description="" required="true"%>
<%@ attribute name="hidden" description="" required="false"%>
<%@ attribute name="clazz" description="" required="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags"  prefix="b"%>

<c:forEach items="${dynamo}" var="next">
<c:set var="allAttributes" >
${allAttributes} ${next.key}="${next.value}"
</c:set>
</c:forEach>

<c:choose>

	<%-- hidden field --%>
	<c:when test="${hidden}">
		<form:hidden path="${name }" />
	</c:when>
	
	<%-- visible field  --%>
	<c:otherwise>
		<fieldset   >
			<label for="${name }"  ${allAttributes} >${name }:</label>
			<form:input path="${name }" />
			<form:errors path="${name }" cssStyle="color:red" />
		</fieldset>
	</c:otherwise>
	
</c:choose>
