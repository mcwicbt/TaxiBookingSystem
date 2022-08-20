<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
</head>
<body>

  	<jsp:include page="header.jsp"></jsp:include>
  	
  	<div class="container pt-5">
		<div class="row">
			<div class="col-6">
				<div class="row mb-3">
					<div class="col">
						<h4>Add Student</h4>
					</div>
				</div>

				<div style="color: red;" class="mb-3">${errors }</div>
				<form action="MvcDemoServlet" method="POST">
					<input type="hidden" name="command" value="ADD">
					<div class="row">
						<div class="col-12">
							
							<div class="mb-3">
								<label class="form-label">First Name</label> 
								<input type="text" class="form-control"  name="firstName"
									value="${student.firstName }">
							</div>
							<div class="mb-3">
								<label class="form-label">Last Name</label> 
								<input type="text" class="form-control" name="lastName" 
									value="${student.lastName }">
							</div>
							<div class="mb-3">
								<label class="form-label">Email</label> 
								<input type="text" class="form-control" name="email"
									value="${student.email }">
							</div>

							<button type="submit" class="btn btn-outline-primary">Save</button>
						</div>					
					</div>


				</form>
				<p class="pt-3">
					<a href="MvcDemoServlet">Back to List</a>
				</p>
			</div>
		</div>
	</div>
  	
</body>
</html>