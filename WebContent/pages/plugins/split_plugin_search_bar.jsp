<%@page pageEncoding="UTF-8"%>
<%--
<jsp:include page="split_plugin_search_bar.jsp">
	<jsp:param name="url" value="<%=URL%>"/>
	<jsp:param name="allRecorders" value="<%=allRecorders%>"/>
	<jsp:param name="columnData" value="<%=columnData%>"/>
	<jsp:param name="keyWord" value="<%=keyWord%>"/>
	<jsp:param name="column" value="<%=column%>"/>
</jsp:include>
--%>
<%
	request.setCharacterEncoding("UTF-8");
	String url = request.getParameter("url");	//提交路径
	String columnData = request.getParameter("columnData");	//查询列的数据
	String keyWord = request.getParameter("keyWord");	//模糊查询关键字
	String column = request.getParameter("column");
	int allRecorders = 0;
	if("null".equals(column)||column==null||"".equals(column)){
		column = "";
	}
	if("null".equals(keyWord)||keyWord==null||"".equals(keyWord)){
		keyWord = "";
	}
%>
<%
	try{
		allRecorders = Integer.parseInt(request.getParameter("allRecorders"));
	}catch(Exception e){

	}
%>
<div id="searchDiv">
	<form action="<%=url%>" method="post">
<%
	if(!(columnData==null||"".equals(columnData))){
%>
		<select id="col" name="col">
<%
			String result[] = columnData.split("\\|");
			for(int x=0;x<result.length;x++){
				String temp[] = result[x].split(":");
%>
			<!--循环生成列表项-->
			<option value=<%=temp[1]%> <%=temp[1].equals(column)? "selected":""%>><%=temp[0]%></option>
<%
			}
%>
		</select>
<%
	}
%>
		<input type="text" name="kw" id="kw" value="<%=keyWord%>">
		<input type="submit" value="查询">
		<p>查询一共返回“<%=allRecorders%>”条记录</p>
	</form>
</div>