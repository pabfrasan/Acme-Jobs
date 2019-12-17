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
	
	<acme:form-textbox code="employer.job.form.label.reference" path="reference"/>
	<acme:form-textbox code="employer.job.form.label.title" path="title"/>
	<acme:form-moment code="employer.job.form.label.deadline" path="deadline"/>
	<acme:form-money code="employer.job.form.label.salary" path="salary"/>
	<acme:form-url code="employer.job.form.label.moreInfo" path="moreInfo"/>
	
	<acme:form-select code="employer.job.form.label.status" path="status">
		<jstl:if test="${status == 'DRAFT'}">
			<acme:form-option code="employer.job.form.label.status.draft" value="DRAFT"/>
		</jstl:if>
		<acme:form-option code="employer.job.form.label.status.published" value="PUBLISHED"/>
	</acme:form-select>
	
	<jstl:if test="${status == 'DRAFT'}">
		<acme:form-select code="employer.job.form.label.idDescriptor" path="idDescriptor">
		
			<jstl:if test="${idDescriptor == 0}">
				<acme:form-option code="---------" value="${0}"/>
			</jstl:if>
			
			<jstl:if test="${idDescriptor != 0}">
				<acme:form-option code="${descriptor.getDescription()}" value="${idDescriptor}"/>
			</jstl:if>
			
			<jstl:forEach items="${descriptors}" var="d">
				<jstl:if test="${d.getId() != descriptor.getId()}">
					<acme:form-option code="${d.getDescription()}" value="${d.getId()}"/>
				</jstl:if>
			</jstl:forEach>
		</acme:form-select>
	</jstl:if>
	
	<acme:form-submit test="${command == 'show'}" 
		code="employer.auditRecord.button.list" 
		method="get" 
		action="/employer/audit-record/list-corresponding?id=${id}"/>
		
	<acme:form-submit test="${command == 'show' && descriptor != null}" 
		code="employer.descriptor.button.show" 
		method="get" 
		action="/employer/descriptor/show?id=${descriptor.id}"/>
		
	<acme:form-submit test="${command == 'show' }"
		code="employer.job.form.button.update" 
		action="/employer/job/update"/>
		
	<acme:form-submit test="${command == 'show' && status == 'DRAFT'}"
		code="employer.job.form.button.delete" 
		action="/employer/job/delete"/>
		
	<acme:form-submit test="${command == 'create' }"
		code="employer.job.form.button.create" 
		action="/employer/job/create"/>
		
	<acme:form-submit test="${command == 'update' }"
		code="employer.job.form.button.update" 
		action="/employer/job/update"/>
		
	<acme:form-return code="employer.job.form.button.return"/>
	
</acme:form>