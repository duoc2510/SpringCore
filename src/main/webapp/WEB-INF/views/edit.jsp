

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="include/header.jsp" %>
<style>.form-input{
    width: 200px
}</style>
<div id="main-wrapper">
    <%@include file="include/nav.jsp" %>
    <!-- End Navigation -->
    <div class="clearfix"></div>
    <%@include file="include/jquery_footer.jsp" %>
</div>
<div class="container mt-5">
    <h2 class="text-center mb-4">add-user.jsp</h2>
    <div class="form-container">
        <button type="button" class="btn btn-primary">Primary</button>
        <button type="button" class="btn btn-secondary">Secondary</button>
        <button type="button" class="btn btn-success">Success</button>
        <button type="button" class="btn btn-danger">Danger</button>
        <button type="button" class="btn btn-warning">Warning</button>
        <button type="button" class="btn btn-info">Info</button>
        <button type="button" class="btn btn-light">Light</button>
        <button type="button" class="btn btn-dark">Dark</button>
        <button type="button" class="btn btn-link">Link</button>
        <form:form action="${pageContext.request.contextPath}/editUser" method="POST" modelAttribute="user">
            <table class="table table-bordered table-striped">
                <tr>
                    <td>ID</td>
                    <td><form:input path="id" readonly="true" /></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><form:input path="name" class="form-input" /></td>
                    <td><form:errors path="name" cssClass="error" /></td>
                </tr>

                <tr>
                    <td>Email</td>
                    <td><form:input path="email" class="form-input" /></td>
                    <td><form:errors path="email" cssClass="error" /></td>
                </tr>

                <tr>
                    <td>Phone Number</td>
                    <td><form:input path="phoneNumber" class="form-input" /></td>
                    <td><form:errors path="phoneNumber" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Date Of Birth</td>
                    <td>
                        <form:input path="dateOfBirth" class="form-input" type="date" />
                    </td>
                    <td>
                        <form:errors path="dateOfBirth" cssClass="error"/>
                    </td>
                </tr>
                <tr>
                    <td>User Type</td>
                    <td>
                        <form:radiobutton path="userType" value="Discount" id="khuyenMai" />
                        <label for="khuyenMai">Khuyến Mãi</label>
                        <form:radiobutton path="userType" value="Null" id="khongKhuyenMai" />
                        <label for="khongKhuyenMai">Không Khuyến Mãi</label>
                    </td>
                    <td>
                        <form:errors path="userType" cssClass="error" />
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><button class="btn btn-success"type="submit">Submit</button></td>
                    <td></td>
                </tr>
            </table>
        </form:form>
        <a class="btn btn-dark" href="${pageContext.request.contextPath}/viewAllUsers">Back to User List</a>


        <!-- Danh sách l?p h?c ch?a tham gia -->
        <h3 class="text-center mt-5">Classes Not Joined</h3>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Class Name</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${classes}" var="lopHoc">
                    <tr>
                        <td>${lopHoc.id}</td>
                        <td>${lopHoc.name}</td>
                        <td>
                            <!-- Nút tham gia -->
                            <form action="${pageContext.request.contextPath}/joinClass" method="POST" style="display: inline;">
                                <input type="hidden" name="userId" value="${user.id}" />
                                <input type="hidden" name="lopHocId" value="${lopHoc.id}" />
                                <button class="btn btn-primary" type="submit">Join</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<!--<script>
    document.addEventListener("DOMContentLoaded", function () {
        const phoneInput = document.querySelector("[name='phoneNumber']");
        const emailInput = document.querySelector("[name='email']");

        // Tạo các phần tử để hiển thị lỗi
        const phoneErrorElement = document.createElement("span");
        phoneErrorElement.className = "text-danger";

        const emailErrorElement = document.createElement("span");
        emailErrorElement.className = "text-danger";

        // Hàm kiểm tra số điện thoại
        function validatePhone() {
            const phoneValue = phoneInput.value.trim();
            phoneErrorElement.textContent = "";

            if (!/^09\d{8}$/.test(phoneValue)) {
                phoneErrorElement.textContent = "Số điện thoại phải gồm 10 chữ số và bắt đầu bằng 09.";
                if (!phoneInput.parentNode.contains(phoneErrorElement)) {
                    phoneInput.parentNode.appendChild(phoneErrorElement);
                }
            } else if (phoneErrorElement.parentNode) {
                phoneErrorElement.parentNode.removeChild(phoneErrorElement);
            }
        }

        // Hàm kiểm tra email
        function validateEmail() {
            const emailValue = emailInput.value.trim();
            emailErrorElement.textContent = "";

            if (!emailValue.endsWith("@gmail.com")) {
                emailErrorElement.textContent = "Email phải kết thúc bằng @gmail.com.";
                if (!emailInput.parentNode.contains(emailErrorElement)) {
                    emailInput.parentNode.appendChild(emailErrorElement);
                }
            } else if (emailErrorElement.parentNode) {
                emailErrorElement.parentNode.removeChild(emailErrorElement);
            }
        }

        // Thêm sự kiện 'input' để kiểm tra ngay lúc nhập
        phoneInput.addEventListener("input", validatePhone);
        emailInput.addEventListener("input", validateEmail);

        // Thêm sự kiện 'blur' để kiểm tra khi rời khỏi trường
        phoneInput.addEventListener("blur", validatePhone);
        emailInput.addEventListener("blur", validateEmail);

        // Kiểm tra toàn bộ trước khi submit
        const form = document.querySelector("form");
        form.addEventListener("submit", function (e) {
            validatePhone();
            validateEmail();

            if (phoneErrorElement.textContent || emailErrorElement.textContent) {
                e.preventDefault(); // Ngăn form submit nếu có lỗi
            }
        });
    });
</script>-->
