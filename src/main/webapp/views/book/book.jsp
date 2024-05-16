<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<style>

    .table-container {
        max-height: 400px; /* 设置最大高度，适当调整以适应你的需求 */
        overflow: auto; /* 添加滚动条 */
    }
    <%--表格设置--%>
    .table11_6 table {
        width:780px;
        margin:15px 0;
        border:0;
    }
    .table11_6 th {
        background-color:#96C7ED;
        color:#000000
    }
    /**
    .table11_6,.table11_6 th,.table11_6 td {
        font-size:0.95em;
        text-align:center;
        padding-left:50px;
        padding-right: 50px;
        border-collapse:collapse;
    }
     */
    .table11_6,.table11_6 th,.table11_6 td {
        font-size:0.95em;
        text-align:center;
        padding: 10px 50px;
        border-collapse:collapse;
    }
    .table11_6 td.id {
        font-size:1em;
        text-align:center;
        padding-left: 20px;
        padding-right: 20px;
        border-collapse:collapse;
    }
    .table11_6 td.name {
        font-size:1em;
        text-align:center;
        padding-left:70px;
        padding-right: 70px;
        border-collapse:collapse;
    }
    .table11_6 td.operate {
        font-size:1em;
        text-align:center;
        padding-left:40px;
        padding-right: 40px;
        border-collapse:collapse;
    }
    .table11_6 th,.table11_6 td {
        border: 1px solid #73b4e7;
        border-width:1px 0 1px 0;
        border:2px inset #ffffff;
    }
    .table11_6 tr {
        border: 1px solid #ffffff;
        overflow: hidden;
    }
    .table11_6 tr:nth-child(odd){
        background-color:#dcecf9;
    }
    .table11_6 tr:nth-child(even){
        background-color:#ffffff;
    }

    <%-- 以下是搜索框设置 --%>
    input, button {
        border: none;
        outline: none;
    }
    input {
        width: 80%;
        height: 42px;
    }
    button {
        height: 42px;
        width: 42px;
        position: absolute;
    }
    .bar1 {
        background: #A3D0C3;
    }
    .bar1 input {
        border: 2px solid #7BA7AB;
        border-radius: 5px;
        background: #F9F0DA;
        color: #9E9C9C;
    }
    .bar1 button {
        background: url("../images/soso-white.png");
        border: 2px solid #7BA7AB;
        border-radius: 5px;
        background: #F9F0DA;
        color: #151414;
    }
</style>

<body>
<div class="container">
    <div class="content" style="margin: auto">
        <div class="information-title">
            <span>当前位置>在线购物</span>
            <c:choose>
                <c:when test="${sessionScope.admin}">
                    <div class="bottom fr">
                        <a href="<c:url value="/book/bookUpdate"/>">添加书籍</a>
                    </div>
                </c:when>
            </c:choose>
        </div>
        <div class="unit-information">
            <div class="unit">
                <p class="unit-content">书籍信息</p>
            </div>
        </div>
        <div class="search bar1" align="center">
            <form action="<c:url value="/book/queryCondition"/>">
                <input type="hidden" name="type" value="query">
                <input type="text" placeholder="输入书名        (模糊搜索）" name="condition"/>
                <input type="submit" value="搜索" align="center">
            </form>
        </div>
        <br>
        <div class="table-container">
            <table class=table11_6 align="center">
                <tr>
                    <th>ID</th><th>书名</th><th>作者</th><th>可借阅数</th><th>操作</th>
                </tr>
                <c:forEach items="${requestScope.list}" var="book">
                    <tr>
                        <td class="bookId">${book.book_id}</td>
                        <td class="title">${book.title}</td>
                        <td class="author">${book.author}</td>
                        <td class="available_copies">${book.available_copies}</td>

                        <c:choose>
                            <c:when test="${sessionScope.admin}">
                                <td class="operate">
                                    <a href="<c:url value="/book/queryById/${book.book_id}"/>" class="table-view">修改</a>
                                    <a>/</a>
                                    <a href="JavaScript:deleteCommodity(${book.book_id})" class="table-view">删除</a>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${book.available_copies > 0}">
                                    <td class="operate">
                                        <a href="JavaScript:buyingCommodity('${book.book_id}','${sessionScope.loginUserId}')" class="table-view">借阅</a>
                                    </td>
                                </c:if>
                                <c:if test="${book.available_copies == 0}">
                                    <td class="operate"></td>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <br>
    </div>
</div>
</div>
</body>
<script>
    function deleteCommodity(book_id){
        if(window.confirm("确定要删除id为"+book_id+"的商品吗？")){
            location.href = "<c:url value='/book'/>?type=delete&book_id="+book_id;
        }
    }
    function buyingCommodity(book_id, user_id) {
        var day = prompt("请输入借阅书籍" + book_id + "的天数:", "1");

        if (day !== null) {
            // 验证输入是否为数字
            if (isNaN(day) || day <= 0) {
                alert("请输入有效的数量和天数！");
                return;
            }

            var today = new Date();
            var borrow_day = today.getDate();
            var return_day = borrow_day + parseInt(day); // 将 day 解析为整数

            // 用户输入了有效的数量和天数
            if (window.confirm("确定要借阅id为" + book_id + "的书籍吗？")) {
                // 发送到服务器的 URL，注意要将参数进行 URL 编码
                var url = "<c:url value='/book'/>?type=borrow" +
                    "&book_id=" + encodeURIComponent(book_id) +
                    "&user_id=" + encodeURIComponent(user_id) +
                    "&borrow_day=" + encodeURIComponent(borrow_day) +
                    "&return_day=" + encodeURIComponent(return_day);
                // 跳转到 URL
                location.href = url;
            }
        } else {
            // 用户点击了取消按钮
            alert("取消借阅");
        }
    }

</script>
</html>