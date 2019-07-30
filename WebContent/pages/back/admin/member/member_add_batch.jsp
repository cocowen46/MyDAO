<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="cn.mldn.util.dbc.*"%>
<%!//写几个常量用于定义提交的路径，也就是说以后所有的连接路径都写在常量里面
	public static final String MEMBER_ADD_URL = "pages/back/admin/member/member_add_batch_do.jsp";
%>
	<jsp:include page="/pages/plugins/include_head.jsp"/>
	<form action="<%=MEMBER_ADD_URL%>" method="post" id="memberForm">
		<table border="0" style="width:100%" cellpadding="1" cellspacing="1" bgcolor="#F5F5F5">
			<tr bgcolor="#FFFFFF">
				<td>批量添加用户信息：</td>
				<td>&nbsp;</td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td colspan="2"><textarea id="memberinfo" name="memberinfo" class="init">hello:你好:1921-10-10:1000.01:0:是个人 ,hello1:你好1:1921-10-11:1001.01:0:是个人1</textarea></td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td colspan="2">
					<input type="submit" value="批量增加">
					<input type="reset" value="重置">
				</td>
			</tr>
		</table>
	</form>
	<jsp:include page="/pages/plugins/include_foot.jsp"/>
