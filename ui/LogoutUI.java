package kr.ac.kopo.ui;

public class LogoutUI extends BaseUI {
	
	private int choiceMenu() {
		int type = 0;
		try {
			type = scanInt("1:메인메뉴로 이동    2:종료");
		} catch (NumberFormatException e){
			System.out.println("잘못 선택하셨습니다.");
			choiceMenu();
		}
		return type;
	}

	@Override
	public void execute() throws Exception {
		System.out.println("------------------------------< 로그아웃 >------------------------------");
		System.out.println("로그아웃하고 종료하시겠습니까?");
		int type = choiceMenu();
		LoginMenuUI ui = new LoginMenuUI();
		switch(type) {
		case 1 :
			ui.execute();
		case 2 :
			System.exit(0);
		}
		if(type != 1 || type != 2) {
			System.out.println("잘못 선택하셨습니다.");
		}
	}
}
