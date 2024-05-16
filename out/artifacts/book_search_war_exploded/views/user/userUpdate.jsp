<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/css/main.css"/>
</head>
<body>
<div class="container">
    <div class="content" style="margin: auto">
        <div class="information-title">
            <span>当前位置>用户管理>用户表单</span>
            <div class="bottom fr">

            </div>
        </div>

        <div class="unit-information">
            <div class="unit">
                <p class="unit-content">用户信息</p>
            </div>
            <div class="unit-list clearfix">
                <div class="fl">
                    <form action="<c:url value="/user/saveOrUpdate"/>" method="post">
                    <div class="list ">
                        <input type="hidden" name="type" value="save">
                        <input type="hidden" name="user_id" value="${user.user_id}">
                        <label>用户名</label> : <input type="text" name="username" value="${user.username}"/>
                        <label>密码  </label> : <input type="text" name="password" value="${user.password}"/>
                        <br><br>
                        <c:if test="${sessionScope.admin}">
                            <label>管理员权限</label> : <input type="text" name="role" value="${user.role}">
                        </c:if>
                        <c:if test="${not sessionScope.admin}" >
                            <input type="hidden" name="role" value="0">
                        </c:if>
                    </div>
                    <label>&nbsp</label><input type="submit" value="确认" class="list">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>