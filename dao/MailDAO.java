 package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.vo.MailVO;
import kr.ac.kopo.vo.MemberVO;

public class MailDAO {

	private MemberVO member;
	private List<MailVO> list;
	
	public MailDAO() {
		list = new ArrayList<>();
	}
	
	public boolean checkRecipient(String recipient) {
		boolean check = false;
		StringBuilder sql = new StringBuilder();
		sql.append("select id from t_member where id = ? ");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setString(1, recipient);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next() == false) check = false;
				else check = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		return check;
	}
	
	public void sendMail(MailVO mail) {
		StringBuilder sql = new StringBuilder();
		int no = 0;
		sql.append("insert into t_mail(no, sender, recipient, title, contents) ");
		sql.append(" values(seq_t_mail_no.nextval, ?, ?, ?, ?) ");
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, mail.getSender());
			pstmt.setString(2, mail.getRecipient());
			pstmt.setString(3, mail.getTitle());
			pstmt.setString(4, mail.getContents());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		sql.delete(0, sql.length());
		sql.append("select no from t_mail where sender = ? and recipient = ? and title = ? and contents = ? ");
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, mail.getSender());
			pstmt.setString(2, mail.getRecipient());
			pstmt.setString(3, mail.getTitle());
			pstmt.setString(4, mail.getContents());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) no = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sql.delete(0, sql.length());
		sql.append("insert into t_send_mail (no) values (?) ");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setInt(1, no);
				pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		sql.delete(0, sql.length());
		sql.append("insert into t_recive_mail (no) values (?) ");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setInt(1, no);
				pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateRead(MailVO mail) {
		StringBuilder sql = new StringBuilder();
		sql.append("update t_mail set readyn = 1 where no = ? ");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				if(mail == null) return;
				pstmt.setInt(1, mail.getNo());
				pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateRead(MemberVO member) {
		StringBuilder sql = new StringBuilder();
		sql.append("update t_mail set readyn = 1 where recipient = ? ");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				if(member == null) return;
				pstmt.setString(1, member.getId());
				pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MailVO> selectAllMail(MemberVO member) {
		List<MailVO> list = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM t_mail where recipient = ? ");
		sql.append(" and NO in (select NO from t_RECIVE_MAIL) ");
		sql.append(" and no not in (select Mno from t_garbage) ");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setString(1, member.getId());
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					int no = rs.getInt(1);
					String sender = rs.getString(2);
					String recipient = rs.getString(3);
					String title = rs.getString(4);
					String contents = rs.getString(5);
					String sendDate = rs.getString(6);
					int readYN = rs.getInt(7);
					
					MailVO mail = new MailVO(no, sender, recipient, title,
							contents, sendDate, readYN);
					list.add(mail);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return list;
	}
	
	public MailVO selectOneMail(MemberVO member, String search) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from t_mail m1 join t_member m2 ");
		sql.append("on m2.id = m1.recipient where m1.recipient = ? ");
		sql.append(" and (m1.sender like '%' || ? || '%' ");
		sql.append(" or m1.title like '%' || ? || '%' ");
		sql.append(" or m1.contents like '%' || ? || '%') ");
		MailVO mail = null;
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setString(1, member.getId());
				pstmt.setString(2, search);
				pstmt.setString(3, search);
				pstmt.setString(4, search);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					int no = rs.getInt("no");
					String sender = rs.getString("sender");
					String recipient = rs.getString("recipient");
					String title = rs.getString("title");
					String contents = rs.getString("contents");
					String sendDate = rs.getString("send_date");
					int readYN = rs.getInt(7);
					
					mail = new MailVO(no, sender, recipient, title,
							contents, sendDate, readYN);
					list.add(mail);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return mail;
	}
	
	public List<MailVO> selectSentMail(MemberVO member) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from t_mail where sender = ? ");
		sql.append("and no in (select no from t_send_mail) ");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setString(1, member.getId());
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					int no = rs.getInt(1);
					String sender = rs.getString("sender");
					String recipient = rs.getString("recipient");
					String title = rs.getString(4);
					String contents = rs.getString(5);
					String sendDate = rs.getString(6);
					int readYN = rs.getInt(7);
					
					MailVO mail = new MailVO(no, sender, recipient, title,
							contents, sendDate, readYN);
					list.add(mail);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return list;
	}
	
	public void clearSendMail(String sender) {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from t_send_mail m1 where ");
		sql.append("no IN (select no from t_mail m2 where ");
		sql.append("sender = ? and m2.no = m1.no) ");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setString(1, sender);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public List<MailVO> selectGarbageMail(MemberVO member) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM t_mail m1 where m1.recipient = ? ");
		sql.append(" and m1.no in (select no from t_garbage) ");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setString(1, member.getId());
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					int no = rs.getInt(1);
					String sender = rs.getString(2);
					String recipient = rs.getString(3);
					String title = rs.getString(4);
					String contents = rs.getString(5);
					String sendDate = rs.getString(6);
					int readYN = rs.getInt(7);
					
					MailVO mail = new MailVO(no, sender, recipient, title,
							contents, sendDate, readYN);
					list.add(mail);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return list;
	}
	
	public void backRecivedMailBox(int no, String recipient) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from t_garbage where no = ? ");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setInt(1, no);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void clearGarbage(MemberVO member) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM t_mail WHERE recipient = ? ");
		sql.append("AND NO IN (SELECT NO FROM t_garbage) ");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setString(1, member.getId());
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void deleteMail(int no, String recipient) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from t_send_mail where no = ?");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setInt(1, no);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		sql.delete(0, sql.length());
		sql.append("delete from t_recive_mail where no = ?");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setInt(1, no);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		sql.delete(0, sql.length());
		sql.append("delete from t_mail where recipient = ? ");
		sql.append("and no = ? ");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setString(1, recipient);
				pstmt.setInt(2, no);
				pstmt.executeUpdate();
			} catch (Exception e) {
			}
	}
	
	public void moveGarbage(int no) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into t_garbage (no) values (?) ");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setInt(1, no);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
