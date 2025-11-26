package subscribe.adapter.out.persistence.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataMemberRepository extends JpaRepository<MemberJpaEntity, Long> {

	boolean existsByProviderId(String providerId);

	Optional<MemberJpaEntity> findByProviderId(String providerId);
}
