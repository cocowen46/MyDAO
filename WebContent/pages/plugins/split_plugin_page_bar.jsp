<%@page pageEncoding="UTF-8"%>
<%--
<jsp:include page="split_plugin_page_bar.jsp">
	<jsp:param name="url" value="<%=URL%>"/>
	<jsp:param name="currentPage" value="<%=currentPage%>"/>
	<jsp:param name="lineSize" value="<%=lineSize%>"/>
	<jsp:param name="allRecorders" value="<%=allRecorders%>"/>
	<jsp:param name="column" value="<%=column%>"/>
	<jsp:param name="keyWord" value="<%=keyWord%>"/>
</jsp:include>
--%>
<%
	request.setCharacterEncoding("UTF-8");
	int currentPage = 1; //当前所在页面，默认是1
	int lineSize = 1;	//表示每页显示的数据行数,默认是5
	int allRecorders = 0;//保存总记录数的统计结果
	int pageSize = 1;	//总页数
	String column = request.getParameter("column");
	String keyWord = request.getParameter("keyWord");
	String url = request.getParameter("url");	//请求路径
%>
<%
	try{
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}catch(Exception e){}
	try{
		lineSize = Integer.parseInt(request.getParameter("lineSize"));
	}catch(Exception e){}
	try{
		allRecorders = Integer.parseInt(request.getParameter("allRecorders"));
	}catch(Exception e){}
	if("null".equals(column)||column==null||"".equals(column)){
		column = "";
	}
	if("null".equals(keyWord)||keyWord==null||"".equals(keyWord)){
		keyWord = "";
	}
%>
<%
	int seed = 2;//定义一个种子数，用于判断是否会有“...”

	if(allRecorders>0){
		pageSize = (allRecorders+lineSize-1)/lineSize; //页码计算公式
	}
%>
<div id="pageCtl">
	<ul>
		<!--第一页-->
		<li>
		<%
			if(currentPage==1){
		%>		
				<span>1<span>
		<%
			}else{
		%>
			<a href="<%=url%>?cp=1&ls=<%=lineSize%>&col=<%=column%>&kw=<%=keyWord%>">1</a>
		<%
			}
		%>
		</li>
<%	//中间页面的处理
	
		if(pageSize>seed*2){//说明数据量很大，需要进行"..."省略号的出现,首页和末页始终出现
			if(currentPage<=seed*2){	//前4页的处理
				int startPage = 2;	//显示起始页
				int endPage = currentPage+seed*2;//显示终止页
				if(endPage>=pageSize){
					endPage = pageSize-1;
				}
				for(int x=startPage;x<=endPage;x++){	//到第7页
%>
					<li>
						<a href="<%=url%>?cp=<%=x%>&ls=<%=lineSize%>&col=<%=column%>&kw=<%=keyWord%>"><%=x%></a>
					</li>
<%				
				}
				if((currentPage+seed*2)<pageSize-1){
%>
					<li><span>...</span></li>
<%
				}
			}else{	//需要分两段显示了（第5页开始）
%>
				<li><span>...</span></li>
<%
				int startPage = currentPage-seed;
				int endPage = currentPage+seed;
				if(endPage>=pageSize){
					endPage=pageSize-1;
				}
				for(int x=startPage;x<=endPage;x++){
%>
					<li>
						<a href="<%=url%>?cp=<%=x%>&ls=<%=lineSize%>&col=<%=column%>&kw=<%=keyWord%>"><%=x%></a>
					</li>
<%
				}
				if((currentPage+seed*2)<pageSize){	//后面还有很多页
%>
					<li><span>...</span></li>
<%
				}else{	//后续的页码需要出现
					for(int x=currentPage+seed+1;x<pageSize;x++){
%>
						<li>
						<a href="<%=url%>?cp=<%=x%>&ls=<%=lineSize%>&col=<%=column%>&kw=<%=keyWord%>"><%=x%></a>
						</li>
<%
					}
%>
<%
				}
			}
		}
%>
<%
	if(pageSize!=1){
%>
		<!--最后一页-->
		<li>
		<%
			if(currentPage==pageSize){
		%>
				<span><%=pageSize%></span>
		<%
			}else{
		%>
				<a href="<%=url%>?cp=<%=pageSize%>&ls=<%=lineSize%>&col=<%=column%>&kw=<%=keyWord%>"><%=pageSize%></a>
		<%
			}
		%>

		</li>
<%
	}
%>	
	</ul>
</div>