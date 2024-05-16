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
      <span>当前位置>在线购物>商品清单</span>
      <div class="bottom fr">

      </div>
    </div>

    <div class="unit-information">
      <div class="unit">
        <p class="unit-content">商品信息</p>
      </div>
      <div class="unit-list clearfix">
        <div class="fl">
              <c:if test="${not empty book}">
                <form action="<c:url value="/book/update"/>" method="post">
                  <div class="list ">
                    <input type="hidden" name="type" value="update">
                    <input type="hidden" name="book_id" value="${book.book_id}">
                    <input type="hidden" name="total_copies" value="${book.total_copies}">
                    <label>书名</label> : <input type="text" name="title" value="${book.title}"/>
                    <label>作者</label> : <input type="text" name="author" value="${book.author}"/>
                    <br><br><br>
                    <label>可借阅数</label> : <input type="text" name="available_copies" value="${book.available_copies}"/>
                  </div>
                  <label>&nbsp</label><input type="submit" value="确认" class="list">
                </form>
              </c:if>
              <c:if test="${empty book}">
              <form action="<c:url value="/book/save"/>" method="post">
                <div class="list ">
                  <input type="hidden" name="type" value="save">
                  <input type="hidden" name="book_id" value=""/>
                  <label>书名</label> : <input type="text" name="title" value=""/>
                  <label>作者</label> : <input type="text" name="author" value=""/>
                  <br><br><br>
                  <label>总数</label> : <input type="text" name="total_copies" value=""/>
                  <label>可借阅数</label> : <input type="text" name="available_copies" value=""/>

                </div>
                <label>&nbsp</label><input type="submit" value="确认" class="list">
              </form>
              </c:if>
            </div>
        </div>
      </div>
    </div>
  </div>
</div>

</body>
</html>
