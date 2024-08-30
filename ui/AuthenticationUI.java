package kr.ac.kopo.ui;

import kr.ac.kopo.vo.MemberVO;

public class AuthenticationUI extends BaseUI {

	@Override
	public void execute() throws Exception {
		
	}
	
	public void findID() throws Exception {
		System.out.println("----------------------------< 아이디 찾기 >-----------------------------");
		String name = scanStr ("이          름 : ");
		String birth = scanStr("생년월일 6자리 : ");
		String hpNum = scanStr("휴대폰 (-제외) : ");
		String id = memberService.findID(name, birth, hpNum);
		if(id == null) System.out.println("일치하는 회원정보가 존재하지 않습니다.");
		else System.out.println("가입하신 아이디는 " + id + "입니다.");
	}
	
	public void findPW() throws Exception {
		System.out.println("---------------------------< 비밀번호 변경 >----------------------------");
		String id = scanStr   ("아    이    디 : ");
		String name = scanStr ("이          름 : ");
		String birth = scanStr("생년월일 6자리 : ");
		String hpNum = scanStr("휴대폰 (-제외) : ");
		boolean check = memberService.findPW(id, name, birth, hpNum);
		String pw = null;
		String pw2 = null;
		if(check == true) {
			while(true) { 
				pw = scanStr ("변경할 비밀번호 : ");
				pw2 = scanStr("비밀번호 재입력 : ");
				if(pw.equals(pw2)) {
					memberService.updatePW(id, pw);
					System.out.println("비밀번호 변경이 완료되었습니다.");
					System.out.println("입력하신 정보로 자동 로그인합니다.");
					MemberVO member = memberService.memberInfo(id, pw);
					IMailUI ui = new MailMenuUI(member);
					ui.execute();
					break;
					
				}
				else System.out.println("비밀번호가 일치하지 않습니다.");
			}
		} else System.out.println("회원정보를 다시 확인해주세요.");
	}
	
	public void changePW(MemberVO member) {
		System.out.println("---------------------------< 비밀번호 변경 >----------------------------");
		String nowPW = scanStr("현재 비밀번호 : ");
		String pw = null;
		String pw2 = null;
		boolean check = memberService.checkPW(member, nowPW);
		if (check == true) {
			while (true) {
				pw = scanStr ("변경할 비밀번호 : ");
				pw2 = scanStr("비밀번호 재입력 : ");
				if (pw.equals(pw2)) {
					memberService.updatePW(member.getId(), pw);
					System.out.println("비밀번호 변경이 완료되었습니다.");
					break;
				} else System.out.println("비밀번호가 일치하지 않습니다. 다시 입력하세요.");
			}
		} else System.out.println("비밀번호가 일치하지 않습니다.");
	}
	
	public void changeHpNum(MemberVO member) {
		System.out.println("--------------------------< 휴대폰번호 변경 >---------------------------");
		String nowPW = scanStr("현재 비밀번호 : ");
		boolean check = memberService.checkPW(member, nowPW);
		String hpNum = null;
		if(check == true) {
			hpNum = scanStr("변경할 휴대폰번호 : ");
			memberService.updateHpNum(member, hpNum);
			System.out.println("휴대폰번호 변경이 완료되었습니다.");
		} else System.out.println("비밀번호가 일치하지 않습니다.");
	}
}
