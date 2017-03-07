package com.yun.service;

import java.util.List;

import com.yun.dao.MemberDao;
import com.yun.dto.Member;

public class MemberServiceImpl implements MemberService{
	private MemberDao dao;
	
	public MemberServiceImpl(MemberDao dao) {
		this.dao = dao;
	}
	
	@Override
	public int add(Member member) {																			// ȸ����
		
		int x = -1;
		
		if(dao.checkId(member.getId()) == 0){			// id �ߺ�üũ�� �Ѵ�.
			x = dao.add(member);								// �ߺ� ���� ������ ����� �õ��Ѵ�. (id �ߺ��� ������ ��� ������ ������ ���̽��� ����� ���еǸ� -1�� x�� ���Եȴ�.)
		}
		
		return x;													// �ߺ� �Ǵ� ��� ������ ���� ������ ���̽��� ��� �����ϸ� -1�� �����Ѵ�.
	}

	@Override
	public int delete(String id) {																					// ȸ�� Ż�� 
		int x = -1;								
		
		if(dao.checkId(id) > 0){	// id�� ������ �����Ѵ�.
			x = dao.delete(id);
		}
		
		return x;				// 1�Ǵ� -1�� �����ش�.
	}

	@Override
	public int update(Member member) {																	// ȸ�����
		int x = -1;
		
		if(dao.checkId(member.getId()) > 0){		// id�� ������ �����Ѵ�.
			x = dao.update(member);
		}
		
		return x;				// 1�Ǵ� -1�� �����ش�.
	}

	@Override
	public Member get(String id) {																				// Ư�� ȸ�� ��ȸ
		
		return dao.get(id);		// Ư�� ȸ�� ������ �����ش�. ������ null�� �����ش�.
	}

	@Override
	public List<Member> getAll() {																				// ��� ȸ�� ��ȸ
		List<Member> members = null;
		
		members =  dao.getAll();
		
		return members;	// ��� ȸ�� ������ �����ش�. ������ null�� �����ش�.
	}

	@Override
	public int getCount() {																						// ȸ�� �� ��ȸ
		return dao.getCount();	// ȸ�� ���� �����ش�.
	}

	@Override
	public int deleteAll() {																							// ��� ȸ�� Ż�� �����.. **�� �޼ҵ�� �����ؼ� ���..;;
		int x = -1;
		
		if(dao.getCount() > 0){	// ȸ�� ������ ������ -1�� ������ ���̴�. ������ ���̽� ������ ������ �־ -1�� ������ ���̴�.. ���� � ���� �������� �ľ��ϱ� ��� �� ���Ƽ� if �۾��� �ɾ�ξ��.
			x = dao.deleteAll();
		}
		
		return x;	// 1�Ǵ� -1�� �����ش�.
	}

	@Override
	public int checkId(String id) {
		return dao.checkId(id);
	}
}
