<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="include/header.jsp" %>

<div id="main-wrapper">
    <%@include file="include/nav.jsp" %>
    <!-- End Navigation -->
    <div class="clearfix"></div>
    <%@include file="include/jquery_footer.jsp" %>
</div>
<div class="container mt-5">
    <h2 class="text-center mb-4">All Users</h2>
    <!-- Search Form -->
    <form action="${pageContext.request.contextPath}/searchResults" method="post">
        <!-- Search Criteria Options -->
        <div class="form-check">
            <input class="form-check-input" type="checkbox" name="searchByName" id="searchByName" onchange="toggleSearchFields()">
            <label class="form-check-label" for="searchByName">
                Search by Name
            </label>
        </div>

        <div class="form-check">
            <input class="form-check-input" type="checkbox" name="searchByPhone" id="searchByPhone" onchange="toggleSearchFields()">
            <label class="form-check-label" for="searchByPhone">
                Search by Phone Number
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="checkbox" name="searchByClass" id="searchByClass" onchange="toggleSearchFields()">
            <label class="form-check-label" for="searchByClass">
                Search by Class
            </label>
        </div>

        <!-- Dynamic Fields Based on Checkbox Selection -->
        <div id="nameField" class="mb-3" style="display: none;">
            <label for="nameSearchTerm" class="form-label">Name</label>
            <input type="text" name="nameSearchTerm" id="nameSearchTerm" class="form-control" placeholder="Enter name">
        </div>

        <div id="phoneField" class="mb-3" style="display: none;">
            <label for="phoneSearchTerm" class="form-label">Phone Number</label>
            <input type="text" name="phoneSearchTerm" id="phoneSearchTerm" class="form-control" placeholder="Enter phone number">
        </div>
        <div id="classField" class="mb-3" style="display: none;">
            <label for="classSearchTerm" class="form-label">Phone Number</label>
            <input type="text" name="classSearchTerm" id="classSearchTerm" class="form-control" placeholder="Enter class">
        </div>

        <button class="btn btn-primary mt-3" type="submit">Search</button>
    </form>
    <c:if test="${not empty users}">
        <table class="table table-bordered table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>Date of Birth</th>
                    <th>Classes</th> <!-- C?t m?i ?? hi?n th? l?p h?c -->
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.email}</td>
                        <td>${user.phoneNumber}</td>
                        <td>${user.dateOfBirth}</td>
                        <td>
                            <!-- Hi?n th? các l?p h?c c?a user -->
                            <c:forEach var="hd" items="${hopDongs}">
                                <c:if test="${hd.user.id eq user.id}">
                                    ${hd.lopHoc.name}<br/>
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/viewUser/${user.id}" class="btn btn-info btn-sm">View</a>
                            <a href="${pageContext.request.contextPath}/editUser/${user.id}" class="btn btn-warning btn-sm">Edit</a>
                            <a href="${pageContext.request.contextPath}/deleteUser/${user.id}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this user?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>


    <c:if test="${empty users}">
        <p class="alert alert-warning">No users found.</p>
    </c:if>
    <a href="${pageContext.request.contextPath}/addUser" class="btn btn-primary">Add New User</a>
    <c:if test="${not empty classes}">
        <table class="table table-bordered table-striped mt-5">
            <thead class="thead-dark">
                <tr>
                    <th>Class Code</th>
                    <th>Class Name</th>

                </tr>
            </thead>
            <tbody>
                <c:forEach var="classes" items="${classes}">
                    <tr>
                        <td>${classes.id}</td>
                        <td>${classes.name}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>


</div>
<script>
    // Hàm ?? hi?n th? các tr??ng nh?p li?u d?a trên checkbox ???c ch?n
    function toggleSearchFields() {
        var searchByName = document.getElementById('searchByName').checked;
        var searchByPhone = document.getElementById('searchByPhone').checked;
        var searchByClass = document.getElementById('searchByClass').checked;


        // Hi?n th?/?n tr??ng nh?p li?u t??ng ?ng v?i tên và s? ?i?n tho?i
        document.getElementById('nameField').style.display = searchByName ? 'block' : 'none';
        document.getElementById('phoneField').style.display = searchByPhone ? 'block' : 'none';
        document.getElementById('classField').style.display = searchByClass ? 'block' : 'none';

    }
</script>


