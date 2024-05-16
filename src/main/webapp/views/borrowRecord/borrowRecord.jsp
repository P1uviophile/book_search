<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    padding-left: 5px;
    padding-right: 5px;
    border-collapse:collapse;
  }
  .table11_6 td.date {
    font-size:1em;
    text-align:center;
    padding-left:5px;
    padding-right: 5px;
    border-collapse:collapse;
  }
  .table11_6 td.operate {
    font-size:1em;
    text-align:center;
    padding-left:5px;
    padding-right: 5px;
    border-collapse:collapse;
  }
  .table11_6 th,.table11_6 td {
    border: 1px solid #73b4e7;
    border-width:1px 0 1px 0;
    border:2px inset #ffffff;
  }
  .table11_6 tr {
    border: 1px solid #ffffff;
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
      <span>当前位置>借阅信息</span>
      <c:if test="${sessionScope.admin}">
        <div class="bottom fr">
          <a href="borrowRecord/borrowRecordUpdate">添加借阅信息</a>
        </div>
      </c:if>
    </div>
    <div class="unit-information">
      <div class="unit">
        <p class="unit-content">借阅信息</p>
      </div>
    </div>
    <c:if test="${sessionScope.admin}">
        <div class="search bar1" align="center">
          <form action="<c:url value="/borrowRecord/query"/>">
            <input type="hidden" name="type" value="query">
            <input type="text" placeholder="输入ID        " name="condition"/>
            <td><!--单选按钮-->
              <input type="radio" name="queryChoose" value="user" checked="checked">用户ID查询
              <input type="radio" name="queryChoose" value="book">书籍ID查询
            </td>
            <input type="submit" value="搜索" align="center">
          </form>
        </div>
    </c:if>
    <br>
    <div class="table-container">
      <table class=table11_6 align="center">
        <c:choose>
          <c:when test="${sessionScope.admin}">
            <tr>
              <th>借阅ID</th><th>客户ID</th><th>书籍ID</th><th>借阅日期</th><th>归还日期</th><th>归还状态</th><th>操作</th>
            </tr>
            <c:forEach items="${requestScope.list}" var="borrowRecord">
              <tr>
                <td class="id">${borrowRecord.record_id}</td>
                <td class="id">${borrowRecord.user_id}</td>
                <td class="id">${borrowRecord.book_id}</td>
                <td class="id">${borrowRecord.borrow_date}</td>
                <td class="id">${borrowRecord.return_date}</td>
                <td class="id">${borrowRecord.returned}</td>
                <td class="operate">
                  <a href="<c:url value="/borrowRecord/queryById/${borrowRecord.record_id}"/>" class="table-view">修改</a>
                </td>
              </tr>
            </c:forEach>
          </c:when>
          <c:otherwise>
            <tr>
              <th>借阅ID</th><th>客户ID</th><th>书籍ID</th><th>借阅日期</th><th>归还日期</th><th>归还状态</th><th>操作</th>
            </tr>
            <c:forEach items="${requestScope.list}" var="borrowRecord">
              <tr>
                <c:if test="${borrowRecord.user_id == sessionScope.loginUserId}">
                  <td class="id">${borrowRecord.record_id}</td>
                  <td class="id">${borrowRecord.user_id}</td>
                  <td class="id">${borrowRecord.book_id}</td>
                  <td class="id">${borrowRecord.borrow_date}</td>
                  <td class="id">${borrowRecord.return_date}</td>
                  <td class="id">${borrowRecord.returned}</td>
                  <c:if test="${borrowRecord.returned == 0}">
                    <td class="operate">
                      <a href="<c:url value="/borrowRecord/queryById/${borrowRecord.record_id}"/>" class="table-view">归还</a>
                    </td>
                  </c:if>
                  <c:if test="${borrowRecord.returned != 0}">
                    <td class="operate">
                    </td>
                  </c:if>
                </c:if>
              </tr>
            </c:forEach>
          </c:otherwise>
        </c:choose>
      </table>
    </div>
    <br>
  </div>
</div>
</body>
</html>

