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

<form:form action="instance/save" modelAttribute="instance">

		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="poll" />
		<form:hidden path="answers" />
		
		<label> <spring:message code="instance.name"/> </label>
		<br />
		<input class="form-control" value="${instance.name}" type="text" name="name"/>
		<form:errors cssClass="error" path="name" /> <br />
		
		<label> <spring:message code="instance.gender"/> </label>
		<br />
		<input class="form-control" value="${instance.gender}" type="text" name="gender"/>
		<form:errors cssClass="error" path="gender" /> <br />
		
		<label> <spring:message code="instance.city"/> </label>
		<br />
		<input class="form-control" value="${instance.city}" type="text" name="city"/>
		<form:errors cssClass="error" path="city" /> <br />
		
		
</form:form>