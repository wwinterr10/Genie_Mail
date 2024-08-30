package kr.ac.kopo.ui;

import kr.ac.kopo.vo.MemberVO;

public class JoinMemberUI extends BaseUI {

	@Override
	public void execute() throws Exception {
		System.out.println("--------------------------< 회원 가입 페이지 >--------------------------");
		
		// 회원정보를 입력받고 저장할 member객체 생성
		MemberVO member= new MemberVO();
		
		// id 중복조회
		while(true) {
			String id = scanStr("아 이 디 : ");
			boolean check = memberService.idCheck(id);
			if(check == false) {
				System.out.println("사용 가능한 ID입니다.");
				member.setId(id);
				break;
			}
			else {
				System.out.println("사용할 수 없는 ID입니다. 다시 입력해주세요.");
			}
		}
		
		// 비밀번호 일치여부 확인
		String pw = null;
		String pw2 = null;
		while(true) { 
			pw = scanStr("비밀번호  입력 : ");
			pw2 = scanStr("비밀번호  확인 : ");
			if(pw.equals(pw2)) break;
			else System.out.println("비밀번호가 일치하지 않습니다.");
		}
		
		String name = scanStr ("이          름 : ");
		String birth = scanStr("생년월일 6자리 : ");
		String hpNum = scanStr("휴대폰 (-제외) : ");
		
		member.setPw(pw);
		member.setName(name);
		member.setBirth(birth);
		member.setHpNum(hpNum);
		memberService.joinMember(member);
		System.out.println("회원가입에 성공하였습니다");
		// 가입한 정보로 자동 로그인
	}
	

}
