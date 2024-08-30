package kr.ac.kopo.ui;

import kr.ac.kopo.vo.MailVO;
import kr.ac.kopo.vo.MemberVO;

public class SendMailUI extends BaseUI {
	
	private MemberVO member;
	
	public SendMailUI(MemberVO member) {
		this.member = member;
	}
	
	public void forward(String title, String contents) {
		System.out.println("-------------------------------< 전달하기 >-----------------------------");
		String sender = member.getId();
		String recipient = scanStr("받는사람 : ");
		String ntitle = "";
		title = "[전달]" + title;
		ntitle = scanStr("제    목 : " + title + " : ");
		if(ntitle.length() == 0) {
			ntitle = title;
		} else {
			ntitle = title + " : " + ntitle;
		}
		String ncontents = "";
		contents = "[전달]" + contents;
		ncontents = scanStr("내    용 : " + contents + " : ");
		if(ncontents.length() == 0) {
			ncontents = contents;
		} else {
			ncontents = contents + " : " + ncontents;
		}
		MailVO mail = new MailVO();
		mail.setSender(sender);
		mail.setRecipient(recipient);
		mail.setTitle(ntitle);
		mail.setContents(ncontents);
		mailService.sendMail(mail);
		System.out.println("메일 전송이 완료되었습니다.");
	}
	
	public void reply(String reply) throws Exception {
		System.out.println("-----------------------------< 답장 보내기 >----------------------------");
		String sender = member.getId();
		String recipient = reply;
		String title = scanStr("제    목 : ");
		String contents = scanStr("내    용 : ");
		MailVO mail = new MailVO();
		mail.setSender(sender);
		mail.setRecipient(recipient);
		mail.setTitle(title);
		mail.setContents(contents);
		mailService.sendMail(mail);
		System.out.println("메일 전송이 완료되었습니다.");
	}

	@Override
	public void execute() throws Exception {
		System.out.println("-----------------------------< 메일 보내기 >----------------------------");
		String sender = member.getId();
		String recipient = scanStr("받는사람 : ");
		String title = scanStr("제    목 : ");
		String contents = scanStr("내    용 : ");
		// 받는사람이 db에 존재하는지 체크
		// 존재하지 않으면 전송실패, 있으면 전송 성공 메시지 출력
		
		boolean check = mailService.checkRecipient(recipient);
		if(check == false) System.out.println("메일 전송에 실패하였습니다.");
		else {
			MailVO mail = new MailVO();
			mail.setSender(sender);
			mail.setRecipient(recipient);
			mail.setTitle(title);
			mail.setContents(contents);
			mailService.sendMail(mail);
			System.out.println("메일 전송이 완료되었습니다.");
		}
		
	}
	
	

}
