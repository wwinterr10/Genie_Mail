package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.vo.MemberVO;

public class MemberDAO {

	private List<MemberVO> memberList;
	
	public MemberDAO() {
		memberList = new ArrayList<>();
	}
	
	public void join(MemberVO member) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO t_member(NO, id, pw, name, birth, hp_num) ");
		sql.append(" values(seq_t_member_no.nextval, ?, ?, ?, ?, ?) ");
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getBirth());
			pstmt.setString(5, member.getHpNum());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// id 중복체크
	public boolean idCheck(String id) {
		boolean check = false;
		//db에서 id를 조회할 sql문
		StringBuilder sql = new StringBuilder();
		sql.append("select id from t_member where id = ? ");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setString(1,id);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next() == false) check = false;
				else check = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		return check;
	}
	
	// 로그인 시 id와 pw 일치여부 확인
	public boolean login(String id, String pw) {
		boolean check = false;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select id, pw from t_member where id = ? ");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setString(1,id);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					
					if(rs.getString(1).equals(id) && rs.getString(2).equals(pw)) {
						check = true;
						System.out.println("로그인에 성공하였습니다.");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return check;
	}
	
	
	public boolean authentication(String name, String birth, String hpNum) {
		boolean check = false;
		return check;
	}
	
	public String findID(String name, String birth, String hpNum) {
		StringBuilder sql = new StringBuilder();
		sql.append("select id from t_member where name = ? "
				+ " and birth = ? and hp_num = ? ");
		String id = null;
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setString(1, name);
				pstmt.setString(2, birth);
				pstmt.setString(3, hpNum);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					id = rs.getString(1);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public boolean findPW(String id, String name, String birth, String hpNum) {
		boolean check = false;
		StringBuilder sql = new StringBuilder();
		sql.append("select id, name from t_member where id = ? and name = ? "
				+ " and birth = ? and hp_num = ? ");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setString(1, id);
				pstmt.setString(2, name);
				pstmt.setString(3, birth);
				pstmt.setString(4, hpNum);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next() == true) check = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}
	
	public void updatePW(String id, String pw) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE t_member SET pw = ? WHERE id = ? ");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setString(1, pw);
				pstmt.setString(2, id);
				pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateHpNum(MemberVO member, String hpNum) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE t_member SET hp_Num = ? WHERE id = ? ");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setString(1, hpNum);
				pstmt.setString(2, member.getId());
				pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkPW(MemberVO member, String pw) {
		boolean check = false;
		StringBuilder sql = new StringBuilder();
		sql.append("select id, pw from t_member where id = ? and pw = ? ");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setString(1, member.getId());
				pstmt.setString(2, pw);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next() == true) check = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}
	
	public MemberVO memberInfo(String id, String pw) {
		MemberVO member = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select no, id, pw, name, birth, hp_Num, reg_Date ");
		sql.append(" from t_member where id = ? and pw = ? ");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					int no = rs.getInt(1);
					String id2 = rs.getString(2);
					String pw2 = rs.getString(3);
					String name = rs.getString(4);
					String birth = rs.getString(5);
					String hpNum = rs.getString(6);
					String regDate = rs.getString(7);
					
					member = new MemberVO(no, id2, pw2, name, birth, hpNum, regDate);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}
}
