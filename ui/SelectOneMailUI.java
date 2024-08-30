package kr.ac.kopo.ui;

import java.util.List;

import kr.ac.kopo.vo.MailVO;
import kr.ac.kopo.vo.MemberVO;

public class SelectOneMailUI extends BaseUI {
	
	private List<MailVO> list;
	private MemberVO member;
	
	public SelectOneMailUI(MemberVO member) {
		this.member = member;
	}
	
	public List<MailVO> selectOneMail(MemberVO member) {
		System.out.println("------------------------------< 메일 검색 >-----------------------------");
		String search = scanStr("검색할 내용을 입력하세요 : ");
		list = mailService.selectOneMail(member, search);
		if(list.isEmpty()) return list = null;
		return list;
	}

	@Override
	public void execute() throws Exception {
		list = selectOneMail(member);
		
		if(list.isEmpty()) {
			System.out.println("조회된 메일이 없습니다.");
		} else {
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i) == null) {
					System.out.println("조회된 메일이 없습니다.");
					break;
				} else {
				String sender = list.get(i).getSender();
				String title = list.get(i).getTitle();
				String contents = list.get(i).getContents();
				String sendDate = list.get(i).getSendDate();
				
				System.out.println("------------------------------------------------------------------------");
				System.out.println("보낸사람 : " + String.format("%-50s", sender));
				System.out.println("제    목 : " + String.format("%-50s", title));
				if(contents.length() >= 32) {
					System.out.println("내    용 : " + contents.substring(0, 32) + "... ");
				} else System.out.println("내    용 : " + String.format("%-50s ", contents));
				System.out.print("받은날짜 : " + String.format("%-50s",  sendDate));
				
				System.out.println("\n------------------------------------------------------------------------");
				int type = choiceMenu();
				switch(type) {
				case 1 : // 계속 조회
					if(i == list.size()-1) System.out.println("더 이상 메일이 없습니다.");
					continue;
				case 2 : // 답장
					SendMailUI sui = new SendMailUI(member);
					sui.reply(list.get(i).getSender());
					break;
				case 3 : // 삭제
					//MailVO mail = list.get(i);
					mailService.deleteMail(list.get(i).getNo(), list.get(i).getRecipient());
					System.out.println("메일이 삭제되었습니다.");
					break;
				case 4 : // 전달
					sui = new SendMailUI(member);
					sui.forward(list.get(i).getTitle(), list.get(i).getContents());
					break;
				case 5 : // 휴지통
					mailService.moveGarbage(list.get(i).getNo());
					System.out.println("메일이 휴지통으로 이동되었습니다.");
					break;
				case 6 : // 이전메뉴
					IMailUI ui = new MailMenuUI(member);
					ui.execute();
					break;
				
				}
				}
			}
		}
	}

	private int choiceMenu() {
		System.out.println("1.계속조회   2.답장   3.삭제   4.전달   5.휴지통으로이동    6.이전메뉴로");
		int type = scanInt("항목을 선택하세요 : ");
	//	System.out.println("------------------------------------------------------------------------");
		return type;
	}
}
