package com.rubik.post_date.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(
        name = "OAuth 2.0",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(
                authorizationCode = @OAuthFlow(
                        authorizationUrl = "${springdoc.oauth.authorization-url}",
                        tokenUrl = "${springdoc.oauth.token-url}",
                        scopes = {
                                @OAuthScope(name = "read", description = "read scope"),
                                @OAuthScope(name = "write", description = "write scope")
                        }
                )
        )
)
@SecurityScheme(
        name = "Basic Auth",
        scheme = "basic",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)

public class OpenApi3Config {
}
