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

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.message-thread.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.message-thread.form.label.moment" path="moment"/>
	
	<acme:form-submit code="authenticated.message-thread.form.button.createMessage" method="get" action="/authenticated/message/create?threadId=${id}"/>
	<acme:form-submit code="authenticated.message-thread.form.button.seeMessages" method="get" action="/authenticated/message/list-mine?threadId=${id}"/>
	
	<acme:form-return code="authenticated.message-thread.form.button.return"/>
</acme:form>