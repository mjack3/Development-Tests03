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



<b><spring:message code="instance.searchTicker" /></b>
<input type="text" class="form-control" style="width: 30%;" name="ticker" id="ticker">

	
<input type="button" class="btn btn-primary" value='<spring:message code='instance.search' />' onclick="search();">
<input onclick=" window.location = 'welcome/index.do'" class="btn btn-warning" type="button" name="cancel" value="<spring:message code="instance.cancel" />"/>


<script>

function search(){
	// comprobacion del género
	var ticker = $('#ticker').val();
	
		//Llamada POST 
		$.ajax({
		    url: 'instance/search.do',
		    type: "POST",
		    data: {'q':ticker},
		    success: function(result){
		   		document.location.href = 'instance/redirect.do';
		    }
		}); 

		
	
}

</script>
