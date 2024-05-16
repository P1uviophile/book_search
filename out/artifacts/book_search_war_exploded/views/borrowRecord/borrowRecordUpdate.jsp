<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <span>当前位置>借阅信息>借阅表单</span>
            <div class="bottom fr">

            </div>
        </div>

        <div class="unit-information">
            <div class="unit">
                <p class="unit-content">借阅信息</p>
            </div>
            <div class="unit-list clearfix">
                <div class="fl">
                    <form action="<c:url value="/borrowRecord/saveOrUpdate"/>" method="post">
                        <div class="list ">
                            <input type="hidden" name="type" value="save">
                            <c:if test="${not empty borrowRecord}">
                                <input type="hidden" name="record_id" value="${borrowRecord.record_id}">
                                <input type="hidden" name="returned_root" value="${borrowRecord.returned}"/>
                            </c:if>
                            <c:if test="${sessionScope.admin == false}">
                                <input type="hidden" name="user_id" value="${borrowRecord.user_id}"/>
                                <input type="hidden" name="book_id" value="${borrowRecord.book_id}"/>
                                <input type="hidden" name="borrow_date" value="${borrowRecord.borrow_date}"/>
                                <input type="hidden" name="return_date" value="${borrowRecord.return_date}"/>

                            </c:if>
                            <c:if test="${sessionScope.admin}">
                                <script type="text/javascript">
                                    clearHiddenFields();
                                </script>
                                <label>客户ID</label> : <input type="text" name="user_id" value="${borrowRecord.user_id}"/>
                                <label>书籍ID</label> : <input type="text" name="book_id" value="${borrowRecord.book_id}"/>
                                <br><br>
                                <label>借阅日期</label> : <input type="date" name="borrow_date" value="${borrowRecord.borrow_date}"/>
                                <label>归还日期</label> : <input type="date" name="return_date" value="${borrowRecord.return_date}"/>
                                <br><br>
                            </c:if>
                            <c:if test="${not empty borrowRecord}">
                                <label>归还</label> :
                                <input name="returned" type="radio" value="1" checked="checked">是
                                <input name="returned" type="radio" value="0">否
                            </c:if>
                            <c:if test="${empty borrowRecord}">
                                <label>归还</label> :
                                <input name="returned" type="radio" value="0" checked="checked">否
                            </c:if>
                        </div>
                        <label>&nbsp</label><input type="submit" value="确认" class="list" >
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function clearHiddenFields() {
        // 清除隐藏字段的值
        document.getElementsByName("user_id")[0].value = "";
        document.getElementsByName("book_id")[0].value = "";
        document.getElementsByName("borrow_date")[0].value = "";
        document.getElementsByName("return_date")[0].value = "";
        document.getElementsByName("returned")[0].value = "";
    }
</script>
</body>
</html>

