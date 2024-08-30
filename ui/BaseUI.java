package kr.ac.kopo.ui;

import java.util.Scanner;

import kr.ac.kopo.service.MailService;
import kr.ac.kopo.service.MailServiceFactory;
import kr.ac.kopo.service.MemberService;
import kr.ac.kopo.service.MemberServiceFactory;

public abstract class BaseUI implements IMailUI {
	
	private Scanner sc;
	protected MemberService memberService;
	protected MailService mailService;
	
	@Override
	public void execute() throws Exception {
	}
	
	public BaseUI() {
		sc = new Scanner(System.in);
		memberService = MemberServiceFactory.getInstance();
		mailService = MailServiceFactory.getInstance();
	}

	protected String scanStr(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}
	
	protected int scanInt(String msg) {
		return Integer.parseInt(scanStr(msg));
	}
	
}
