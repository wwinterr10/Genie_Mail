package kr.ac.kopo.ui;

public class LoginMenuUI extends BaseUI {
	
	
	private int choiceMenu() {
		System.out.println("-------------------------< 메일 서비스 로그인 >-------------------------");
		System.out.println("1.로그인       2.ID/PW찾기       3.회원가입       4.로그아웃      0.종료");
		int type = scanInt("항목을 선택하세요 : ");
		return type;
	}

	public void execute() throws Exception {
		while(true) {
			IMailUI ui = null;
			int type = choiceMenu();
			switch(type) {
			case 1 : // 로그인 화면
				ui = new LoginInfoUI();
				break;
			case 2 : // ID/PW 찾기
				ui = new FindInfoUI();
				break;
			case 3 : // 회원가입
				ui = new JoinMemberUI();
				break;
			case 4 : // 로그아웃
				ui = new LogoutUI();
				break;
			case 0 : // 종료
				ui = new ExitUI();
			}
			
			if(ui != null) {
				ui.execute();	
			} else {
				System.out.println("잘못 선택하셨습니다.");
			}
		}
	}
}
