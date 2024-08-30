package kr.ac.kopo.service;

import kr.ac.kopo.dao.MemberDAO;
import kr.ac.kopo.vo.MemberVO;

public class MemberService {

	private MemberDAO dao;
	
	public MemberService() {
		dao = new MemberDAO();
	}
	
	public void joinMember(MemberVO member) {
		dao.join(member);
	}
	
	public boolean idCheck(String id) {
		boolean check = dao.idCheck(id);
		return check;
	}
	
	public boolean login(String id, String pw) {
		boolean check = dao.login(id, pw);
		return check;
	}
	
	public String findID(String name, String birth, String hpNum) {
		String id = dao.findID(name, birth, hpNum);
		return id;
	}
	
	public boolean findPW(String id, String name, String birth, String hpNum) {
		boolean check = dao.findPW(id, name, birth, hpNum);
		return check;
	}
	
	public void updatePW(String id, String pw) {
		dao.updatePW(id, pw);
	}
	
	public MemberVO memberInfo(String id, String pw) {
		MemberVO member = dao.memberInfo(id, pw);
		return member;
	}
	
	public boolean checkPW(MemberVO member, String pw) {
		boolean check = dao.checkPW(member, pw);
		return check;
	}
	
	public void updateHpNum(MemberVO member, String hpNum) {
		dao.updateHpNum(member, hpNum);
	}
}
