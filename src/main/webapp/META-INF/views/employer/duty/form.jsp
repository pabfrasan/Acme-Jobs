<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not duty any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="employer.duty.form.label.title" path="title"/>
	<acme:form-textarea code="employer.duty.form.label.description" path="description"/>
	<acme:form-integer code="employer.duty.form.label.percentage" path="percentage"/>
	
	<jstl:if test="${command == 'create' }">
		<acme:form-select code="employer.duty.form.label.idDescriptor" path="idDescriptor">
			<jstl:forEach items="${descriptors}" var="descriptor">
				<acme:form-option code="${descriptor.getDescription()}" value="${descriptor.getId()}"/>
			</jstl:forEach>
		</acme:form-select>
	</jstl:if>
	
	<acme:form-submit test="${command == 'create' }" code="employer.duty.form.button.create" action="/employer/duty/create/"/>
	<acme:form-return code="employer.duty.form.button.return"/>
</acme:form>