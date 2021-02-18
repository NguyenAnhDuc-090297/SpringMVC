<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Applicant</title>
<style type="text/css">
.error-message {
	color: red;
	font-size: 90%;
	font-style: italic;
}
</style>
</head>
<body>

	<h3>${formTitle}</h3>

	<form:form action="saveApplicant" method="POST" modelAttribute="applicantForm">
		<form:hidden path="id" />

		<table>
			<tr>
				<td>Name</td>
				<td><form:input path="name" /></td>
				<td><form:errors path="name" class="error-message" /></td>
			</tr>

			<tr>
				<td>Gender</td>
				<td><form:select path="gender">
						<form:option value="" label="- Gender -" />
						<form:option value="M" label="Male" />
						<form:option value="F" label="Female" />
					</form:select></td>
				<td><form:errors path="gender" class="error-message" /></td>
			</tr>

			<tr>
				<td>Email</td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email" class="error-message" /></td>
			</tr>

			<tr>
				<td>Position</td>
				<td><form:select path="position">
						<form:options items="${positionMap}" />
					</form:select></td>
				<td><form:errors path="position" class="error-message" /></td>
			</tr>

			<tr>
				<td>Skills</td>
				<td><c:forEach items="${skills}" var="skill">
						<form:checkbox path="skills" value="${skill}" label="${skill}" />
					</c:forEach></td>
				<td></td>
			</tr>

			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Submit" /> 
					<a href="${pageContext.request.contextPath}/applicantList">Cancel</a>
				</td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form:form>

</body>
</html>