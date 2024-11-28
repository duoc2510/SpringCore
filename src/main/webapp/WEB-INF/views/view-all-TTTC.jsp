<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="include/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="main-wrapper">
    <%@include file="include/nav.jsp" %>
    <!-- End Navigation -->
    <div class="clearfix"></div>
    <%@include file="include/jquery_footer.jsp" %>
</div>
<div class="container mt-5">
    <h2 class="text-center mb-4">All Users</h2>
    <h3 class="text-center mb-4" style="color: red">${msg}</h3>

    <!-- Search Form -->
    <form action="${pageContext.request.contextPath}/searchTTTC" method="post">
        <!-- Search Criteria Options -->
        <div class="form-check">
            <input class="form-check-input" type="checkbox" name="searchByName" id="searchByName" onchange="toggleSearchFields()">
            <label class="form-check-label" for="searchByName">
                Search by Name
            </label>
        </div>


        <!-- Dynamic Fields Based on Checkbox Selection -->
        <div id="nameField" class="mb-3" style="display: none;">
            <label for="nameSearchTerm" class="form-label">Name</label>
            <input type="text" name="nameSearchTerm" id="nameSearchTerm" class="form-control" placeholder="Enter name">
        </div>


        <button class="btn btn-primary mt-3" type="submit">Search</button>
    </form>
    <c:if test="${not empty tttcs}">
        <table class="table table-bordered table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>ID ${msg}</th>
                    <th>Tên Cô Dâu</th>
                    <th>Tên Chú Rể</th>
                    <th>Ngày Tổ Chức</th>
                    <th>Số Lượng Bàn</th>
                    <th>Đơn Giá</th>
                    <th>Dịch Vụ Đi Kèm</th>
                    <th>Tiền Đặt Cọc</th>
                    <th>Tiền Thanh Toán</th>
                    <th>Ngày Đặt Cọc</th>
                    <th>Ngày Thanh Toán</th>
                    <th>Trạng Thái</th>
                    <th>Ghi Chú</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="tttcs" items="${tttcs}">
                    <tr>
                        <td>${tttcs.id}</td>
                        <td>${tttcs.tenCoDau}</td>
                        <td>${tttcs.tenChuRe}</td>
                        <td>${tttcs.ngayToChuc}</td>
                        <td>${tttcs.slBan}</td>
                        <td>${tttcs.donGia}</td>
                        <td>${tttcs.dichVu.tendichvu}</td>
                        <td>${tttcs.tienDatCoc}</td>
                        <td>${tttcs.tienThanhToan}</td>
                        <td>${tttcs.ngayDatCoc}</td>
                        <td>${tttcs.ngayThanhToan}</td>
                        <td>${tttcs.trangThai}</td>
                        <td>${tttcs.ghiChu}</td>
                        <td>
                            <!--<a href="${pageContext.request.contextPath}/viewUser/${user.id}" class="btn btn-info btn-sm">View</a>-->
                            <!--<a href="${pageContext.request.contextPath}/editUser/${user.id}" class="btn btn-warning btn-sm">Edit</a>-->
                            <a href="${pageContext.request.contextPath}/deleteTTTC/${tttcs.id}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>


    <c:if test="${empty tttcs}">
        <p class="alert alert-warning">Hiện không có thông tin nào</p>
    </c:if>
    <a href="${pageContext.request.contextPath}/addTTTC" class="btn btn-primary">Add New TTTC</a>
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



        // Hi?n th?/?n tr??ng nh?p li?u t??ng ?ng v?i tên và s? ?i?n tho?i
        document.getElementById('nameField').style.display = searchByName ? 'block' : 'none';


    }
</script>


