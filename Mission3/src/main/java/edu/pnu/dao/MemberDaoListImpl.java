package edu.pnu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDaoListImpl implements MemberInterface {
	private List<MemberVO> list;
	
	public MemberDaoListImpl() {
		list = new ArrayList<>();
		for (int i = 1 ; i < 21 ; i++) {
			list.add(new MemberVO(i, "1234", "이름"+i, new Date()));
		}	
	}
	
	@Override
	public List<MemberVO> getMembers() {
		return list;
	}

	@Override
	public MemberVO getMember(Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id)
				return m;
		}
		return null;
	}
	
	private int getNextId() {
		return list.size() + 1;
	}
	
	@Override
	public MemberVO addMember(MemberVO member) {
		member.setId(getNextId());
		member.setRegidate(new Date());
		return member;
	}

	@Override
	public MemberVO updateMember(MemberVO member) {
		for (MemberVO m : list) {
			if (m.getId() == member.getId()) {
				m.setName(member.getName());
				m.setPass(member.getPass());
				return m;
			}
		}
		return null;
	}

	@Override
	public int deleteMember(Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id) {
				list.remove(m);
				return 1;
			}
		}
		return 0;
	}

}
