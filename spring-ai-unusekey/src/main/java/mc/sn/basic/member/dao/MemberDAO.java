package mc.sn.basic.member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import mc.sn.basic.member.vo.MemberVO;


public interface MemberDAO {
	 public List selectAllMemberList() throws DataAccessException;
	 public int insertMember(MemberVO memberVO) throws DataAccessException ;
	 public int updateMember(MemberVO memberVO) throws DataAccessException ;
	 public int deleteMember(String id) throws DataAccessException;
	 public MemberVO searchMember(String id) throws DataAccessException;

}
