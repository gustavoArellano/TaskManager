<%@ page isErrorPage="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Create Task</title>
	</head>
	<body>
		<a href="/welcome">Back</a>
		
		<h1>Create a new task</h1>
		
		<p><c:out value="${error}" /></p>
		
		
		
		<form method="POST" action="/new">
		
		
			<p>Task: <input type="text" name="task"></p>
		
			<p> Assignee: <select name="assignee">
				<option label="---SELECT---"></option>
				<c:forEach items="${user}" var="users">
						<option><c:out value="${users.name}" /></option>
				</c:forEach>
			</select></p>
			
			<p> Priority: <select name="priority">
				<option label="---SELECT---"></option>
				<option>Low</option>
				<option>Medium</option>
				<option>High</option>
			</select> </p>
			
			<button>CREATE</button>
		</form>
	
	
		
	</body>
</html>

