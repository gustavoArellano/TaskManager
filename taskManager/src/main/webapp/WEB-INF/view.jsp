<%@ page isErrorPage="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>View Edit Delete</title>
	</head>
	<body>
		<a href="/welcome">Back</a>
		
		<h1>Task: ${tasks.task}</h1>
		
		<p>Creator:   ${task.user.name}</p>
		
		<p>Assignee:   ${task.assignee}</p>
		
		<p>Priority:   ${task.priority}</p>
		
		
		<c:if test="${task.user.id == sessionScope.user}">
		
			<a href="/edit/${task.id}"><button>EDIT</button></a>
			
			<a href="/delete/${task.id}"><button>DELETE</button></a>
			
		</c:if>
		
 		<c:if test="${task.assignee == sessionScope.name}">
		
			<a href="/completed/${task.id}"><button>COMPLETED</button></a>
		
		</c:if>
	
	</body>
</html>
