<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="cn.mldn.util.dbc.*"%>
<%@ page import="cn.mldn.vo.*"%>
<%@ page import="cn.mldn.util.factory.*"%>
<%@ page import="cn.mldn.service.*"%>
<%@ page import="cn.mldn.dao.*"%>
<%!//写几个常量用于定义提交的路径，也就是说以后所有的连接路径都写在常量里面
	public static final String MEMBER_LIST_URL = "pages/back/admin/member/member_list.jsp";
%>
	<jsp:include page="/pages/plugins/include_head.jsp"/>
<% 	request.setCharacterEncoding("UTF-8");
	//step1:需要接收所有的请求参数
	Member vo = new Member();
	vo.setMid(request.getParameter("mid"));
	vo.setName(request.getParameter("name"));
	vo.setBirthday(new java.text.SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday")));
	vo.setSalary(Double.parseDouble(request.getParameter("salary")));
	vo.setNote(request.getParameter("note"));
	//step2:调用业务层进行数据的操作处理
	boolean flag = false;	//操作的最终标记
	IMemberService memberService = Factory.getServiceInstance("member.service");
	flag = memberService.edit(vo);	//调用业务层执行操作
	String msg = "用户信息修改失败！";
	if(flag){	//操作成功
		msg = "用户信息修改成功！";
	}
%>
<jsp:include page="/pages/plugins/forward.jsp">
	<jsp:param name="msg" value="<%=msg%>"/>
	<jsp:param name="url" value="<%=MEMBER_LIST_URL%>"/>
</jsp:include>
<jsp:include page="/pages/plugins/include_foot.jsp"/>
