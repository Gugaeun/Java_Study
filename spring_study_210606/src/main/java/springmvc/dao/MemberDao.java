package springmvc.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import springmvc.model.Member;


@Repository
public class MemberDao {
	private static long nextId = 0;

    private Map<String, Member> mapBaseEmail = new HashMap<String, Member>();

    /**
     * 회원 조회(단건)
     */
    public Member selectByEmail(String email) {
        return mapBaseEmail.get(email);
    }

    /**
     * 회원 등록 목적
     */
    public void insert(Member member) {
        // 회원번호
        member.setId(++nextId);

        mapBaseEmail.put(member.getEmail(), member);
//        mapBaseId.put(member.getId(), member);
    }

    /**
     * 회원 수정(단건)
     */
    public void update(Member member) {
        mapBaseEmail.put(member.getEmail(), member);
    }

    public Collection<Member> selectAll() {
        return mapBaseEmail.values();
    }
}
