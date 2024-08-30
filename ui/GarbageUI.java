package kr.ac.kopo.ui;

import java.util.List;

import kr.ac.kopo.vo.MailVO;
import kr.ac.kopo.vo.MemberVO;

public class GarbageUI extends BaseUI {

	private MemberVO member;
	private List<MailVO> list;
	
	public GarbageUI(MemberVO member) {
		this.member = member;
	}
	
	public int choiceMenu() {
		System.out.println("1.계속조회      2.전체삭제      3.선택삭제      4.복원      5.이전메뉴로");
		int type = scanInt("항목을 선택하세요 : ");
		return type;
	}
	@Override
	public void execute() throws Exception {
		System.out.println("------------------------------< 휴지통 >--------------------------------");
		list = mailService.selectGarbageMail(member);
		if (list.isEmpty()) {
			System.out.println("조회된 메일이 없습니다.");
		} else {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) == null) {
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
					if (contents.length() >= 32) {
						System.out.println("내    용 : " + contents.substring(0, 32) + "... ");
					} else
						System.out.println("내    용 : " + String.format("%-50s", contents));
					System.out.print("받은날짜 : " + String.format("%-50s", sendDate));

					System.out.println("\n------------------------------------------------------------------------");

					int type = choiceMenu();
					switch (type) {
					case 1:
						if (i == list.size() - 1)
							System.out.println("더 이상 메일이 없습니다.");
						continue;
					case 2: // 전체삭제
						mailService.clearGarbage(member);
						System.out.println("휴지통 비우기가 완료되었습니다.");
						break;
					case 3: // 조회 후 삭제
						String search = scanStr("삭제할 메일의 검색어를 입력하세요 : ");
						list = mailService.selectOneMail(member, search);
						mailService.deleteMail(list.get(i).getNo(), list.get(i).getRecipient());
						System.out.println("조회한 메일이 삭제되었습니다.");
						break;
					case 4:
						mailService.backRecivedMailBox(list.get(i).getNo(), list.get(i).getRecipient());
						System.out.println("받은 메일함으로 복원되었습니다.");
						break;
					case 5: // 이전메뉴
						MailMenuUI ui = new MailMenuUI(member);
						ui.execute();
						break;
					}
				}
			}
		}
	}
}
