<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<security:authorize access="isAuthenticated()">

	<security:authorize access="hasRole('POLLER')">
		<jstl:set var="url" value="poller/save-poller.do" />
	</security:authorize>

<form:form action="${url}" modelAttribute="poller">

		
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="userAccount.username" />
		<form:hidden path="userAccount.password" />
		<form:hidden path="userAccount.authorities" />
		<form:hidden path="userAccount.banned" />
		<form:hidden path="chirps" />
		
		<security:authorize access="hasRole('POLLER')">
			<form:hidden path="polls" />
		</security:authorize>


	    <div class="form-group" style="width: 20%;"> 
	    
		<label> <spring:message code="poller.actorName"/> </label>
		<br />
		<input class="form-control" value="${poller.name}" type="text" name="name"/>
		<form:errors cssClass="error" path="name" /> <br />
		
		<label> <spring:message code="poller.surname"/> </label><br />
		<input class="form-control" value="${poller.surname}" type="text" name="surname"/>
		<form:errors cssClass="error" path="surname" /> <br />
		
		<label> <spring:message code="poller.email"/> </label><br />
		<input class="form-control" value="${poller.email}" type="text" name="email"/>
		<form:errors cssClass="error" path="email" /> <br />
		
		<label> <spring:message code="poller.phone"/> </label><br />
		<input class="form-control" value="${poller.phone}" type="text" name="phone"/>
		<form:errors cssClass="error" path="phone" /> <br />
		
		
		<label> <spring:message code="poller.address"/> </label><br />
		<input class="form-control" value="${poller.address}" type="text" name="address"/>
		<form:errors cssClass="error" path="address" /> <br />
		
		
		</div>
		
		<spring:message code="actor.save" var="actorSaveHeader"/>
		<spring:message code="actor.cancel" var="actorCancelHeader"/>
		<input type="submit" class="btn btn-primary" name="save" value="${actorSaveHeader}" />
		<input onclick="window.history.back()" class="btn btn-warning" type="button" name="cancel" value="${actorCancelHeader}"/>
		
		
	</form:form>

</security:authorize>





