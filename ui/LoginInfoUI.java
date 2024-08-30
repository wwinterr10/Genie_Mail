package kr.ac.kopo.ui;

import kr.ac.kopo.vo.MemberVO;

public class LoginInfoUI extends BaseUI {

	@Override
	public void execute() throws Exception {
		System.out.println("-------------------------------< 로그인 >-------------------------------");
		int cnt = 0;
		IMailUI ui = null;
		while(true) {
			String id = scanStr("아 이 디 : ");
			String pw = scanStr("비밀번호 : ");
			
			// 입력받은 id와 일치하는 데이터를 찾은 뒤 일치하면 비밀번호 일치여부 확인
			// id가 없거나 비밀번호가 다르면 오류 메시지 출력, 5회 카운트 초과 시 본인인증 화면으로 이동
			boolean check = memberService.login(id, pw);
			if(check == true) {
				MemberVO member = memberService.memberInfo(id, pw);
				ui = new MailMenuUI(member);
				ui.execute();
				break;
			}
			else {
				cnt++;
				System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
			}
			if(cnt == 5) {
				System.out.println("오류 횟수를 초과하여 ID/PW 찾기로 이동합니다.");
				ui = new FindInfoUI();
				ui.execute();
				cnt = 0;
			}
		}
	}
}
