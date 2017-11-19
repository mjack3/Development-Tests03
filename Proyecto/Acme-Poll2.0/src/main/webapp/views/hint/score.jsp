<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<spring:message code="hint.state" /> 
<input type="number" min="0" max="10" step="1" id="mark">

<br/>
<br/>

<spring:message code="actor.save" var="actorSaveHeader"/>
<spring:message code="actor.cancel" var="actorCancelHeader"/>

<input type="button" onclick="call();" class="btn btn-primary" name="save" value="${actorSaveHeader}" />
<input onclick="window.history.back()" class="btn btn-warning" type="button" name="cancel" value="${actorCancelHeader}"/>
		
<spring:message code="hint.alert" var="alert"/>

<script>


function call(){
	if($("#mark").val() < 0 || $("#mark").val() > 10){
		alert("${alert}");
	}else{
		//Llamada POST 
		$.ajax({
		    url: 'hint/score.do',
		    type: "POST",
		    data: {'q':${hint},'t':$("#mark").val()}
		}); 
		document.location.href = 'hint/list.do?q=${pollId}';
	}
	
	
}

</script>

