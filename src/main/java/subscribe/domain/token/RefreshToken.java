package subscribe.domain.token;

import java.util.Date;

import lombok.Getter;

@Getter
public class RefreshToken {

	private String token;

	private String providerId;

	private Date issuedAt;

	private Date expiration;

	public RefreshToken(
		String token,
		String providerId,
		Date issuedAt,
		Date expiration
	) {
		this.token = token;
		this.providerId = providerId;
		this.issuedAt = issuedAt;
		this.expiration = expiration;
	}
}
