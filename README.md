# keycloak security
oauth2 resource server 에서의 security 생성.

## 사전 준비
keycloak 이 설치 되어 있고, 해당 realm 에서 토큰이 발급된 상태에서 테스트 가능함. 
Request 요청할 때, token일 제공해야 하기 때문이다. 

## 동작확인
clinet.http 파일에 있는 내용 확인. 

## 코드 설명
```
protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.authorizeRequests(
        req -> req.antMatchers("/user/**").hasAuthority("SCOPE_profile")
        .antMatchers("/admin/**").hasAuthority("SCOPE_admin")
        .anyRequest().authenticated()
    )
    .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
}
```
위 사항은 일반 spring security와 동일하다. 다만, role 대싱 authotiry를 사용하고, 
oauth2 인증을 위해 추가로 configure 설정이 된다. 
> config 설정도 정확히 말하면, oauth2 서버 연결정보가 아니라 JWT 토큰을 validate 하기 위해 필요한 public-key 주소가 제공된다. 

```
@Value("${keycloak.jwtSetUri}")
private String jwkSetUri;

@Bean
public JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
}
```
위 사항은 앞서 말한  JWT 토큰 validate 을 위해 필요한 public-key 값을 구하는 설정이다. 
토큰 해독을 위해 Numbus 모듈을 사용한다. 

## keycloak scope 설정
[여기 참고](https://wstutorial.com/rest/spring-security-oauth2-keycloak.html)