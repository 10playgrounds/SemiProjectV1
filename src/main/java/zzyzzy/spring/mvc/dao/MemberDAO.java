package zzyzzy.spring.mvc.dao;

import java.util.List;

import zzyzzy.spring.mvc.vo.MemberVO;
import zzyzzy.spring.mvc.vo.Zipcode;

public interface MemberDAO {

	int insertMember(MemberVO mvo);

	MemberVO selectOneMember(String uid);

	int selectOneMember(MemberVO m);

	int selectCountUserid(String uid);

	List<Zipcode> selectZipcode(String string);

}
