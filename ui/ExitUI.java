package kr.ac.kopo.ui;

public class ExitUI extends BaseUI {

	@Override
	public void execute() throws Exception {
		System.out.println("프로그램을 종료합니다");
		System.exit(0);
	}
}
