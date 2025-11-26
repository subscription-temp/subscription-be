package subscribe.application.token.port.out;

import subscribe.domain.token.RefreshToken;

public interface SaveRefreshTokenPort {

	void saveRefreshToken(RefreshToken refreshToken);
}
