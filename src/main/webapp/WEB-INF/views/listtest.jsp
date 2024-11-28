<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<title>List Xe</title>
</head>
<body>
    <div align="center">
        <a href="add">Add new Xe</a> <a
            href="${pageContext.request.contextPath}">Home</a>
        <table class="table table-hover table-striped">
            <thead>
                <tr>
                    <th width="10%">Ma Xe</th>
                    <th width="10%">Hang SX</th>
                    <th width="10%">Ma Loai Xe</th>
                    <th width="10%">Mo Ta</th>
                    <th width="10%">So Cho Ngoi</th>
                    <th width="10%">Bien So</th>
                    <th width="10%">Han Kiem Dinh</th>
                    <th width="10%">Ma Nha Xe</th>
                    <th width="10%">Ten Nha Xe</th>
                    <th width="10%">Nam Thanh lap</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="x" items="${hd}" varStatus="status">
                    <tr>
                        <td>${x.id}</td>
                        <td>${x.user.name}</td>
                        
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>