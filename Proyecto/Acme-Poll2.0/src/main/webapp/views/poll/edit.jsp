<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>



<jstl:set var="url" value="poll/poller/save.do" />

<form:form action="${url}" modelAttribute="poll">

		
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="questions" />
		<form:hidden path="instances" />
		<form:hidden path="poller" />
		<form:hidden path="hints" />


	    <div class="form-group" style="width: 20%;"> 
	    
		<label> <spring:message code="poll.title"/> </label>
		<br />
		<input class="form-control" value="${poll.title}" type="text" name="title"/>
		<form:errors cssClass="error" path="title" /> <br />
		
		<label> <spring:message code="poll.description"/> </label><br />
		<textarea rows="3" cols="40" name="description" id="description">${poll.description}</textarea>
		<form:errors cssClass="error" path="description" /> <br />
		
		<label> <spring:message code="poll.banner"/> </label><br />
		<input class="form-control" value="${poll.banner}" type="text" name="banner"/>
		<form:errors cssClass="error" path="banner" /> <br />
		
		<label> <spring:message code="poll.startDate"/> </label><br />
		<input class="form-control" value="${poll.startDate}" type="date" name="startDate"/>
		<form:errors cssClass="error" path="startDate" /> <br />
		
		<label> <spring:message code="poll.endDate"/> </label><br />
		<input class="form-control" value="${poll.endDate}" type="date" name="endDate"/>
		<form:errors cssClass="error" path="endDate" /> <br />
		
		
		</div>
		
		<spring:message code="actor.save" var="actorSaveHeader"/>
		<spring:message code="actor.cancel" var="actorCancelHeader"/>
		<input type="submit" class="btn btn-primary" name="save" value="${actorSaveHeader}" />
		<input onclick="window.history.back()" class="btn btn-warning" type="button" name="cancel" value="${actorCancelHeader}"/>
		
		
	</form:form>






