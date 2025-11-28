package subscribe.application.member.port.out;

import subscribe.domain.member.Member;

public interface SyncMemberPort {

	Long syncMember(Member member);
}
