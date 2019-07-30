/**
 * 进行批量删除按钮的JS处理，在此函数中会对是否选中数据进行判断
 * 如果数据被选中则会将数据重新组合成“id,id,id..”的形式，并将其传入到其他页面，这个数据的参数
 * 名称固定为ids
 * @param eleId	元素id	
 * @param delUrl 删除操作跳转路径
 * @returns
 */
function handleDelete(eleId,delUrl){
	eleObject = document.all(eleId); 	//获取指定组件的信息
	idsValue = "";	//初始化字符串内容
	if(eleObject.length==undefined){	//只有一个元素
		if(eleObject.checked){	//元素被选中
			idsValue+=eleObject.value;
		}
	}else{
		for(x=0;x<eleObject.length;x++){
			if(eleObject[x].checked){  //元素被选中
				idsValue+=eleObject[x].value+",";
			}
		}
	}
	if(idsValue==""){	//如果现在数据内容没有改变，则表示没有数据被选中
		alert("对不起，您没有选择任何数据进行删除");
	}else{
		if(window.confirm("您确定要执行此操作吗")){
			window.location = delUrl+"?ids="+idsValue; 	//地址重写的方式进行页面跳转
		}
	}
	
}
/**
 * 实现复选框全选操作控制的可重用函数
 * @param ctlId	发出全选指令的控制组件的名称
 * @param eleId	实现全选的控制操作
 * @returns
 */
function checkboxSelectAll(ctlId,eleId){
	ctlObject = document.getElementById(ctlId);
	eleObject = document.all(eleId);	//获取指定的组件信息
	if(eleObject.length==undefined){	//只有一行记录
		eleObject.checked = ctlObject.checked; 
	}else{
		for(x=0;x<eleObject.length;x++){
			eleObject[x].checked = ctlObject.checked;
		}
	}
}
function changeColor(obj,color){
	obj.bgColor=color;
}
/**
 * 取得HTML元素对象的操作
 */
function ele(eleId){
	return document.getElementById(eleId);
}
/**
 * 取得id相同的全部HTML元素
 */
function eleAll(eleId){
	return document.all(eleId);
}
/**
 * 为HTML元素对象绑定事件处理
 */
function listener(eleId,eventType,fun){
	var obj = ele(eleId);
	obj.addEventListener(eventType,fun,false);

}
/**
 * 验证文本内容是否为空
 */
function validateEmpty(eleId){
	var obj = ele(eleId);	//取得指定Id的对象
	if(obj!=null){	//如果该元素对象存在
		if(obj.value==""){//验证失败
			setFailureStyle(obj);
			return false;
		}else{
			setSuccessStyle(obj);
			return true;
		}
	}
	return false;
}
/**
 * 正则验证
 */
function validateRegex(eleId,regex){
	var obj = ele(eleId);
	if(validateEmpty(eleId)){//元素内容非空再进行正则验证
		if(!regex.test(obj.value)){//正则验证没有通过
			setFailureStyle(obj);
			return false;
		}else{
			setSuccessStyle(obj);
			return true;
		}
	}
}
/**
 * 数字验证
 */
function validateNumber(eleId){
	return validateRegex(eleId,/^\d{1,5}(\.\d{1,2})?$/);
}
/**
 * 日期验证
 */
function validateDate(eleId){
	return validateRegex(eleId,/^\d{4}-\d{2}-\d{2}$/);
}
/**
 * 设置成功样式与信息提示
 */
function setSuccessStyle(obj){
	if(obj!=null){
		obj.className = "success";
		var spanObj = ele(obj.id+"Span");
		if(spanObj!=null){
			spanObj.innerHTML="<font color='green'>√</font>";
		}
	}
}
/**
 * 设置失败样式与信息提示
 */
function setFailureStyle(obj){
	if(obj!=null){
		obj.className = "failure";
		var spanObj = ele(obj.id+"Span");
		if(spanObj!=null){
			spanObj.innerHTML="<font color='red'>×</font>";
		}
	}
}