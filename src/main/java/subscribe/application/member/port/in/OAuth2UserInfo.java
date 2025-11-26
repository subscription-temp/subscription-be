package subscribe.application.member.port.in;

import subscribe.domain.member.Member;

public record OAuth2UserInfo(
	String providerId,
	String nickname,
	String email
) {

	public Member toMember() {
		return Member.withoutId(
			providerId,
			nickname,
			email
		);
	}
}
