<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="include/header.jsp" %>
<div id="main-wrapper">
    <%@include file="include/nav.jsp" %>
    <!-- End Navigation -->
    <div class="clearfix"></div>
    <%@include file="include/jquery_footer.jsp" %>
</div>
<div class="container mt-5">
    <div class="container mt-5">
        <h2 class="text-center mb-4">User Information</h2>
        <!-- Hi?n th? thông tin ng??i dùng -->
        <table class="table table-bordered user-info">
            <tr>
                <th>ID</th>
                <td>${user.id}</td>
            </tr>
            <tr>
                <th>Name</th>
                <td>${user.name}</td>
            </tr>
            <tr>
                <th>Email</th>
                <td>${user.email}</td>
            </tr>
            <tr>
                <th>Phone Number</th>
                <td>${user.phoneNumber}</td>
            </tr>
            <tr>
                <th>Date of Birth</th>
                <td>${user.dateOfBirth}</td>
            </tr>
        </table>

        <!-- Link tr? l?i trang danh sách ng??i dùng -->
        <a href="${pageContext.request.contextPath}/viewAllUsers" class="btn btn-secondary">Back to User List</a>
        <a href="${pageContext.request.contextPath}/editUser/${user.id}" class="btn btn-danger">Update</a>
    </div>
</div>


