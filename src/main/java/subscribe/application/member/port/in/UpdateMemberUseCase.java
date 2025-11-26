package subscribe.application.member.port.in;

public interface UpdateMemberUseCase {

	void updateMember(String providerId, UpdateMemberRequest request);
}
