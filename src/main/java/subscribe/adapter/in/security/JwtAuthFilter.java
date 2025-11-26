package subscribe.adapter.in.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import subscribe.adapter.in.security.oauth2.CustomOAuth2User;
import subscribe.application.exception.CustomException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

	private final JwtManager jwtManager;

	@Override
	protected void doFilterInternal(
		HttpServletRequest request,
		HttpServletResponse response,
		FilterChain filterChain
	) throws ServletException, IOException {
		String accessToken;
		try {
			String authorizationHeader = request.getHeader("Authorization");
			jwtManager.verifyAuthorizationHeader(authorizationHeader);

			accessToken = authorizationHeader.split(" ")[1];
			jwtManager.verifyTokenExpiration(accessToken, new Date());
		} catch (JwtException | CustomException e) {
			request.setAttribute("exception", e);
			filterChain.doFilter(request, response);
			return;
		}

		OAuth2User oAuth2User = new CustomOAuth2User(
			jwtManager.getProviderId(accessToken)
		);

		SecurityContextHolder.getContext().setAuthentication(
			new UsernamePasswordAuthenticationToken(
				oAuth2User,
				Optional.empty(),
				Collections.emptyList()
			)
		);

		filterChain.doFilter(request, response);
	}
}
