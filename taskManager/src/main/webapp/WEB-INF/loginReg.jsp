<%@ page isErrorPage="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login or Register</title>
	</head>
	<body>
		<h1>Welcome!</h1>

        <div class="register">
        
        	<p><form:errors path="user.*"/></p>
        	
	        <form:form style="display:block" method="POST" action="/registration" modelAttribute="user">

	            <h3>Register here!</h3>
				
				<p>
					<form:label path="name">Name: </form:label>
	            	<form:input type="text" path="name"/>
	            	
				</p>
	            
				<p>
					<form:label path="email">Email: </form:label>
	            	<form:input type="text" path="email"/>
	            	
				</p>
				
				<p>
					<form:label path="password">Password:</form:label>
	            	<form:password path="password"/>
	            	
				</p>
	
	            <p>
	           		<form:label path="passwordConfirmation">Password Confirmation: </form:label>
	            	<form:password path="passwordConfirmation"/>
	            	
	            </p>
	
	            <input type="submit" value="REGISTER!" />
	
	        </form:form>
	    </div>
	
	    <div class="login">
	     		
            <h3>Login here!</h3>
	            
            <p><c:out value="${error}" /></p>
	            
            <form method="post" action="/login">
            	<p>
             		<label for="email" >Email: </label>
             		<input type="text" id="email" name="email"/>
            	</p>
	    
	            <p>
	            	<label for="password">Password </label>
	            	<input type="password" id="password" name="password">
	            </p>
	            <input type="submit" name='login' value="LOGIN!" >
	        </form>
	    </div>
	</body>
</html>