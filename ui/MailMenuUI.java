package kr.ac.kopo.ui;

import kr.ac.kopo.vo.MemberVO;

public class MailMenuUI extends BaseUI {
	
	private MemberVO member;
	
	public MailMenuUI() {
		
	}
	
	public MailMenuUI(MemberVO member) {
		this.member = member;
	}

	private int choiceMenu() {
		System.out.println("--------------------------< 메일 확인 서비스 >--------------------------");
		System.out.println("1.메일쓰기  2.받은메일  3.보낸메일  4.휴지통  5.회원정보수정  6.로그아웃");
		int type = scanInt("항목을 선택하세요 : ");
		return type;
	}

	@Override
	public void execute() throws Exception {
		while(true) {
			IMailUI ui = null;
			int type = choiceMenu();
			switch(type) {
			case 1 : // 메일 쓰기
				ui = new SendMailUI(member);
				break;
			case 2 : // 받은 메일함
				ui = new RecivedMailBoxUI(member);
				break;
			case 3 : // 보낸 메일함
				ui = new SentMailBoxUI(member);
				break;
			case 4 : // 휴지통
				ui = new GarbageUI(member);
				break;
			case 5 : // 회원정보 수정
				ui = new UpdateMemberUI(member);
				break;
			case 6 : // 로그아웃
				ui = new LogoutUI();
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
