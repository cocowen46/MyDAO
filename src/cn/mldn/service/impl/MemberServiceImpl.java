package cn.mldn.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.dao.IMemberDAO;
import cn.mldn.service.IMemberService;
import cn.mldn.util.factory.Factory;
import cn.mldn.vo.Member;

public class MemberServiceImpl implements IMemberService {
	@Override
	public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		IMemberDAO memberDAO = Factory.getDAOInstance("member.dao");
		if(column==null||"".equals(column)||keyWord==null||"".equals(keyWord)) {
			map.put("allMembers", memberDAO.findAllSplitByDelete(currentPage, lineSize, 0));
			map.put("memberCount",memberDAO.getAllCountByDelete(0));
		}else {
			map.put("allMembers", memberDAO.findAllSplitByDelete(currentPage, lineSize,column,keyWord, 0));
			map.put("memberCount",memberDAO.getAllCountByDelete(column,keyWord,0));
		}
		return map;
	}
	@Override
	public boolean add(Member vo) throws Exception {
		IMemberDAO memberDAO = Factory.getDAOInstance("member.dao");
		if(memberDAO.findById(vo.getMid())==null) {	//用户不存在
			vo.setDel(0);	//新追加的用户是不可能被删除的，所以删除的逻辑位上设置为0
			return memberDAO.doCreate(vo);
		}else{
			return false;
		}
	}
	@Override
	public boolean addBatch(Set<Member> allMembers) throws Exception {
		if(allMembers==null||allMembers.size()==0) {
			return false;
		}
		IMemberDAO memberDAO = Factory.getDAOInstance("member.dao");
		Iterator<Member> iter = allMembers.iterator();
		while(iter.hasNext()) {
			Member vo = iter.next();
			if(memberDAO.findById(vo.getMid())!=null) {	//说明数据库中已经有相同mid数据存在
				iter.remove();	//只能够利用Iterator完成删除操作
			}
		}
		return memberDAO.doCreateBatch(allMembers); //执行数据批量增加
	}
	@Override
	public List<Member> listByDelete(int del) throws Exception {
		IMemberDAO memberDAO = Factory.getDAOInstance("member.dao");
		return memberDAO.findAllByDelete(del);
	}
	@Override
	public Member editPre(String id) throws Exception {
		IMemberDAO memberDAO = Factory.getDAOInstance("member.dao");
		return memberDAO.findByIdAndDelete(id, 0);	//只返回未删除的用户
	}
	@Override
	public boolean edit(Member vo) throws Exception {
		IMemberDAO memberDAO = Factory.getDAOInstance("member.dao");
		vo.setDel(0);
		return memberDAO.doUpdateByDelete(vo, 0);
	}
	@Override
	public boolean delete(Set<String> ids) throws Exception {
		if(ids==null||ids.size()==0) {	//现在没有数据可以删除
			return false;
		}
		IMemberDAO memberDAO = Factory.getDAOInstance("member.dao");
		return memberDAO.doUpdateDelete(ids);
	}
}
