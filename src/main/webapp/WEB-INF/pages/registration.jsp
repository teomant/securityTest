<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>BORAJI.COM</title>
</head>
<body>

<h1>Spring MVC 5 + Spring Security 5 + Hibernate 5 example</h1>
<h4>Registration Form</h4>

<form:form method="post" action="/registration" modelAttribute="registrationForm">
    <spring:bind path="username">
        <label>Login</label>
        <form:input path="username" placeholder="User name"/>
        <form:errors path="username"/>
    </spring:bind>
    <br/>
    <spring:bind path="password">
        <label>Password</label>
        <form:password path="password" placeholder="Password"/>
        <form:errors path="password"/>
    </spring:bind>
    <spring:bind path="role">
        <label>Role</label>
        <form:input path="role" placeholder="role"/>
        <form:errors path="role"/>
    </spring:bind>
    <br/>
    <button type="submit" > Submit</button>
</form:form>
<br/>
</body>
</html>
