package cn.mldn.util.factory;

import cn.mldn.util.MessageUtil;
import cn.mldn.util.proxy.ServiceProxy;

public class Factory {
	private static final MessageUtil DAO_MESSAGE = new MessageUtil("cn.mldn.util.message.dao");
	private static final MessageUtil SERVICE_MESSAGE = new MessageUtil("cn.mldn.util.message.service");
	private Factory() {}
	@SuppressWarnings("unchecked")
	public static <T> T getDAOInstance(String daoName) {
		String className = DAO_MESSAGE.getText(daoName);
		T t = null;
		try {
			t=(T)Class.forName(className).getConstructor().newInstance();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	@SuppressWarnings("unchecked")
	public static <T> T getServiceInstance(String serviceName) {
		String className = SERVICE_MESSAGE.getText(serviceName);
		T t = null;
		try {
			t = (T) new ServiceProxy().bind(Class.forName(className).getConstructor().newInstance());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return t;
	}
}
