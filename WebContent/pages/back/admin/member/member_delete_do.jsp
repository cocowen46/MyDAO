<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="cn.mldn.util.dbc.*"%>
<%@ page import="cn.mldn.vo.*"%>
<%@ page import="cn.mldn.util.factory.*"%>
<%@ page import="cn.mldn.service.*"%>
<%@ page import="cn.mldn.dao.*"%>
<%@ page import="java.util.*"%>
<%!//写几个常量用于定义提交的路径，也就是说以后所有的连接路径都写在常量里面
	public static final String MEMBER_LIST_URL = "pages/back/admin/member/member_list.jsp";
%>
	<jsp:include page="/pages/plugins/include_head.jsp"/>
<% 	request.setCharacterEncoding("UTF-8");
	String ids = request.getParameter("ids");
	Set<String> set = new HashSet<String>();
	String[] result = ids.split(",");	//拆分字符串
	for(int x=0;x<result.length;x++){
		set.add(result[x]);
	}
	boolean flag = false;	//操作的最终标记
	IMemberService memberService = Factory.getServiceInstance("member.service");
	String msg = "用户删除失败！";
	flag = memberService.delete(set);	//调用业务层执行操作
	if(flag){	//操作成功
		msg = "用户删除成功！";
	}
%>
<jsp:include page="/pages/plugins/forward.jsp">
	<jsp:param name="msg" value="<%=msg%>"/>
	<jsp:param name="url" value="<%=MEMBER_LIST_URL%>"/>
</jsp:include>
<jsp:include page="/pages/plugins/include_foot.jsp"/>
