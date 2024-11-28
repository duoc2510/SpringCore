<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="include/header.jsp" %>

<div id="main-wrapper">
    <%@include file="include/nav.jsp" %>
    <!-- End Navigation -->
    <div class="clearfix"></div>
    <%@include file="include/jquery_footer.jsp" %>
</div>
<div class="container mt-5">
    <div class="card text-white bg-danger mb-3">
        <div class="card-header">
            <h3>Error Occurred</h3>
        </div>
        <div class="card-body">
            <h4 class="card-title">Error Code: ${errCode}</h4>
            <p class="card-text">${errMsg}</p>
            <a href="javascript:history.back()" class="btn btn-light">Go Back</a>
        </div>
    </div>
</div>

