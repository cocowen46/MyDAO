window.onload=function(){
	listener("selectAll","click",function(){
		checkboxSelectAll("selectAll","mid");
	});
	listener("deleteBtn","click",function(){
		handleDelete("mid",deleteUrl);
	});
}