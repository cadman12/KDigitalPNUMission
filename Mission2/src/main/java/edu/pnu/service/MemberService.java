package edu.pnu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {

	private MemberDao memberDao;
	
	public MemberService() {
		this.memberDao = new MemberDao();
	}
	
	public List<MemberVO> getMembers() {
		return memberDao.getMembers();
	}

	public MemberVO getMember(Integer id) {
		return memberDao.getMember(id);
	}

	public MemberVO addMember(MemberVO member) {
		return memberDao.addMember(member);
	}

	public MemberVO updateMember(MemberVO member) {
		return memberDao.updateMember(member);
	}

	public boolean deleteMember(Integer id) {
		return memberDao.deleteMember(id);
	}

}
