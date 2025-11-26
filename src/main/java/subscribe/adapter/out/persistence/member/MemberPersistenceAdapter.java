package subscribe.adapter.out.persistence.member;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import subscribe.application.exception.CustomException;
import subscribe.application.exception.ErrorCode;
import subscribe.application.member.port.out.SaveMemberPort;
import subscribe.application.member.port.out.UpdateMemberPort;
import subscribe.domain.member.Member;

@Component
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements
	SaveMemberPort,
	UpdateMemberPort {

	private final SpringDataMemberRepository memberRepository;
	private final MemberMapper memberMapper;

	@Override
	public void saveMember(Member member) {
		if (!memberRepository.existsByProviderId(member.getProviderId())) {
			MemberJpaEntity memberEntity = memberMapper.toJpaEntity(member);
			memberRepository.save(memberEntity);
		}
	}

	@Override
	public void updateMember(Member member) {
		MemberJpaEntity memberEntity = memberRepository.findByProviderId(member.getProviderId())
			.orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

		memberEntity.update(
			member.getNickname(),
			member.getEmail()
		);
	}
}
