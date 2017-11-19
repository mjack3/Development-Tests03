<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<jstl:set value="0" var="j" />

<jstl:forEach items="${question }" var="q">



<b><jstl:out value="${q.number}: ${q.statment } " /><br /></b>

	<jstl:forEach var="i" begin="0" end="${q.choices.size() -1 }">
		<jstl:out value="${q.choices.get(i)}: ${results.get(j) }" /> <br/>
		<jstl:set value="${j+1 }" var="j" />
	</jstl:forEach>
	

	
</jstl:forEach>

	


<script>

</script>