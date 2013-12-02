<%@ tag language="java" pageEncoding="US-ASCII"%>
<%@ attribute name="name" description="" required="true" %>

<hr/>
post: ${pageContext.request.method == 'post'}
   <fieldset>
    <label for="${name }">${name }:</label>
    <input id="${name }" type="text" name="${name }" value="${pageContext.request.method == 'POST' ? param[name] : sessionScope.address[name]}"/>
  </fieldset>
