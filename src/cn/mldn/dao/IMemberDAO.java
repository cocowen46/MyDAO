package cn.mldn.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import cn.mldn.util.dao.IBaseDAO;
import cn.mldn.vo.Member;

public interface IMemberDAO extends IBaseDAO<String, Member> {
	/**
	 * 进行数据的全部分页信息的显示
	 * @param currentPage
	 * @param lineSize
	 * @param del 删除的标志位，可以列出的应该是未删除的数据
	 * @return
	 * @throws SQLException
	 */
	public List<Member> findAllSplitByDelete(Integer currentPage,Integer lineSize,Integer del) throws SQLException;
	/**
	 * 进行模糊查询的分页操作显示
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
	 * 根据删除标志位进行信息统计
	 * @param del
	 * @return
	 * @throws SQLException
	 */
	public Integer getAllCountByDelete(Integer del) throws SQLException;
	/**
	 * 根据删除标志位进行模糊查询的数据量统计
	 * @param column
	 * @param keyWord
	 * @param del
	 * @return
	 * @throws SQLException
	 */
	public Integer getAllCountByDelete(String column,String keyWord,Integer del) throws SQLException;
	/**
	 * 进行数据的批量增加操作处理
	 * @param allMembers 没有重复的Member数据集合
	 * @return
	 * @throws SQLException
	 */
	public boolean doCreateBatch(Set<Member> allMembers) throws SQLException;
	/**
	 * 传递要进行删除的数据，本次删除属于更新del字段的操作
	 * @param ids	所有准备删除的数据
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateDelete(Set<String> ids) throws SQLException;
	/**
	 * 根据del的内容来进行全部数据的查询显示
	 * @param del 删除标记，有以下取值<br>
	 * del = 0:表示列出所有未删除的用户信息
	 * del = 1：表示列出所有删除的用户信息
	 * @return	
	 */
	public List<Member> findAllByDelete(Integer del) throws SQLException;
	/**
	 * 根据用户的id和删除标记del进行用户数据的查询
	 * @param id
	 * @param del
	 * @return
	 * @throws SQLException
	 */
	public Member findByIdAndDelete(String id,Integer del) throws SQLException;
	/**
	 * 根据用户的删除标记的状态进行用户数据的修改，修改时不去修改id和del字段的内容
	 * @param vo
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateByDelete(Member vo,Integer del) throws SQLException;
}
