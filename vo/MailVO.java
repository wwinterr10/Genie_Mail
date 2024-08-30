package kr.ac.kopo.vo;

public class MailVO {
	
	private int no;
	private String sender;
	private String recipient;
	private String title;
	private String contents;
	private String sendDate;
	private int readYN;
	
	

	public MailVO(int no, String sender, String recipient, String title, String contents, String sendDate,
			int readYN) {
		super();
		this.no = no;
		this.sender = sender;
		this.recipient = recipient;
		this.title = title;
		this.contents = contents;
		this.sendDate = sendDate;
		this.readYN = readYN;
	}

	public MailVO() {
		// TODO Auto-generated constructor stub
	}

	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public int getReadYN() {
		return readYN;
	}
	public void setReadYN(int readYN) {
		this.readYN = readYN;
	}
	@Override
	public String toString() {
	//	return "MailVO [sender=" + sender + ", recipient=" + recipient + ", title=" + title + ", contents=" + contents
	//			+ ", sendDate=" + sendDate + ", readYN=" + readYN + "]";
		String read = null;
		if(readYN == 0) read = "NO";
		else read = "YES";
		return ""+ recipient + "\t" + title + "\t" + contents + "\t"
				+ sendDate + "\t" + read;
	}
	
	

}
