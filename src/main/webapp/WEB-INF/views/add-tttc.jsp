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
        <form:form action="addTTTC" method="POST" modelAttribute="tttc">
            <table class="table table-bordered table-striped">
                <tr>
                    <td>Ngày Tổ Chức</td>
                    <td><form:input path="ngayToChuc" class="form-input" type="date" required="true"/></td>
                    <td><form:errors path="ngayToChuc" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Tên Cô Dâu</td>
                    <td><form:input path="tenCoDau" class="form-input" required="true"/></td>
                    <td><form:errors path="tenCoDau" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Tên Chú Rể</td>
                    <td><form:input path="tenChuRe" class="form-input" required="true"/></td>
                    <td><form:errors path="tenChuRe" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Số Lượng Bàn</td>
                    <td><form:input path="slBan" class="form-input" required="true"/></td>
                    <td><form:errors path="slBan" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Tiền Đặt Cọc</td>
                    <td><form:input path="tienDatCoc" class="form-input" required="true"/></td>
                    <td><form:errors path="tienDatCoc" cssClass="error" /></td>
                </tr>
                <tr>
                    <td><form:label path="dichVu.id">Dịch vụ đi kèm</form:label></td>
                    <td>
                        <form:select path="dichVu.id" id="id" class="form-select">
                            <form:option value="" label="--Hãy Chọn Dịch Vụ Đi Kèm--" readonly="true" />
                            <c:forEach var="dv" items="${dichVus}">
                                <form:option value="${dv.id}" label="${dv.tendichvu}" />
                            </c:forEach>
                        </form:select>
                    </td> 

                    <td><form:errors path="dichVu.id" /></td>
                </tr>
                <tr>
                    <td>Ghi Chú</td>
                    <td><form:input path="ghiChu" class="form-input" required="true"/></td>
                    <td><form:errors path="ghiChu" cssClass="error" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><button class="btn btn-success"type="submit">Submit</button></td>
                    <td></td>
                </tr>
            </table>
        </form:form>
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
