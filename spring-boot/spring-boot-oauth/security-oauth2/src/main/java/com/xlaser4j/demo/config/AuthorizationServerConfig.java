package com.xlaser4j.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @package: com.xlaser4j.demo.config
 * @author: Elijah.D
 * @time: 2020/2/4 19:53
 * @description:
 * @modified: Elijah.D
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager manager;

    private final RedisConnectionFactory factory;

    private final UserDetailsService userDetailsService;

    public AuthorizationServerConfig(AuthenticationManager manager, RedisConnectionFactory factory, UserDetailsService userDetailsService) {
        this.manager = manager;
        this.factory = factory;
        this.userDetailsService = userDetailsService;
    }

    /**
     * 配置授权信息,oauth2授权类型有四种,这里用的password也是前后端分离常用的方案
     * <p>
     * resourceIds:要配置与resource server一致
     * <p>
     * grantType是用来区分oauth类型,refresh_token是security的机制
     * <p>
     * /oauth/token-获取token参数:
     * grant_type:password
     * username:admin
     * password:123
     * client_id:password
     * client_secret:1234
     * scope:all
     * <p>
     * /oauth/token-刷新token参数:
     * grant_type:refresh_token
     * refresh_token:0f3b97e5-4b53-4e9a-81cd-11a29b6dc37a
     * client_id:password
     * client_secret:1234
     * scope:all
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("password")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(60)
                .resourceIds("custom_id")
                .scopes("all")
                .secret("1234");
    }

    /**
     * 配置授权信息:token存储,manager,登陆service
     *
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(new RedisTokenStore(factory))
                .authenticationManager(manager)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.allowFormAuthenticationForClients();
    }
}
