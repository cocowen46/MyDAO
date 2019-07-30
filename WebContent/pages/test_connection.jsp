<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="cn.mldn.util.dbc.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>测试数据库连接</title>
</head>
<body>
	<%=DatabaseConnection.getConnection()%>
	<%DatabaseConnection.close();%>
</body>
</html>