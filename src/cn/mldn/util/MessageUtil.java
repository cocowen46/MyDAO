package cn.mldn.util;

import java.util.ResourceBundle;

public class MessageUtil {
	private ResourceBundle resource = null;
	/**
	 * 
	 * @param baseName 资源文件的名称
	 */
	public MessageUtil(String baseName) {
		this.resource = ResourceBundle.getBundle(baseName);
	}
	public String getText(String key) {
		return this.resource.getString(key);
	}
}
