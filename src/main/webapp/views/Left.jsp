<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="<c:url value='css/Left.css'/>">
</head>
<body>
<div class="nav">
    <div class="nav-list">
        <div class="nav-tit">
            <a href="<c:url value='/user'/>" target="main">
                <img src="<c:url value='images/personal-msg.png'/>" alt="">
                <span>用户管理</span>
            </a>
        </div>
        <div class="nav-tit" id="personal">
            <img src="<c:url value='images/soso-white.png'/>" alt="">
            <span>读书读书</span>
            </a>
        </div>
        <div class="personal-list" id="personal_child">
            <ul>
                <li><a href="<c:url value='/book'/>" target="main">书籍目录</a></li>
                <li><a href="<c:url value='/borrowRecord'/>" target="main">借阅信息</a></li>
            </ul>
        </div>
    </div>
</div>

<script src="<c:url value='js/jquery-1.9.1.min.js'/>" type="text/javascript" charset="utf-8"></script>
<script>
    $(document).ready(function(){
        $('#personal').on('click',function(){
            $('#personal_child').fadeToggle(300);
        });
        let aLi = $('#personal_child li');
        aLi.on('click',function(){
            $(this).addClass('active').siblings('li').removeClass('active');
        })
    });
</script>
</body>
</html>
