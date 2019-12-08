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

<acme:form readonly="true">
	<acme:form-textarea code="employer.descriptor.form.label.description" path="description"/>
	
	
	<!--<jstl:forEach items="${duties}" var="duty">
			${duty.getTitle()}<br>
			${duty.getDescription()}<br>
			${duty.getPercentage()}<br>
	</jstl:forEach>
	-->
	<acme:form-return code="employer.descriptor.form.button.return"/>
</acme:form>