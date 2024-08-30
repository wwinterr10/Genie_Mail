package kr.ac.kopo.service;

public class MailServiceFactory {

	private static MailService mailService;
	
	public static MailService getInstance() {
		if(mailService == null) {
			mailService = new MailService();
		}
		return mailService;	
	}
}
