package subscribe.application.token.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import subscribe.application.token.port.in.SaveRefreshTokenUseCase;
import subscribe.application.token.port.out.SaveRefreshTokenPort;
import subscribe.domain.token.RefreshToken;

@Service
@RequiredArgsConstructor
public class RefreshTokenCrudService implements SaveRefreshTokenUseCase {

	private final SaveRefreshTokenPort saveRefreshTokenPort;

	@Transactional
	@Override
	public void saveRefreshToken(RefreshToken refreshToken) {
		saveRefreshTokenPort.saveRefreshToken(refreshToken);
	}
}
