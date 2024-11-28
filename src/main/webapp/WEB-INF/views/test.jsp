<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add User</title>
</head>
<body>
    <h2>Add New User</h2>
    
    <form:form method="POST" action="${pageContext.request.contextPath}/save" modelAttribute="user">
        <table>
            <tr>
                <td><form:label path="name">Name:</form:label></td>
                <td><form:input path="name" /></td>
                <td><form:errors path="name" cssClass="error" /></td>
            </tr>
            <tr>
                <td><form:label path="email">Email:</form:label></td>
                <td><form:input path="email" /></td>
                <td><form:errors path="email" cssClass="error" /></td>
            </tr>
            <tr>
                <td><form:label path="dateOfBirth">Date of Birth:</form:label></td>
                <td><form:input path="dateOfBirth" type="date" /></td>
                <td><form:errors path="dateOfBirth" cssClass="error" /></td>
            </tr>
            <tr>
                <td><form:label path="phoneNumber">Phone Number:</form:label></td>
                <td><form:input path="phoneNumber" /></td>
                <td><form:errors path="phoneNumber" cssClass="error" /></td>
            </tr>
        </table>
        
        <input type="submit" value="Save" />
    </form:form>
</body>
</html>
