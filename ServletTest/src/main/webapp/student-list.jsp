<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<input type="button" value="Add Student"
						onclick="window.location.href='add-student.jsp'"
						class="btn btn-outline-primary mt-3 mx-3" >
	
	<div class="pt-5 px-3">
		<table class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="student" items="${student_list }">
				
				<c:url var="updateLink" value="MvcDemoServlet">
						<c:param name="command" value="SHOW_UPDATE"></c:param>
						<c:param name="studentId" value="${student.id}"></c:param>
				</c:url>
				
				<c:url var="deleteLink" value="MvcDemoServlet">
						<c:param name="command" value="DELETE"></c:param>
						<c:param name="studentId" value="${student.id}"></c:param>
				</c:url>
				
				<tr>
					<td>${student.firstName }</td>
					<td>${student.lastName }</td>
					<td>${student.email }</td>
					<td>
						<a href="${updateLink}">Update</a> | 
						<a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to delete this student?'))) return false">Delete</a>
					</td>
				</tr>		
			</c:forEach>
		</tbody>	
	</table>
	</div>
</body>
</html>