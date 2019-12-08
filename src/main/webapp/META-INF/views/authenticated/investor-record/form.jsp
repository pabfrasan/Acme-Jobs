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

<acme:form readonly="!hasRole('Admnistrator')">
	<acme:form-textbox code="authenticated.investor-record.form.label.name" path="name"/>
	<acme:form-textbox code="authenticated.investor-record.form.label.sector" path="sector"/>
	<acme:form-textarea code="authenticated.investor-record.form.label.invStatement" path="invStatement"/>
	<acme:form-integer code="authenticated.investor-record.form.label.stars" path="stars" placeholder="1 2 3 4 5"/>
	
	<acme:check-access test="hasRole('Administrator')">
		<acme:form-submit test="${command == 'show'}" code="administrator.investor-record.form.button.update" action="/administrator/investor-record/update"/>
		<acme:form-submit test="${command == 'show'}" code="administrator.investor-record.form.button.delete" action="/administrator/investor-record/delete"/>
		<acme:form-submit test="${command == 'delete'}" code="administrator.investor-record.form.button.delete" action="/administrator/investor-record/delete"/>
	</acme:check-access>
	
	<acme:form-return code="authenticated.investor-record.form.button.return"/>
	
</acme:form>