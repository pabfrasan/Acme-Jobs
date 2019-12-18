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

<acme:form>
	
	<acme:form-textbox code="auditor.job.form.label.reference" path="reference"/>
	<acme:form-textbox code="auditor.job.form.label.title" path="title"/>
	<acme:form-moment code="auditor.job.form.label.deadline" path="deadline"/>
	<acme:form-money code="auditor.job.form.label.salary" path="salary"/>
	<acme:form-url code="auditor.job.form.label.moreInfo" path="moreInfo"/>
	<acme:form-textarea code="auditor.job.form.label.descriptor" path="descriptor"/>
	
	<strong><acme:message  code="auditor.job.form.descriptor.duties"/></strong><br>
    <br><acme:form>
    <jstl:forEach items="${duties}" var="duty">
        <jstl:out value ="${duty.getTitle()}"/><br>
        <jstl:out value    ="${duty.getDescription()}"/><br>
        <jstl:out value="${duty.getPercentage()}"/><br>
        <br>
    </jstl:forEach>
    </acme:form>
	
    <acme:check-access test="hasRole('Auditor')">
		<acme:form-submit code="auditor.auditRecord.button.create" method="get" action="/auditor/audit-record/create?jobId=${id}"/>
	</acme:check-access>
	
	<acme:form-submit code="auditor.auditRecord.button.list" method="get" action="/auditor/audit-record/list-corresponding?id=${id}"/>
	<acme:form-return code="auditor.job.form.button.return"/>
	
</acme:form>