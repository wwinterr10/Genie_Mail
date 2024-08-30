package kr.ac.kopo.vo;

public class MemberVO {
	
	private int no; // 회원 코드
	private String id;
	private String pw;
	private String name;
	private String birth;
	private String hpNum;
	private String regDate;
	
	public MemberVO() {
		super();
	}
	
	public MemberVO(int no, String id, String pw, String name, String birth, String hpNum, String regDate) {
		super();
		this.no = no;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.birth = birth;
		this.hpNum = hpNum;
		this.regDate = regDate;
	}


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getBirth() {
		return birth;
	}


	public void setBirth(String birth) {
		this.birth = birth;
	}


	public String getHpNum() {
		return hpNum;
	}


	public void setHpNum(String hpNum) {
		this.hpNum = hpNum;
	}


	public String getRegDate() {
		return regDate;
	}


	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}


	@Override
	public String toString() {
		return "MemberVO [no=" + no + ", id=" + id + ", pw=" + pw + ", name=" + name + ", birth=" + birth + ", hpNum="
				+ hpNum + ", regDate=" + regDate + "]";
	}
	
	
	

}
