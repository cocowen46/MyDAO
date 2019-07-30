package cn.mldn.util.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import cn.mldn.util.dbc.DatabaseConnection;

public class ServiceProxy implements InvocationHandler {
	private Object target = null;//真实主题
	public Object bind(Object target) {
		this.target=target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object returnData = null;	//真实主题方法的返回值
		if(method.getName().startsWith("add")
				||method.getName().startsWith("edit")
				||method.getName().startsWith("delete")) {//进行事务控制
			DatabaseConnection.getConnection().setAutoCommit(false);
			try {
				returnData = method.invoke(this.target, args);
				DatabaseConnection.getConnection().commit();	//没有异常则进行事务提交
			}catch(Exception e) {	//出现异常则进行事务回滚
				DatabaseConnection.getConnection().rollback();
			}finally {
				DatabaseConnection.getConnection().close();	//关闭数据库
			}
		}else {
			try {
				returnData = method.invoke(this.target, args);
			}catch(Exception e) {	//出现异常则进行事务回滚
				e.printStackTrace();
			}finally {
				DatabaseConnection.close();	//关闭数据库
			}
		}
		return returnData;
	}
}
