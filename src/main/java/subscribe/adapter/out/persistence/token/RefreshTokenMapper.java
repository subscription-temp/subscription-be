package subscribe.adapter.out.persistence.token;

import org.springframework.stereotype.Component;

import subscribe.domain.token.RefreshToken;

@Component
public class RefreshTokenMapper {

	public RefreshTokenJpaEntity toJpaEntity(RefreshToken refreshToken) {
		return new RefreshTokenJpaEntity(
			refreshToken.getToken(),
			refreshToken.getProviderId(),
			refreshToken.getIssuedAt(),
			refreshToken.getExpiration()
		);
	}
}
