<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%> <%@ page isELIgnored="false" %> <%@ taglib
  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib
  prefix="security" uri="http://www.springframework.org/security/tags" %> --%>
  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <!-- <script
      src="https://code.jquery.com/jquery-3.6.3.min.js"
      integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
      crossorigin="anonymous"
    ></script>
    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
    />
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    /> -->
    
    <meta charset="UTF-8" />
    <title>Welcome to Spring Boot</title>
     <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        div {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            margin: 5px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        button:hover {
            background-color: #367c39;
        }
        
        .logout-form {
            margin-top: 20px;
        }
    </style> 
</head>
<body>
    <div>
        <h1>Welcome!</h1>
        
        <security:authorize access="isAuthenticated()">
          <span id="username">
          <h1><security:authentication property="principal.username" /></h1>
          </span>
          <security:authorize access="hasAuthority('ADMIN')">
            <h3>Admin</h3>
            <button onclick="">Admin</button>
          </security:authorize>
          <security:authorize access="hasAuthority('USER')">
            <h3>User</h3>
            <button onclick="">User</button>
          </security:authorize>
        </security:authorize> 
        
        <form action="${pageContext.request.contextPath}/logout" method="post" class="logout-form">
        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
         	<button type="submit">Logout</button>
         </form>
        
    </div>
    
</body>
</html>


