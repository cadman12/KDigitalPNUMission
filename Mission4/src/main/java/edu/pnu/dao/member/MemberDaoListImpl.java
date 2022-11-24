package edu.pnu.dao.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public Map<String, Object> getMembers() {
		Map<String, Object> map = new HashMap<>();
		map.put("sql", "from list getMembers()");
		map.put("data", list);
		return map;
	}

	@Override
	public Map<String, Object> getMember(Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id) {
				Map<String, Object> map = new HashMap<>();
				map.put("sql", "from list getMember()");
				map.put("data", m);
				return map;
			}
		}
		return null;
	}
	
	private int getNextId() {
		return list.size() + 1;
	}
	
	@Override
	public Map<String, Object> addMember(MemberVO member) {
		member.setId(getNextId());
		member.setRegidate(new Date());
		list.add(member);
		
		Map<String, Object> map = new HashMap<>();
		map.put("sql", "from list addMember");
		map.put("data", member);
		
		return map;
	}

	@Override
	public Map<String, Object> updateMember(MemberVO member) {
		for (MemberVO m : list) {
			if (m.getId() == member.getId()) {
				m.setName(member.getName());
				m.setPass(member.getPass());
				
				Map<String, Object> map = new HashMap<>();
				map.put("sql", "from list updateMember");
				map.put("data", m);				

				return map;
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> deleteMember(Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id) {
				list.remove(m);
				
				Map<String, Object> map = new HashMap<>();
				map.put("sql", "from list deleteMember");
				map.put("data", m);	
				
				return map;
			}
		}
		return null;
	}
}
