package kr.ac.kopo.ui;

import kr.ac.kopo.vo.MemberVO;

public class RecivedMailBoxUI extends BaseUI {

	private MemberVO member;
	
	public RecivedMailBoxUI(MemberVO member) {
		this.member = member;
	}
	
	private int choiceMenu() {
		System.out.println("----------------------------< 받은 메일함 >-----------------------------");
		System.out.println("1.메일전체조회                2.메일검색조회                3.이전메뉴로");
		int type = scanInt("항목을 선택하세요 : ");
		//System.out.println("----------------------------------------------------------------");
		return type;
	}

	@Override
	public void execute() throws Exception {
		while(true) {
			IMailUI ui = null;
			int type = choiceMenu();
			switch(type) {
			case 1 : // 메일전체조회
				ui = new SelectAllMailUI(member);
				break;
			case 2 : // 메일선택조회
				ui = new SelectOneMailUI(member);
				break;
			case 3 : // 이전메뉴
				ui = new MailMenuUI(member);
				break;
			}
			if(ui != null) {
				ui.execute();
			} else {
				System.out.println("잘못 선택하셨습니다.");
			}
		}
	}
}
