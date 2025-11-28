package subscribe.application.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import subscribe.application.member.port.in.UpdateMemberRequest;
import subscribe.application.member.port.in.UpdateMemberUseCase;
import subscribe.application.member.port.out.UpdateMemberPort;
import subscribe.domain.member.Member;

@Service
@RequiredArgsConstructor
public class MemberCrudService implements UpdateMemberUseCase {

	private final UpdateMemberPort updateMemberPort;

	@Transactional
	@Override
	public void updateMember(Long memberId, UpdateMemberRequest request) {
		Member member = Member.withId(
			memberId,
			null,
			request.nickname(),
			request.email()
		);
		updateMemberPort.updateMember(member);
	}
}
