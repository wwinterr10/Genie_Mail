package kr.ac.kopo.ui;

import kr.ac.kopo.vo.MemberVO;

public class UpdateMemberUI extends BaseUI {

	private MemberVO member;
	
	public UpdateMemberUI(MemberVO member) {
		this.member = member;
	}
	
	public int choiceMenu() {
		System.out.println("---------------------------< 회원 정보 변경 >---------------------------");
		System.out.println("1.비밀번호 변경\t2.휴대폰번호 변경");
		int type = scanInt("항목을 선택하세요 : ");
		return type;
	}

	@Override
	public void execute() throws Exception {
		int type = choiceMenu();
		AuthenticationUI ui = new AuthenticationUI();
		switch(type) {
		case 1 :
			ui.changePW(member);
			break;
		case 2 :
			ui.changeHpNum(member);
			break;
		}
	}
	
	

}
