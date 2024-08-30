package kr.ac.kopo.service;

public class MemberServiceFactory {

	private static MemberService memberService;
	
	public static MemberService getInstance() {
		if(memberService == null) {
			memberService = new MemberService();
		}
		return memberService;
	}
}
