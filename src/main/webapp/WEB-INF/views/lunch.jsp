<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="b"%>
<h2>Lunch</h2>
Minutes left : ${minutes}

<b:debug map="${pageScope}" title="page"/>
<b:debug map="${requestScope}" title="request"/>
<b:debug map="${applicationScope}" title="application"/>
