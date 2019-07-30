<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="cn.mldn.vo.*" %>
<%@ page import="cn.mldn.service.*" %>
<%@ page import="cn.mldn.util.factory.*" %>
<%!//写几个常量用于定义提交的路径，也就是说以后所有的连接路径都写在常量里面
	public static final String MEMBER_EDIT_URL = "pages/back/admin/member/member_edit.jsp";
	public static final String MEMBER_DELETE_URL = "pages/back/admin/member/member_delete_do.jsp";
%>
	<jsp:include page="/pages/plugins/include_head.jsp"/>
	<script type="text/javascript" src="js/back/admin/member/member_list.js"></script>
	<script type="text/javascript">
		var deleteUrl = "<%=request.getAttribute("basePath")%><%=MEMBER_DELETE_URL%>";
	</script>
<%
	request.setCharacterEncoding("UTF-8");
	IMemberService memberService = Factory.getServiceInstance("member.service");
	List<Member> all = memberService.listByDelete(0);	//列出所有未删除用户
	Iterator<Member> iter = all.iterator();
%>
<table border="0" style="width:100%" cellpadding="1" cellspacing="1" bgcolor="#F5F5F5">
	<tr bgcolor="#FFFFFF">
		<td style="width:5%"><input type="checkbox" id="selectAll"></td>
		<td style="width:20%">用户名：</td>
		<td style="width:20%">真实姓名：</td>
		<td style="width:10%">出生日期：</td>
		<td style="width:10%">基本工资：</td>
		<td style="width:40%">个人信息：</td>
	</tr>
<% 
	while(iter.hasNext()){
		Member vo = iter.next();
%>
		<tr bgcolor="#FFFFFF">
			<td><input type="checkbox" id="mid" value="<%=vo.getMid()%>"></td>
			<td><a href="<%=MEMBER_EDIT_URL%>?mid=<%=vo.getMid()%>"><%=vo.getMid()%></a></td>
			<td><%=vo.getName()%></td>
			<td><%=vo.getBirthday()%></td>
			<td><%=vo.getSalary()%></td>
			<td><%=vo.getNote()%></td>
		</tr>
<% 
	}
%>			
</table>
<button id="deleteBtn">删除选中的用户信息</button>
	<jsp:include page="/pages/plugins/include_foot.jsp"/>
