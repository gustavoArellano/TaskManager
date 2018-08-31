<%@ page isErrorPage="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Update Or Delete</title>
	</head>
	<body>
		<a href="/welcome">Home</a>
		
		<h1>Edit ${task.task}</h1>
			
		<form method="post" action="/edit/${task.id}">
		<input type="hidden" name="_method" value="put">
			<input type="text" name="task" value="${task.task}" />
			
			<select name="assignee">
				
				<option><c:out value="${task.assignee}" /></option>
				<c:forEach items="${user}" var="users">
					<option><c:out value="${users.name}" /></option>
				</c:forEach>
			</select>
			
			<select name="priority" id="priority">
		
					<option value="${task.priority}"></option>
					<option value="Low">Low</option>
					<option value="Medium">Medium</option>
					<option value="High">High</option>
			
			</select>
			
			<input type="submit" value="UPDATE" />
			
		</form>
		
		
	</body>
</html>