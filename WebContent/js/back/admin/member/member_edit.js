window.onload=function(){	//动态绑定事件
	listener("mid","blur",validateMid);
	listener("name","blur",validateName);
	listener("birthday","blur",validateBirthday);
	listener("salary","blur",validateSalary);
	listener("memberForm","submit",function(e){
		if(validateMid()&validateName()&validateBirthday()&validateSalary()){
			this.submit();
		}else{
			e.preventDefault();
		}
	});
}
function validateMid(){
	return validateEmpty("mid");
}
function validateName(){
	return validateEmpty("name");
}
function validateBirthday(){
	return validateRegex("birthday",/^\d{4}-\d{2}-\d{2}$/);
}
function validateSalary(){
	return validateRegex("salary",/^\d+(\.\d{1,2})?$/);
}