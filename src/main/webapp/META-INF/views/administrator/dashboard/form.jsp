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
	<acme:form-integer code="administrator.dashboard.form.label.totalAnnouncement" path="totalAnnouncement"/>
	<acme:form-integer code="administrator.dashboard.form.label.totalCompanyRecord" path="totalCompanyRecord"/>
	<acme:form-integer code="administrator.dashboard.form.label.totalInvestorRecords" path="totalInvestorRecords"/>
	
	<acme:form-double code="administrator.dashboard.form.label.minRewardsSolicit" path="minRewardsSolicit"/>
	<acme:form-double code="administrator.dashboard.form.label.maxRewardsSolicit" path="maxRewardsSolicit"/>
	<acme:form-double code="administrator.dashboard.form.label.avgRewardsSolicit" path="avgRewardsSolicit"/>
	<acme:form-double code="administrator.dashboard.form.label.stdRewardsSolicit" path="stdRewardsSolicit"/>
	
	<acme:form-double code="administrator.dashboard.form.label.minRewardsOffer" path="minRewardsOffer"/>
	<acme:form-double code="administrator.dashboard.form.label.maxRewardsOffer" path="maxRewardsOffer"/>
	<acme:form-double code="administrator.dashboard.form.label.avgRewardsOffer" path="avgRewardsOffer"/>
	<acme:form-double code="administrator.dashboard.form.label.stdRewardsOffer" path="stdRewardsOffer"/>
	
	<acme:form-double code="administrator.dashboard.form.label.avgJobsEmployer" path="avgJobsEmployer"/>
	<acme:form-double code="administrator.dashboard.form.label.avgApplicationsEmployer" path="avgApplicationsEmployer"/>
	<acme:form-double code="administrator.dashboard.form.label.avgApplicationsWorker" path="avgApplicationsWorker"/>
	
	
	<h2>
		<acme:message code="administrator.dashboard.form.label.chart.company" />
	</h2>
	
	<div>
		<canvas id="canvas1"></canvas>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
			var data = {
					labels : [
						
						<jstl:forEach var="item" items="${sectorNumberCompanyRecord}">
							"<jstl:out value="${item[0]}"/>",
						</jstl:forEach>
					],
					datasets : [
						{
							data : [
							<jstl:forEach var="item" items="${sectorNumberCompanyRecord}">
								"<jstl:out value="${item[1]}"/>",
							</jstl:forEach>
							]
						}						
					]
			};
			var options = {
					legend : {
						display : false
					},
					scales : {
						yAxes:[{
							ticks:{
								suggestedMin:0.0
							}
						}]
					}
			};
			var canvas, context;
			
			canvas = document.getElementById("canvas1");
			context = canvas.getContext("2d");
			new Chart(context, {
				type : "bar",
				data : data,
				options : options
			});
		});
	</script>
	
	<h2>
		<acme:message code="administrator.dashboard.form.label.chart.investor" />
	</h2>
	
	<div>
		<canvas id="canvas2"></canvas>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
			var data = {
					labels : [
						
						<jstl:forEach var="item" items="${sectorNumberInvestorRecord}">
							"<jstl:out value="${item[0]}"/>",
						</jstl:forEach>
					],
					datasets : [
						{
							data : [
							<jstl:forEach var="item" items="${sectorNumberInvestorRecord}">
								"<jstl:out value="${item[1]}"/>",
							</jstl:forEach>
							]
						}						
					]
			};
			var options = {
					legend : {
						display : false
					}
			};
			var canvas, context;
			
			canvas = document.getElementById("canvas2");
			context = canvas.getContext("2d");
			new Chart(context, {
				type : "pie",
				data : data,
				options : options
			});
		});
	</script>
	
	<h2>
		<acme:message code="administrator.dashboard.form.label.chart.jobsByStatus" />
	</h2>
	
	<div>
		<canvas id="canvas3"></canvas>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
			var data = {
					labels : [
						
						<jstl:forEach var="item" items="${ratioJobsByStatus}">
							"<jstl:out value="${item[1]}"/>",
						</jstl:forEach>
					],
					datasets : [
						{
							data : [
							<jstl:forEach var="item" items="${ratioJobsByStatus}">
								"<jstl:out value="${item[0]}"/>",
							</jstl:forEach>
							]
						}						
					]
			};
			var options = {
					legend : {
						display : false
					},
					scales : {
						yAxes:[{
							ticks:{
								suggestedMin:0.0,
								suggestedMax:100.0
							}
						}]
					}
			};
			var canvas, context;
			
			canvas = document.getElementById("canvas3");
			context = canvas.getContext("2d");
			new Chart(context, {
				type : "bar",
				data : data,
				options : options
			});
		});
	</script>
	
	<h2>
		<acme:message code="administrator.dashboard.form.label.chart.applicationsByStatus" />
	</h2>
	
	<div>
		<canvas id="canvas4"></canvas>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
			var data = {
					labels : [
						
						<jstl:forEach var="item" items="${ratioApplicationsByStatus}">
							"<jstl:out value="${item[1]}"/>",
						</jstl:forEach>
					],
					datasets : [
						{
							data : [
							<jstl:forEach var="item" items="${ratioApplicationsByStatus}">
								"<jstl:out value="${item[0]}"/>",
							</jstl:forEach>
							]
						}						
					]
			};
			var options = {
					legend : {
						display : false
					},
					scales : {
						yAxes:[{
							ticks:{
								suggestedMin:0.0,
								suggestedMax:100.0
							}
						}]
					}
			};
			var canvas, context;
			
			canvas = document.getElementById("canvas4");
			context = canvas.getContext("2d");
			new Chart(context, {
				type : "bar",
				data : data,
				options : options
			});
		});
	</script>
	
	
	
	<h2>
		<acme:message code="administrator.dashboard.form.label.status" />
	</h2>
	
	<div>
		<canvas id="canvas5"></canvas>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
			var data = {
					labels : ["28","27","26","25","24","23","22","21","20",
						"19","18","17","16","15","14","13","12","11","10",
						"9","8","7","6","5","4","3","2","1"
						]
					,datasets : [
						{	
							label : "Pending",
							borderColor: "#ffff00",
							fill : false,
							data : [
								
							<jstl:forEach var="pending" items="${numberPendingApplications}">
				   		  			"<jstl:out value="${pending}"/>",
				   			</jstl:forEach>
							
				
							]
						},
						{
							label : "Accepted",
							borderColor: "#00ff00",
							fill : false,
					      	data: [ 
					        	<jstl:forEach var="accepted" items="${numberAcceptedApplications}">
						    		"<jstl:out value="${accepted}"/>",
								</jstl:forEach>
					        ]
					     }, 
					     {
					    	label : "Rejected",
					    	borderColor: "#ff0000",
					    	fill : false,
							data: [ 
								<jstl:forEach var="rejected" items="${numberRejectedApplications}">
					    			"<jstl:out value="${rejected}"/>",
								</jstl:forEach>
							]
						}
					    ]
					};

			var options = {
					legend : {
						display : true
					},
					scales : {
						yAxes:[{
							ticks:{
								suggestedMin:0.0,
							}
						}]
					}
			};
			var canvas, context;
			
			canvas = document.getElementById("canvas5");
			context = canvas.getContext("2d");
			new Chart(context, {
				type : "line",
				data : data,
				options : options
			});
		});
	</script>
	
	
	
	<acme:form-return code="administrator.dashboard.form.button.return"/>
</acme:form>