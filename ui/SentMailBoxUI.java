package kr.ac.kopo.ui;

import java.util.List;

import kr.ac.kopo.vo.MailVO;
import kr.ac.kopo.vo.MemberVO;

public class SentMailBoxUI extends BaseUI {
	
	private List<MailVO> list;
	private MemberVO member;
	
	public SentMailBoxUI(MemberVO member) {
		this.member = member;
	}

	@Override
	public void execute() throws Exception {
		System.out.println("----------------------------< 보낸 메일함 >-----------------------------");
		list = mailService.selectSentMail(member);
		if(list.isEmpty()) {
			System.out.println("조회된 메일이 없습니다.");
		} else {
			for(int i = 0; i < list.size(); i++) {
				//System.out.println("수신자   제목       내용                 발신일                수신확인");
				String sender = list.get(i).getRecipient();
				String title = list.get(i).getTitle();
				String contents = list.get(i).getContents();
				String sendDate = list.get(i).getSendDate();
				String read = "";
				
				System.out.println("------------------------------------------------------------------------");
				System.out.println("받는사람 : " + String.format("%-50s", sender));
				System.out.println("제    목 : " + String.format("%-50s", title));
				if(contents.length() >= 32) {
					System.out.println("내    용 : " + contents.substring(0, 32) + "... ");
				} else System.out.println("내    용 : " + String.format("%-50s", contents));
				System.out.println("보낸날짜 : " + String.format("%-50s", sendDate));
				if(list.get(i).getReadYN() == 0) read = "읽지않음";
				else read = "읽음";
				System.out.print("수신확인 : " + String.format("%-50s", read));
				/*
				System.out.print(String.format("%-7s", sender));
				if(title.length() >= 5) {
					System.out.print(title.substring(0,3) + "...  ");
				} else {
					System.out.print(String.format("%-10s",title));
				}
				if(contents.length() >= 15) {
					System.out.print(contents.substring(0,11) + "...  ");
				} else {
					System.out.print(String.format("%-20s",contents));
				}
				System.out.print(String.format("%-18s",sendDate));
				if(list.get(i).getReadYN() == 0) {
					System.out.print(String.format("%8s", "읽지않음"));
				} else System.out.print(String.format("%8s", "읽음")); 
				*/
				System.out.println("\n------------------------------------------------------------------------");
				int type = choiceMenu();
				switch(type) {
				case 1:
					if(i == list.size()-1) System.out.println("더 이상 메일이 없습니다.");
					continue;
				case 2:
					MailVO mail = list.get(i);
					if(mail.getReadYN() == 0) {
						mailService.deleteMail(list.get(i).getNo(), list.get(i).getRecipient());
						System.out.println("전송 취소가 완료되었습니다.");
					}
					else System.out.println("읽은 메일은 전송을 취소할 수 없습니다.");
					break;
				case 3:
					mailService.clearSendMail(list.get(i).getSender());
					System.out.println("보낸 메일함 비우기를 완료하였습니다.");
					break; 
				case 4:
					IMailUI ui = new MailMenuUI(member);
					ui.execute();
					break;
				}
			}
		}
	}
	
	public int choiceMenu() {
		System.out.println("1.계속조회      2.메일전송취소      3.보낸메일함비우기      4.이전메뉴로");
		int type = scanInt("항목을 선택하세요 : ");
		//System.out.println("------------------------------------------------------------------------");
		return type;
	}
	
	
}
