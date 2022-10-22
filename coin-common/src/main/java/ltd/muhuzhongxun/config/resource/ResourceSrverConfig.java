package ltd.muhuzhongxun.config.resource;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableResourceServer
public class ResourceSrverConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement().disable()
                .authorizeRequests().antMatchers(
                        "/gt/register",
                        "/login",
                        "/v3/api-docs",
                        "/swagger-resources/configuration/ui",//用来获取支持的动作
                        "/swagger-resources",//用来获取api-docs的URI
                        "/swagger-resources/configuration/security",//安全选项
                        "/webjars/**",
                        "/swagger-ui/**"
                ).permitAll()
                .antMatchers("/**").authenticated()//其余路径需要认证
                .and().headers().cacheControl();
    }

    /**
     * 设置公钥
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore());
    }

    private TokenStore tokenStore() {
        JwtTokenStore jwtTokenStore = new JwtTokenStore(accessTokenConverter());
        return jwtTokenStore;
    }

    // resource 验证 token（公钥） authorization 产生 token （私钥）
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        ClassPathResource classPathResource = new ClassPathResource("coinexchange.pub");
        String publicKey = null;
        try {
            byte[] bytes = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
            publicKey = new String(bytes, "utf-8");
        } catch (IOException e) {
//            log.info("读取公钥失败");
            e.printStackTrace();
        }
        tokenConverter.setVerifierKey(publicKey);
        return tokenConverter;
    }
}
