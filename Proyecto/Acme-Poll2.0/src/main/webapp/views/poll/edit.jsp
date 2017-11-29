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

<%-- <acme:acme_form url="${url}" entity="${poll}" hiddenFields="id,version,questions,instances,poller,hints" type="edit">
</acme:acme_form>
 --%>



<form:form modelAttribute="poll" action="${url }">

<form:hidden path="id"/>
<form:hidden path="version"/>
<form:hidden path="questions"/>
<form:hidden path="instances"/>
<form:hidden path="poller"/>
<form:hidden path="hints"/>

<acme:textbox2 readonly="true" code="poll.ticket" path="ticket"/>
<acme:textbox2 code="poll.title" path="title"/>
<acme:textbox2 code="poll.description" path="description"/>
<acme:textbox2 code="poll.banner" path="banner"/>
<acme:textbox2 code="poll.startDate" path="startDate"/>
<acme:textbox2 code="poll.endDate" path="endDate"/>


<acme:submit name="save" code="poll.save"/>
<acme:cancel url="/welcome/index.do" code="poll.cancel"/>

</form:form>



