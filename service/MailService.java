package kr.ac.kopo.service;

import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.dao.MailDAO;
import kr.ac.kopo.vo.MailVO;
import kr.ac.kopo.vo.MemberVO;

public class MailService {

	private MailDAO mdao;
	private List<MailVO> list;
	
	public MailService() {
		mdao = new MailDAO();
	}
	
	public boolean checkRecipient(String recipient) {
		boolean check = mdao.checkRecipient(recipient);
		return check;
	}
	
	public void sendMail(MailVO mail) {
		mdao.sendMail(mail);
	}
	
	public List<MailVO> selectAllMail(MemberVO member) {
		List<MailVO> list = mdao.selectAllMail(member);
		if(list != null) mdao.updateRead(member);
		return list;
	}
	public List<MailVO> selectNotRead(MemberVO member) {
		List<MailVO> nlist = mdao.selectAllMail(member);
		List<MailVO> list = new ArrayList<>();
		
		for(int i = 0; i < nlist.size(); i++) {
			if(nlist.get(i).getReadYN() == 0)
				list.add(nlist.get(i));
		}
		return list;
	}
	public List<MailVO> selectOneMail(MemberVO member, String search) {
		MailVO mail = mdao.selectOneMail(member, search);
		List<MailVO> list = new ArrayList<>();
		list.add(mail);
		if(list != null) mdao.updateRead(mail);
		return list;
	}
	
	public List<MailVO> selectSentMail(MemberVO member) {
		List<MailVO> list = mdao.selectSentMail(member);
		return list;
	}
	
	public List<MailVO> selectGarbageMail(MemberVO member) {
		List<MailVO> list = mdao.selectGarbageMail(member);
		return list;
	}
	
	public void backRecivedMailBox(int no, String recipient) {
		mdao.backRecivedMailBox(no, recipient);
	}
	
	public void clearGarbage(MemberVO member) {
		mdao.clearGarbage(member);
	}
	
	public void deleteMail(int no, String recipient) {
		mdao.deleteMail(no, recipient);
	}
	
	public void clearSendMail(String sender) {
		mdao.clearSendMail(sender);
	}
	
	public String replyMail(String sender) {
		return sender;
	}
	
	public void moveGarbage(int no) {
		mdao.moveGarbage(no);
	}
}
