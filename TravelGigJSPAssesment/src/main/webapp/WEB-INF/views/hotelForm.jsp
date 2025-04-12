<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head><title>Hotel Form</title></head>
<body>
    <h2>Hotel Form</h2>
    <form:form modelAttribute="hotel" method="post" action="submitHotel">
        Hotel Name: <form:input path="hotelName"/><br/>
        Address: <form:input path="address"/><br/>
        Contact Details: <form:input path="contactDetails"/><br/>
        Email: <form:input path="email"/><br/>
        <input type="submit" value="Submit"/>
    </form:form>
</body>
</html>
