package cn.mldn.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.vo.Member;

public interface IMemberService {
	/**
	 * �������ݵķ�ҳ�б���ʾ��ֻ����ʾδ��ɾ����������Ϣ���ò��������ģ����ѯ���к͹ؼ��ֽ��з����ĵ��ã�<br>
	 * 1.���column��keyWord������Ϊ�գ���ô��ʹ�ò�����ģ����ѯ�Ĳ�������;<br>
	 * 2.���column��keyWord������ͬʱ��Ϊ�գ���ô��ʹ�ý���ģ����ѯ�Ĳ�������;<br>
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyWord
	 * @return	���ص���Ϣ�����������ݣ�<br>
	 * 1.key=allMembers value = IMemberDAO.findAllSplit();<br>
	 * 2.key=memberCount value = IMemberDAO.getAllCount();
	 * @throws Exception
	 */
	public Map<String,Object> list(int currentPage,int lineSize,String column,String keyWord) throws Exception;
	/**
	 * ִ�����ݵ���������
	 * @param allMembers
	 * @return
	 * @throws Exception
	 */
	public boolean addBatch(Set<Member> allMembers) throws Exception;
	/**
	 * �������ݵ�ɾ������ɾ�������Ӧ�������ݲ�ĸ��²���������IMemberDAO.doUpdateDelete()����
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public boolean delete(Set<String> ids) throws Exception;
	/**
	 * ��ҵ����Ҫִ�����²�����<br>
	 * 1.ͨ��IMemberDAO.findById()�ж�Ҫ׷�ӵ��û�ID�Ƿ���ڣ�<br>
	 * 2.����û���ID�����ڣ���ʹ��IMemberDAO.doCreate()�����û����Ӵ���<br>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean add(Member vo) throws Exception;
	/**
	 * ����ɾ����ǽ���ȫ���û�����ʾ
	 * @param del
	 * @return
	 * @throws Exception
	 */
	public List<Member> listByDelete(int del) throws Exception;
	/**
	 * �û�ֻ���ĸ���idȡ��һ��Ҫ�޸ĵ�Member���ݣ�������Ҫע��һ��ǰ�᣺���뱣֤���û�û�б��߼�ɾ��
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Member editPre(String id) throws Exception;
	/**
	 * �����û����ݵĸ��²������û�ֻ����Ҫ���µ����ݣ�����δɾ�����ж���ҵ������
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean edit(Member vo) throws Exception;
}
