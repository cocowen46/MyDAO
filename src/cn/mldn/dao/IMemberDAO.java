package cn.mldn.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import cn.mldn.util.dao.IBaseDAO;
import cn.mldn.vo.Member;

public interface IMemberDAO extends IBaseDAO<String, Member> {
	/**
	 * �������ݵ�ȫ����ҳ��Ϣ����ʾ
	 * @param currentPage
	 * @param lineSize
	 * @param del ɾ���ı�־λ�������г���Ӧ����δɾ��������
	 * @return
	 * @throws SQLException
	 */
	public List<Member> findAllSplitByDelete(Integer currentPage,Integer lineSize,Integer del) throws SQLException;
	/**
	 * ����ģ����ѯ�ķ�ҳ������ʾ
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyWord
	 * @param del
	 * @return
	 * @throws SQLException
	 */
	public List<Member> findAllSplitByDelete(Integer currentPage,Integer lineSize,String column,String keyWord,Integer del) throws SQLException;
	/**
	 * ����ɾ����־λ������Ϣͳ��
	 * @param del
	 * @return
	 * @throws SQLException
	 */
	public Integer getAllCountByDelete(Integer del) throws SQLException;
	/**
	 * ����ɾ����־λ����ģ����ѯ��������ͳ��
	 * @param column
	 * @param keyWord
	 * @param del
	 * @return
	 * @throws SQLException
	 */
	public Integer getAllCountByDelete(String column,String keyWord,Integer del) throws SQLException;
	/**
	 * �������ݵ��������Ӳ�������
	 * @param allMembers û���ظ���Member���ݼ���
	 * @return
	 * @throws SQLException
	 */
	public boolean doCreateBatch(Set<Member> allMembers) throws SQLException;
	/**
	 * ����Ҫ����ɾ�������ݣ�����ɾ�����ڸ���del�ֶεĲ���
	 * @param ids	����׼��ɾ��������
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateDelete(Set<String> ids) throws SQLException;
	/**
	 * ����del������������ȫ�����ݵĲ�ѯ��ʾ
	 * @param del ɾ����ǣ�������ȡֵ<br>
	 * del = 0:��ʾ�г�����δɾ�����û���Ϣ
	 * del = 1����ʾ�г�����ɾ�����û���Ϣ
	 * @return	
	 */
	public List<Member> findAllByDelete(Integer del) throws SQLException;
	/**
	 * �����û���id��ɾ�����del�����û����ݵĲ�ѯ
	 * @param id
	 * @param del
	 * @return
	 * @throws SQLException
	 */
	public Member findByIdAndDelete(String id,Integer del) throws SQLException;
	/**
	 * �����û���ɾ����ǵ�״̬�����û����ݵ��޸ģ��޸�ʱ��ȥ�޸�id��del�ֶε�����
	 * @param vo
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateByDelete(Member vo,Integer del) throws SQLException;
}
