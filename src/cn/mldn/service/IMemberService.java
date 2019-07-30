package cn.mldn.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.vo.Member;

public interface IMemberService {
	/**
	 * 进行数据的分页列表显示，只是显示未被删除的数据信息，该操作会根据模糊查询的列和关键字进行方法的调用：<br>
	 * 1.如果column或keyWord的内容为空，那么则使用不进行模糊查询的操作方法;<br>
	 * 2.如果column或keyWord的内容同时不为空，那么则使用进行模糊查询的操作方法;<br>
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyWord
	 * @return	返回的信息包括如下内容：<br>
	 * 1.key=allMembers value = IMemberDAO.findAllSplit();<br>
	 * 2.key=memberCount value = IMemberDAO.getAllCount();
	 * @throws Exception
	 */
	public Map<String,Object> list(int currentPage,int lineSize,String column,String keyWord) throws Exception;
	/**
	 * 执行数据的批量增加
	 * @param allMembers
	 * @return
	 * @throws Exception
	 */
	public boolean addBatch(Set<Member> allMembers) throws Exception;
	/**
	 * 进行数据的删除处理，删除处理对应的是数据层的更新操作，调用IMemberDAO.doUpdateDelete()方法
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public boolean delete(Set<String> ids) throws Exception;
	/**
	 * 本业务需要执行以下操作：<br>
	 * 1.通过IMemberDAO.findById()判断要追加的用户ID是否存在；<br>
	 * 2.如果用户的ID不存在，则使用IMemberDAO.doCreate()进行用户增加处理<br>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean add(Member vo) throws Exception;
	/**
	 * 根据删除标记进行全部用户的显示
	 * @param del
	 * @return
	 * @throws Exception
	 */
	public List<Member> listByDelete(int del) throws Exception;
	/**
	 * 用户只关心根据id取得一个要修改的Member数据，但是需要注意一个前提：必须保证该用户没有被逻辑删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Member editPre(String id) throws Exception;
	/**
	 * 进行用户数据的更新操作，用户只关心要更新的数据，对于未删除的判断由业务层控制
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean edit(Member vo) throws Exception;
}
