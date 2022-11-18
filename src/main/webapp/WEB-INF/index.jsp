<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <meta charset="UTF-8">
    <title>Login Page</title>
</head>
<body>
	<div class="d-flex justify-content-evenly">
    <div class="input-group mb-3" style="margin-left: 100px">
    <form:form method="POST" action="/register" modelAttribute="user">
    <h1>Register</h1>
   		<p>
            <form:label class="form-label" path="userName">User Name:</form:label>
            <form:errors path="userName"/>
            <form:input type="text" path="userName" class="form-control" id="exampleFormControlInput1" placeholder=""/>
        </p>
        <p>
            <form:label class="form-label" path="email">Email:</form:label>
            <form:errors path="email"/>
            <form:input type="email" path="email" class="form-control" id="exampleFormControlInput1" placeholder=""/>
        </p>
        <p>
            <form:label class="form-label" path="password">Password:</form:label>
            <form:errors path="password"/>
            <form:input type="password" path="password" class="form-control" id="exampleFormControlInput1" placeholder=""/>
        </p>
        <p>
            <form:label class="form-label" path="confirm">Confirm Password:</form:label>
            <form:errors path="confirm"/>
            <form:input type="password" path="confirm" class="form-control" id="exampleFormControlInput1" placeholder=""/>
        </p>
        <input type="submit" value="Submit"/>
    </form:form>
    </div>
    <div class="input-group mb-3" style="margin-left: 100px">
    <form:form method="post" action="/login"  modelAttribute="loginUser">
    <h1>Login</h1>
    <p><c:out value="${error}" /></p>
        <p>
        	<form:label path="email" for="email" class="form-label">Email</form:label>
            <form:errors path="email"/>
            <form:input id="email" path="email" type="email" class="form-control" placeholder=""/>
        </p>
        <p>
            <form:label path="password" for="exampleFormControlInput1" class="form-label">Password</form:label>
            <form:errors path="password"/>
            <form:input type="password" id="password" path="password" class="form-control" placeholder=""/>
        </p>
        <input type="submit" value="Submit"/>
    </form:form>    
    </div>
    </div>
</body>
</html>