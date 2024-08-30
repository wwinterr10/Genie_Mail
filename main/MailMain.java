package kr.ac.kopo.main;

import kr.ac.kopo.ui.LoginMenuUI;

public class MailMain {

	public static void main(String[] args) {
		LoginMenuUI ui = new LoginMenuUI();
		try {
			ui.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
