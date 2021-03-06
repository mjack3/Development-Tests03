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



<b><spring:message code="answer.name" /></b>
<input type="text" class="form-control" style="width: 30%;" name="name" id="name">

<br/>

<b><spring:message code="answer.gender" /></b>

<input type="text" id="gender" class="form-control" style="width: 30%;" name="gender" />
<i><spring:message code="answer.regex"/></i>

<br/>
<br/>

<b><spring:message code="answer.city" /></b>

<input type="text" class="form-control" style="width: 30%;" name="city"/>

<br/>
<br/>

<jstl:forEach begin="0" end="${question.size()-1}" var="a">
	
	<b>${question.get(a).number}. ${question.get(a).statment}</b>

	<br />
	
	<div class="btn-group" data-toggle="buttons">
	<jstl:forEach begin="0" end="${question.get(a).choices.size()-1}" var="i">

		<input type="radio" value="${i}" name="radio${a}" >${question.get(a).choices.get(i)}
		<br/>

	</jstl:forEach>
	</div>
	<br/>

</jstl:forEach>

<spring:message code="actor.cancel" var="actorCancelHeader"/>
		
<input type="button" class="btn btn-primary" value='<spring:message code='answer.save' />' onclick="save();">
<input onclick=" window.location = 'poll/list.do'" class="btn btn-warning" type="button" name="cancel" value="${actorCancelHeader}"/>


<spring:message code="answer.alert" var="alert" />
<spring:message code="must.be.gender" var="alert1" />
<spring:message code="answer.commit.error2" var="alert2" />

<script>

function save(){
	
	//Comprobacion de que haya respondido todas las preguntas y rellenado todos los campos
	if($( ":input:checked" ).length == ${question.size()}){
		
		// comprobacion del g�nero
		var val1 = $('#gender').val();
		
		if(val1 === "HOMBRE" || val1 === "MUJER" || val1 ==="MALE" || val1 ==="FEMALE" || val1 === ""){
		
		
		var res = "";
		
		//Recorro todos los input que estan marcados
		$( ":input:checked" ).each(function() { 
			res = res + "," + $(this).attr('value');
		});
		

		//Llamada POST 
		$.ajax({
		    url: 'answer/save.do',
		    type: "POST",
		    data: {'data':res,'city':$('[name="city"]').val(),'gender':$('[name="gender"]').val(),'name':$('[name="name"]').val()},
		    success: function(result){
		    	//Si el resultado contiene el titulo del listado de poll es que es correcto
		    	if(result !=null && (result.includes('Polls list') || result.includes('Lista de encuestas'))){
		    		//Redireccion
		   		 	document.location.href = 'answer/correctSave.do';
		    	}else{
		    		alert("${alert2}");
		    	}
		    }
		}); 

		
		}else{
			alert("${alert1}");
		}
		
		
	}else{
		alert("${alert}");
	}
	
}

</script>
