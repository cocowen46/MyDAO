<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="cn.mldn.util.dbc.*"%>
<%!//写几个常量用于定义提交的路径，也就是说以后所有的连接路径都写在常量里面
	public static final String MEMBER_ADD_URL = "pages/back/admin/member/member_add_do.jsp";
%>
	<jsp:include page="/pages/plugins/include_head.jsp"/>
	<script type="text/javascript" src="js/back/admin/member/member_add.js"></script>
	<form action="<%=MEMBER_ADD_URL%>" method="post" id="memberForm">
		<table border="0" style="width:100%" cellpadding="1" cellspacing="1" bgcolor="#F5F5F5">
			<tr bgcolor="#FFFFFF">
				<td style="width:10%">用户名：</td>
				<td style="width:40%"><input type="text" id="mid" name="mid" placeholder="用户名" class="init"></td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td>真实姓名：</td>
				<td><input type="text" id="name" name="name" placeholder="请填写真实姓名" class="init"></td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td>出生日期：</td>
				<td><input type="text" id="birthday" name="birthday" placeholder="请选择您的出生日期" class="init"></td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td>工资收入:</td>
				<td><input type="text" id="salary" name="salary" placeholder="请填写月薪收入" class="init"></td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td>个人简介：</td>
				<td>&nbsp;</td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td colspan="2"><textarea id="note" name="note" class="init"></textarea></td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td colspan="2">
					<input type="submit" value="增加">
					<input type="reset" value="重置">
				</td>
			</tr>
		</table>
	</form>
	<jsp:include page="/pages/plugins/include_foot.jsp"/>
