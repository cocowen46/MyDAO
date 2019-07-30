package cn.mldn.util.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import cn.mldn.util.dbc.DatabaseConnection;

public class ServiceProxy implements InvocationHandler {
	private Object target = null;//��ʵ����
	public Object bind(Object target) {
		this.target=target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object returnData = null;	//��ʵ���ⷽ���ķ���ֵ
		if(method.getName().startsWith("add")
				||method.getName().startsWith("edit")
				||method.getName().startsWith("delete")) {//�����������
			DatabaseConnection.getConnection().setAutoCommit(false);
			try {
				returnData = method.invoke(this.target, args);
				DatabaseConnection.getConnection().commit();	//û���쳣����������ύ
			}catch(Exception e) {	//�����쳣���������ع�
				DatabaseConnection.getConnection().rollback();
			}finally {
				DatabaseConnection.getConnection().close();	//�ر����ݿ�
			}
		}else {
			try {
				returnData = method.invoke(this.target, args);
			}catch(Exception e) {	//�����쳣���������ع�
				e.printStackTrace();
			}finally {
				DatabaseConnection.close();	//�ر����ݿ�
			}
		}
		return returnData;
	}
}
