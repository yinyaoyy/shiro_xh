<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<% String web_path=request.getContextPath();%>
<html>
<body>

<h2>Hello!</h2>
<shiro:hasRole name="超级管理员">欢迎您超级管理员！</shiro:hasRole>
<shiro:hasRole name="主任">欢迎您主任！</shiro:hasRole>
</body>
</html>
