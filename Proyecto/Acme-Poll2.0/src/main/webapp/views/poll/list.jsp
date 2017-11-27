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

<input type="text" id="textSearch" />
<button type="button" class="btn btn-primary">
	<spring:message code="poll.search" />
</button>

<br />

<display:table pagesize="5" keepStatus="true" name="poll"
	requestURI="${requestURI}" id="row" class="table table-over">


	<spring:message code="poll.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"
		sortable="false" />

	<spring:message code="poll.ticket" var="ticketHeader" />
	<display:column property="ticket" title="${ticketHeader}"
		sortable="false" />

	<spring:message code="poll.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}"
		sortable="false" />

	<spring:message code="poll.banner" var="bannerHeader" />
	<display:column title="${bannerHeader}">
		<a href="${row.banner}">Banner</a>

	</display:column>

	<spring:message code="poll.startDate" var="startDateHeader" />
	<display:column property="startDate" title="${startDateHeader}"
		sortable="false" />

	<spring:message code="poll.endDate" var="endDateHeader" />
	<display:column property="endDate" title="${endDateHeader}"
		sortable="false" />

	<security:authorize access="permitAll() && !hasRole('POLLER')">

		<display:column>
			<a href="question/list.do?q=${row.id}"> <spring:message
					code="poll.questions" />
			</a>
		</display:column>

		<display:column>
			<a href="poller/view.do?pollId=${row.id}"> <spring:message
					code="poll.poller" />
			</a>
		</display:column>

		<display:column>
			<a href="hint/list.do?q=${row.id}"> <spring:message
					code="poll.hints" />
			</a>
		</display:column>

		<jstl:if test="${row.endDate.after(actualDate)}">

			<display:column>
				<a href="answer/answer.do?q=${row.id}"> <spring:message
						code="poll.answer" />
				</a>
			</display:column>

		</jstl:if>

	</security:authorize>

	<security:authorize access="hasRole('POLLER')">

		<display:column>
			<a href="question/poller/list.do?q=${row.id}"> <spring:message
					code="poll.questions" />
			</a>
		</display:column>

		<display:column>
			<a href="poller/view.do?pollId=${row.id}"> <spring:message
					code="poll.poller" />
			</a>
		</display:column>

		<display:column>
			<a href="hint/poller/list.do?q=${row.id}"> <spring:message
					code="poll.hints" />
			</a>
		</display:column>

		<jstl:if test="${row.endDate.after(actualDate)}">
		
			<display:column>
				<a href="poll/poller/edit.do?q=${row.id}"> <spring:message
					code="acme.edit" />
				</a>
			</display:column>

			<display:column>
				<a href="poll/poller/remove.do?q=${row.id}"> <spring:message
						code="acme.delete" />
				</a>
			</display:column>
			
			<display:column>
				<a href="answer/answer.do?q=${row.id}"> <spring:message
						code="poll.answer" />
				</a>
			</display:column>

		</jstl:if>

	</security:authorize>
	
		<display:column>
			<a href="poll/results.do?q=${row.id}"> <spring:message
					code="poll.result" />
			</a>
		</display:column>
		<jstl:if test="diffDaysValid(${row.endDate},${row.startDate},${validPeriod});">
		<display:column>
			<h5><b style="color: red;"> <spring:message code="poll.invalid" /> </b></h5>
		</display:column>
		</jstl:if>
		
	<security:authorize access="hasRole('ADMINISTRATOR')">
	
	<jstl:if test="${row.bill==null}">
		
		<display:column>
			<a href="bill/administrator/create.do?q=${row.id}"> 
			<spring:message code="poll.bill" />
			</a>
		</display:column>
	</jstl:if>
	
	</security:authorize>

</display:table>


<script>

function diffDaysValid(date1,date2,invalidPeriod){
	
	var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
	var firstDate = date1;
	var secondDate = date2;

	var diffDays = Math.round(Math.abs((firstDate.getTime() - secondDate.getTime())/(oneDay)));
	console.log(invalidPeriod);
	console.log(diffDays);
	return diffDays>invalidPeriod;
}

</script>

<script>
	$(document)
			.ready(
					function() {
						$("button")
								.click(
										function() {
											$
													.ajax({
														success : function(
																result) {
															var input, filter, table, tr, tdTitle, tdDescription, tdTicker, i;
															input = document
																	.getElementById("textSearch");
															filter = input.value
																	.toUpperCase();
															table = document
																	.getElementById("row");
															tr = table
																	.getElementsByTagName("tr");
															for (i = 0; i < tr.length; i++) {
																tdTitle = tr[i]
																		.getElementsByTagName("td")[0];
																tdTicker = tr[i]
																		.getElementsByTagName("td")[1];
																tdDescription = tr[i]
																		.getElementsByTagName("td")[2];
																if (tdTitle
																		|| tdDescription
																		|| tdTicker) {
																	if (tdTitle.innerHTML
																			.toUpperCase()
																			.indexOf(
																					filter) > -1
																			|| tdDescription.innerHTML
																					.toUpperCase()
																					.indexOf(
																							filter) > -1
																			|| tdTicker.innerHTML
																					.toUpperCase()
																					.indexOf(
																							filter) > -1) {
																		tr[i].style.display = "";
																	} else {
																		tr[i].style.display = "none";
																	}
																}
															}
														}
													});
										});
					});
</script>
