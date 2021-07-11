package spring;

/*
 * 자바빈 규칙
      - 메서드 이름이 set으로 시작한다.
      - set 뒤에 첫 글자는 대문자로 시작한다.
      - 파라미터가 1개 이다.
      - 리턴 타입이 void 이다.
 * 자바빈에서는 getter와 setter를 이용해서 프로퍼티를 정의한다.
 * setAge와 같은 쓰기 메서드는 프로퍼티(property) 값을 변경하므로 프로퍼티 설정 메서드라고도 부른다.
*/
public class MemberInfoPrinter {
    private MemberDao memberDao;
    private MemberPrinter printer;

    public void printMemberInfo(String email) {
        Member member = memberDao.selectByEmail(email);
        if(member == null) {
            System.out.println("데이터 없음\n");

            return;
        }
        printer.print(member);
        System.out.println();
    }

    // 의존 주입을 위한 setter 메서드
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    // 의존 주입을 위한 setter 메서드
    public void setPrinter(MemberPrinter printer) {
        this.printer = printer;
    }
}
