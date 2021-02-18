<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Applicant List</title>
<style type="text/css">
	table {
		margin-top: 10px;
		border: solid black 1px;
	}
	table td {
	 	padding: 5px;
	}
	.message {
		font-size: 90%;
		color: blue;
		font-style: italic;
		margin-top: 30px;
	}
</style>
</head>
<body>

<a href="${pageContext.request.contextPath}/createApplicant">Create Applicant</a>
<br/>

<table border="1">
	<tr>
		<th>Name</th>
		<th>Position</th>
		<th>Gender</th>
		<th>Email</th>
		<th>Skills</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>
	<c:forEach items="${applicantInfos}" var="info">
	
	<tr>
		<td>${info.name}</td>
		<td>${info.position}</td>
		<td>${info.gender}</td>
		<td>${info.email}</td>
		<td>${info.skillsString}</td>
		<td><a href="deleteApplicant?id=${info.id}">Delete</a></td>
		<td><a href="editApplicant?id=${info.id}">Edit</a></td>
	</tr>
	
	</c:forEach>
</table>

<c:if test="${not empty message}">
	<div class="message">${message}</div>
</c:if>


</body>
</html>














