<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<meta charset="UTF-8">
<title>首页</title>
</head>
<body>
    <a href="${path}/user/login">用户列表</a><br/>
    <a href="${path}/task/findTaskName">任务列表</a>
</body>
</html>