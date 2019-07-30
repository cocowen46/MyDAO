package cn.mldn.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.mldn.dao.IMemberDAO;
import cn.mldn.util.dbc.DatabaseConnection;
import cn.mldn.vo.Member;

public class MemberDAOImpl implements IMemberDAO {
	private PreparedStatement pstmt;
	@Override
	public List<Member> findAllSplitByDelete(Integer currentPage, Integer lineSize, Integer del) throws SQLException {
		List<Member> all = new ArrayList<Member>();
		String sql = " SELECT * FROM ( "
				+ " SELECT mid,name,birthday,salary,note,del,ROWNUM rn "
				+ " FROM member WHERE del=? AND ROWNUM<=?) temp WHERE temp.rn>? ";
		this.pstmt=DatabaseConnection.getConnection().prepareStatement(sql);
		this.pstmt.setInt(1, del);
		this.pstmt.setInt(2, currentPage*lineSize);
		this.pstmt.setInt(3, (currentPage-1)*lineSize);
		ResultSet rs = this.pstmt.executeQuery();
		while(rs.next()) {
			Member vo = new Member();
			vo.setMid(rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setBirthday(rs.getDate(3));
			vo.setSalary(rs.getDouble(4));
			vo.setNote(rs.getString(5));
			vo.setDel(rs.getInt(6));
			all.add(vo);
		}
		return all; 
	}
	@Override
	public List<Member> findAllSplitByDelete(Integer currentPage, Integer lineSize, String column, String keyWord,
			Integer del) throws SQLException {
		List<Member> all = new ArrayList<Member>();
		String sql = " SELECT * FROM ( "
				+ " SELECT mid,name,birthday,salary,note,del,ROWNUM rn "
				+ " FROM member WHERE del=? AND "+column+" LIKE ? AND ROWNUM<=?) temp WHERE temp.rn>? ";
		this.pstmt=DatabaseConnection.getConnection().prepareStatement(sql);
		this.pstmt.setInt(1, del);
		this.pstmt.setString(2, "%"+keyWord+"%");
		this.pstmt.setInt(3, currentPage*lineSize);
		this.pstmt.setInt(4, (currentPage-1)*lineSize);
		ResultSet rs = this.pstmt.executeQuery();
		while(rs.next()) {
			Member vo = new Member();
			vo.setMid(rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setBirthday(rs.getDate(3));
			vo.setSalary(rs.getDouble(4));
			vo.setNote(rs.getString(5));
			vo.setDel(rs.getInt(6));
			all.add(vo);
		}
		return all; 
	}
	@Override
	public Integer getAllCountByDelete(Integer del) throws SQLException {
		String sql = " SELECT COUNT(*) FROM member WHERE del=? ";
		this.pstmt=DatabaseConnection.getConnection().prepareStatement(sql);
		this.pstmt.setInt(1, del);
		ResultSet rs = this.pstmt.executeQuery();
		if(rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}
	@Override
	public Integer getAllCountByDelete(String column, String keyWord, Integer del) throws SQLException {
		String sql = " SELECT COUNT(*) FROM member WHERE del=? AND "+column+" LIKE ? ";
		this.pstmt=DatabaseConnection.getConnection().prepareStatement(sql);
		this.pstmt.setInt(1, del);
		this.pstmt.setString(2, "%"+keyWord+"%");
		ResultSet rs = this.pstmt.executeQuery();
		if(rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}
	@Override
	public boolean doCreate(Member vo) throws SQLException {
		String sql = "INSERT INTO member(mid,name,birthday,salary,note,del) VALUES(?,?,?,?,?,?)";
		this.pstmt = DatabaseConnection.getConnection().prepareStatement(sql);
		this.pstmt.setString(1, vo.getMid());
		this.pstmt.setString(2, vo.getName());
		this.pstmt.setDate(3, new java.sql.Date(vo.getBirthday().getTime()));
		this.pstmt.setDouble(4, vo.getSalary());
		this.pstmt.setString(5, vo.getNote());
		this.pstmt.setInt(6, vo.getDel());
		return this.pstmt.executeUpdate()>0;
	}

	@Override
	public boolean doUpdate(Member vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(String id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Member findById(String id) throws SQLException {
		Member vo = null;
		String sql = "SELECT mid,name,birthday,salary,note,del FROM member WHERE mid=?";
		this.pstmt=DatabaseConnection.getConnection().prepareStatement(sql);
		this.pstmt.setString(1, id);
		ResultSet rs = this.pstmt.executeQuery();
		if(rs.next()) {
			vo = new Member();
			vo.setMid(rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setBirthday(rs.getDate(3));
			vo.setSalary(rs.getDouble(4));
			vo.setNote(rs.getString(5));
			vo.setDel(rs.getInt(6));
		}
		return vo;
	}
	
	@Override
	public List<Member> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Member> findAllByDelete(Integer del) throws SQLException {
		List<Member> all = new ArrayList<Member>();
		String sql = "SELECT mid,name,birthday,salary,note,del FROM member WHERE del=?";
		this.pstmt=DatabaseConnection.getConnection().prepareStatement(sql);
		this.pstmt.setInt(1, del);
		ResultSet rs = this.pstmt.executeQuery();
		while(rs.next()) {
			Member vo = new Member();
			vo.setMid(rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setBirthday(rs.getDate(3));
			vo.setSalary(rs.getDouble(4));
			vo.setNote(rs.getString(5));
			vo.setDel(rs.getInt(6));
			all.add(vo);
		}
		return all; 
	}
	@Override
	public List<Member> findAll(Integer currentPage, Integer lineSize) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllCount() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllCountSplit(String column, String keyWord) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member findByIdAndDelete(String id, Integer del) throws SQLException {
		Member vo = null;
		String sql = "SELECT mid,name,birthday,salary,note,del FROM member WHERE mid=? AND del=?";
		this.pstmt=DatabaseConnection.getConnection().prepareStatement(sql);
		this.pstmt.setString(1, id);
		this.pstmt.setInt(2, del);
		ResultSet rs = this.pstmt.executeQuery();
		if(rs.next()) {
			vo = new Member();
			vo.setMid(rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setBirthday(rs.getDate(3));
			vo.setSalary(rs.getDouble(4));
			vo.setNote(rs.getString(5));
			vo.setDel(rs.getInt(6));
		}
		return vo;
	}

	@Override
	public boolean doUpdateByDelete(Member vo,Integer del) throws SQLException {
		String sql = "UPDATE member SET name=?,birthday=?,salary=?,note=? WHERE mid=? AND del=?";
		this.pstmt=DatabaseConnection.getConnection().prepareStatement(sql);
		this.pstmt.setString(1, vo.getName());
		this.pstmt.setDate(2, new java.sql.Date(vo.getBirthday().getTime()));
		this.pstmt.setDouble(3, vo.getSalary());
		this.pstmt.setString(4,vo.getNote());
		this.pstmt.setString(5, vo.getMid());
		this.pstmt.setInt(6, del);
		return this.pstmt.executeUpdate()>0;
	}

	@Override
	public boolean doUpdateDelete(Set<String> ids) throws SQLException {
		StringBuffer buf = new StringBuffer();	//进行SQL语句的拼凑处理
		buf.append("UPDATE member SET del=1 WHERE mid IN( ");
		Iterator iter = ids.iterator();
		while(iter.hasNext()) {	//拼凑SQL语句
			buf.append("'").append(iter.next()).append("'").append(","); 
		}
		buf.delete(buf.length()-1, buf.length()).append(")");
		this.pstmt=DatabaseConnection.getConnection().prepareStatement(buf.toString());
		return this.pstmt.executeUpdate()>0;
	}

	@Override
	public boolean doCreateBatch(Set<Member> allMembers) throws SQLException {
		String sql = "INSERT INTO member(mid,name,birthday,salary,note,del) VALUES(?,?,?,?,?,?)";
		this.pstmt=DatabaseConnection.getConnection().prepareStatement(sql);
		Iterator<Member> iter = allMembers.iterator();
		while(iter.hasNext()) {
			Member vo = iter.next();
			this.pstmt.setString(1, vo.getMid());
			this.pstmt.setString(2, vo.getName());
			this.pstmt.setDate(3, new java.sql.Date(vo.getBirthday().getTime()));
			this.pstmt.setDouble(4, vo.getSalary());
			this.pstmt.setString(5, vo.getNote());
			this.pstmt.setInt(6, vo.getDel());
			this.pstmt.addBatch();	//添加至批处理
		}
		int [] result = this.pstmt.executeBatch();
		for(int x=0;x<result.length;x++) {
			if(result[x]==0) {
				return false;
			}
		}
		return true;
	}

}
