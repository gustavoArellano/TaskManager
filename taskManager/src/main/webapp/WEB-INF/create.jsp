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
		
			<select name="assignee">
				<c:forEach items="${user}" var="users">
						<option><c:out value="${users.name}" /></option>
				</c:forEach>
			</select>
			
			<select name="priority" id="priority">
				<option value="priority">Low</option>
				<option value="priority">Medium</option>
				<option value="priority">High</option>
			</select>
			
			<button>CREATE</button>
		</form>
	
	</body>
</html>

