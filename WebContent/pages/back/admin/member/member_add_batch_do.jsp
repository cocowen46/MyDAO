<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="cn.mldn.util.dbc.*"%>
<%@ page import="cn.mldn.vo.*"%>
<%@ page import="cn.mldn.util.factory.*"%>
<%@ page import="cn.mldn.service.*"%>
<%@ page import="cn.mldn.dao.*"%>
<%@ page import="java.util.*" %>
<%!//写几个常量用于定义提交的路径，也就是说以后所有的连接路径都写在常量里面
	public static final String MEMBER_ADD_URL = "pages/back/admin/member/member_add_batch.jsp";
%>
	<jsp:include page="/pages/plugins/include_head.jsp"/>
<% 	request.setCharacterEncoding("UTF-8");
	//step1:需要接收所有的请求参数
	String memberinfo = request.getParameter("memberinfo").trim();
	Set<Member> allMembers = new HashSet<Member>();
	String[] result = memberinfo.split(",");
	for(int x=0;x<result.length;x++){
		String[] temp = result[x].split(":");
		Member vo = new Member();
		vo.setMid(temp[0]);
		vo.setName(temp[1]);
		vo.setBirthday(new java.text.SimpleDateFormat("yyyy-MM-dd").parse(temp[2]));
		vo.setSalary(Double.parseDouble(temp[3]));
		vo.setDel(Integer.parseInt(temp[4]));
		vo.setNote(temp[5]);
		allMembers.add(vo);
	}
	//step2:调用业务层进行数据的操作处理
	boolean flag = false;	//操作的最终标记
	IMemberService memberService = Factory.getServiceInstance("member.service");
	flag = memberService.addBatch(allMembers);	//调用业务层执行操作
	String msg = "用户信息批量增加失败！";
	if(flag){	//操作成功
		msg = "用户信息批量增加成功！";
	}
%>
<jsp:include page="/pages/plugins/forward.jsp">
	<jsp:param name="msg" value="<%=msg%>"/>
	<jsp:param name="url" value="<%=MEMBER_ADD_URL%>"/>
</jsp:include>
<jsp:include page="/pages/plugins/include_foot.jsp"/>
