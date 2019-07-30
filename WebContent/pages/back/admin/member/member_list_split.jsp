<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="cn.mldn.vo.*" %>
<%@ page import="cn.mldn.service.*" %>
<%@ page import="cn.mldn.util.factory.*" %>
<%!//写几个常量用于定义提交的路径，也就是说以后所有的连接路径都写在常量里面
	public static final String MEMBER_EDIT_URL = "pages/back/admin/member/member_edit.jsp";
	public static final String MEMBER_DELETE_URL = "pages/back/admin/member/member_delete_do.jsp";
	public static final String MEMBER_LIST_URL = "pages/back/admin/member/member_list_split.jsp";

%>
	<jsp:include page="/pages/plugins/include_head.jsp"/>
	<script type="text/javascript" src="js/back/admin/member/member_list.js"></script>
	<script type="text/javascript">
		var deleteUrl = "<%=request.getAttribute("basePath")%><%=MEMBER_DELETE_URL%>";
	</script>
<%
	int currentPage = 1; //当前所在页面，默认是1，即第一次加载时currentPage的值是1
	int lineSize = 1;	//表示每页显示的数据行数
	int allRecorders = 0;//保存总记录数的统计结果
	int pageSize = 1;	//总页数
	String columnData = "用户编号:mid|用户姓名:name";
	String column = request.getParameter("col");
	String keyWord = request.getParameter("kw");
%>
<%	//cp表示的是currentPage的参数内容，接收的都是String，需要将其转换为Integer
	try{
		currentPage = Integer.parseInt(request.getParameter("cp"));
	}catch(Exception e){
		//在第一次访问时cp的值为null,此时try块中的语句将出现异常，即不执行
		//这样currentPage的值是默认的“1”
	}
	try{
		lineSize = Integer.parseInt(request.getParameter("ls"));
	}catch(Exception e){
		
	}
%>
<%
	request.setCharacterEncoding("UTF-8");
	IMemberService memberService = Factory.getServiceInstance("member.service");
	Map<String,Object> map = memberService.list(currentPage, lineSize, column, keyWord);
	List<Member> all = (List<Member>)map.get("allMembers");
	allRecorders = (Integer)map.get("memberCount");
	Iterator<Member> iter = all.iterator();
%>
<jsp:include page="/pages/plugins/split_plugin_search_bar.jsp">
	<jsp:param name="url" value="<%=MEMBER_LIST_URL%>"/>
	<jsp:param name="allRecorders" value="<%=allRecorders%>"/>
	<jsp:param name="columnData" value="<%=columnData%>"/>
	<jsp:param name="keyWord" value="<%=keyWord%>"/>
	<jsp:param name="column" value="<%=column%>"/>
</jsp:include>

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
<jsp:include page="/pages/plugins/split_plugin_page_bar.jsp">
	<jsp:param name="url" value="<%=MEMBER_LIST_URL%>"/>
	<jsp:param name="currentPage" value="<%=currentPage%>"/>
	<jsp:param name="lineSize" value="<%=lineSize%>"/>
	<jsp:param name="allRecorders" value="<%=allRecorders%>"/>
	<jsp:param name="column" value="<%=column%>"/>
	<jsp:param name="keyWord" value="<%=keyWord%>"/>
</jsp:include>
	<jsp:include page="/pages/plugins/include_foot.jsp"/>
