package kr.ac.kopo.ui;

public class FindInfoUI extends BaseUI {

	private int choiceMenu() {
		System.out.println("-----------------------------< ID/PW 찾기 >-----------------------------");
		System.out.println("1.아이디 찾기       2.비밀번호 재설정       3.회원가입      4.이전메뉴로");
		int type = scanInt("항목을 선택하세요 : ");
		return type;
	}
	
	@Override
	public void execute() throws Exception {
		int type = choiceMenu();
		AuthenticationUI ui = new AuthenticationUI();
		switch(type) {
		case 1 : 
			ui.findID();
			break;
		case 2 :
			ui.findPW();
			break;
		case 3 :
			IMailUI ui2 = new JoinMemberUI();
			ui2.execute();
			break;
		case 4 :
			ui2 = new LoginMenuUI();
			ui2.execute();
			break;
		}
		
	}

	
}
