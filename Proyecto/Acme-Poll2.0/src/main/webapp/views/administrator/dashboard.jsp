<%--
 * action-1.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


				
		<spring:message code="dashboard.findMinAvgStdMaxPollsByPoller" var="dash1" />	
		<h4><jstl:out value="${dash1}" />:</h4>
		<jstl:out value="${findMinAvgStdMaxPollsByPoller}" />		
		
		<br/><br/>
		
		<spring:message code="dashboard.findMinAvgStdMaxInstancesByPoll" var="dash1" />	
		<h4><jstl:out value="${dash1}" />:</h4>
		<jstl:out value="${findMinAvgStdMaxInstancesByPoll}" />		
		
		<br/><br/>
		
		<spring:message code="dashboard.findMinAvgStdMaxQuestionByPoll" var="dash1" />	
		<h4><jstl:out value="${dash1}" />:</h4>
		<jstl:out value="${findMinAvgStdMaxQuestionByPoll}" />		
		
		<br/><br/>
		
		<spring:message code="dashboard.avgMaxMinAmountToBePaid" var="dash1" />	
		<h4><jstl:out value="${dash1}" />:</h4>
		<jstl:out value="${avgMaxMinAmountToBePaid}" />
		
		<br/><br/>
		
		<spring:message code="dashboard.ratioBillsHaveBeenEndorsed" var="dash1" />	
		<h4><jstl:out value="${dash1}" />:</h4>
		<jstl:out value="${ratioBillsHaveBeenEndorsed}" />
		
		<br/><br/>
		
		<spring:message code="dashboard.ratioBillsHaveToBeEndorsed" var="dash1" />	
		<h4><jstl:out value="${dash1}" />:</h4>
		<jstl:out value="${ratioBillsHaveToBeEndorsed}" />
		
		<br/><br/>
		
		<spring:message code="dashboard.ratioBillsOverdue" var="dash1" />	
		<h4><jstl:out value="${dash1}" />:</h4>
		<jstl:out value="${ratioBillsOverdue}" />
		
		<br/><br/>
		
		<spring:message code="dashboard.avgFoldersPerActor" var="dash1" />	
		<h4><jstl:out value="${dash1}" />:</h4>
		<jstl:out value="${avgFoldersPerActor}" />
		
		<br/><br/>
		
		<spring:message code="dashboard.avgSystemFolders" var="dash1" />	
		<h4><jstl:out value="${dash1}" />:</h4>
		<jstl:out value="${avgSystemFolders}" />
		
		<br/><br/>
		
		<spring:message code="dashboard.avgSpamMessagesPerActor" var="dash1" />	
		<h4><jstl:out value="${dash1}" />:</h4>
		<jstl:out value="${avgSpamMessagesPerActor}" />
		
		<br/><br/>
		
		<spring:message code="dashboard.avgEditPerInstance" var="dash1" />	
		<h4><jstl:out value="${dash1}" />:</h4>
		<jstl:out value="${avgEditPerInstance}" />
		
		<br/><br/>
		
		<spring:message code="dashboard.pollInstanceMostEdited" var="dash1" />	
		<h4><jstl:out value="${dash1}" />:</h4>
		<jstl:forEach items="${pollInstanceMostEdited}" var="a">
			${a.title } ,
		</jstl:forEach>
		