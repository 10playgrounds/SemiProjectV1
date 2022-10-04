package zzyzzy.spring.mvc.service;

import zzyzzy.spring.mvc.vo.MemberVO;

public interface MemberService {

	boolean newMember(MemberVO mvo);

	MemberVO readOneMember();

	boolean checkLogin(MemberVO mvo);

}
