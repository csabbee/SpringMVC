<%@ tag language="java" pageEncoding="US-ASCII"%>
<%@ attribute name="name" description="" required="true"%>
<%@ attribute name="hidden" description="" required="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:choose>

	<%-- hidden field --%>
	<c:when test="${hidden}">
		<form:hidden path="${name }" />
	</c:when>
	
	<%-- visible field  --%>
	<c:otherwise>
		<fieldset>
			<label for="${name }">${name }:</label>
			<form:input path="${name }" />
			<form:errors path="${name }" cssStyle="color:red" />
		</fieldset>
	</c:otherwise>
	
</c:choose>
