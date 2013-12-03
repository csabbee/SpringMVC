<%@ tag language="java" pageEncoding="US-ASCII"%>
<%@ attribute name="name" description="" required="true" %>
<%@taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>

   <fieldset>
    <label for="${name }">${name }:</label>
    <form:input path="${name }"/> <form:errors path="${name }" cssStyle="color:red"/>   
  </fieldset>
