package zzyzzy.spring.mvc.dao;

import zzyzzy.spring.mvc.vo.MemberVO;

public interface MemberDAO {

	int insertMember(MemberVO mvo);

	MemberVO selectOneMember();

	int selectOneMember(MemberVO m);

}
