<%@ page isErrorPage="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Welcome</title>
	</head>
	<body>
		<a href="/logout">Logout</a>
	
		<h1>Welcome, ${users.name}!</h1>
		
		<h2>Tasks</h2>
		
		<table style="width:600px; text-align:center">
			<thead>
				<tr>
					<th>Task</th>
					<th>Creator</th>
					<th>Assignee</th>
					<th>Priority</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${tasks}" var="tasks">
				<tr>
					<td><a href="/view/${tasks.id}"><c:out value="${tasks.task}"/></a></td>
					
					<td><p><c:out value="${tasks.user.name}"/></p></td>
					
					<td><p><c:out value="${tasks.assignee}"/></p></td>
					
					<td><p><c:out value="${task.priority}"/></p></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<div style="margin-top=50px;">
		
			<a href="/create" style="margin-top:200px"><button>CREATE TASK</button></a>
			
		</div>
		
	</body>
</html>