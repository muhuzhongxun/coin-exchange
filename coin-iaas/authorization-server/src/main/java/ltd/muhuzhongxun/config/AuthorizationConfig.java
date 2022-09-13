package ltd.muhuzhongxun.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@EnableAuthorizationServer
@Configuration
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    public PasswordEncoder passwordEncoder ;

    @Autowired
    private AuthenticationManager authenticationManager ;

    @Autowired
    private UserDetailsService userDetailsService ;
    /**
     * 配置第三方客户端
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("coin-api")
                .secret(passwordEncoder.encode("coin-secret"))
                .scopes("all")
                .authorizedGrantTypes("password","refresh")
                .accessTokenValiditySeconds(24 * 7200)
                .refreshTokenValiditySeconds(7 *  24 * 7200) ;
    }

    /**
     * 设置授权管理器和UserDetailsService
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(new InMemoryTokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService) ;
    }
}
