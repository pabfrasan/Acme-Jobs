<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.challenge.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.challenge.form.label.description" path="description"/>
	<acme:form-moment code="authenticated.challenge.form.label.deadline" path="deadline"/>
	<acme:form-textbox code="authenticated.challenge.form.label.rewardGold" path="rewardGold"/>
	<acme:form-textbox code="authenticated.challenge.form.label.rewardSilver" path="rewardSilver"/>
	<acme:form-textbox code="authenticated.challenge.form.label.rewardBronze" path="rewardBronze"/>
	
	<acme:check-access test="hasRole('Administrator')">
		<acme:form-submit test="${command == 'show'}" code="administrator.challenge.form.button.update" action="/administrator/challenge/update"/>
		<acme:form-submit test="${command == 'show'}" code="administrator.challenge.form.button.delete" action="/administrator/challenge/delete"/>
		<acme:form-submit test="${command == 'create'}" code="administrator.challenge.form.button.create" action="/administrator/challenge/create"/>
		<acme:form-submit test="${command == 'update'}" code="administrator.challenge.form.button.update" action="/administrator/challenge/update"/>
		<acme:form-submit test="${command == 'delete'}" code="administrator.challenge.form.button.delete" action="/administrator/challenge/delete"/>
	</acme:check-access>
	
	<acme:form-return code="authenticated.challenge.form.button.return"/>
</acme:form>