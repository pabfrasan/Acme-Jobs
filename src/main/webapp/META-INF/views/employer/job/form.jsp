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
	
	<acme:form-textbox code="employer.job.form.label.reference" path="reference"/>
	<acme:form-textbox code="employer.job.form.label.title" path="title"/>
	<acme:form-moment code="employer.job.form.label.deadline" path="deadline"/>
	<acme:form-money code="employer.job.form.label.salary" path="salary"/>
	<acme:form-url code="employer.job.form.label.moreInfo" path="moreInfo"/>
	<acme:form-textarea code="employer.job.form.label.status" path="status"/>
	<acme:form-textarea readonly="true" code="employer.job.form.label.descriptor" path="descriptor"/>
	
	
	<strong><acme:message  code="employer.job.form.descriptor.duties"/></strong><br>
    <br><acme:form>

    <jstl:forEach items="${duties}" var="duty">
        <jstl:out value ="${duty.getTitle()}"/><br>
        <jstl:out value    ="${duty.getDescription()}"/><br>
        <jstl:out value="${duty.getPercentage()}"/><br>
        <br>
    </jstl:forEach>

    </acme:form>

	<acme:form-submit code="employer.auditRecord.button.list" method="get" action="/employer/audit-record/list-corresponding?id=${id}"/>
	<acme:form-return code="employer.job.form.button.return"/>
	
</acme:form>