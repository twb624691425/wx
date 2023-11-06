package com.tang.wx.config.shiro;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.support.json.JSONWriter;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.tang.wx.utils.CustomException;
import com.tang.wx.utils.JwtUtil;
import com.tang.wx.utils.Res;
import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Component
@Scope("prototype")
public class OAuth2Filter extends AuthenticatingFilter {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${custom.jwt.timeout}")
    private long timeout;

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        String token = httpServletRequest.getHeader("token");
        if (token == null || "".equals(token)) {
            return  null;
        }
        return new OAuth2Token(token);
    }

    // 拦截请求，判断请求是否需要被Shiro处理
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }
        return false;
    }

    // 处理所有应该被shiro处理的请求
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        resp.setHeader("Content-Type", "application/json;charset=UTF-8");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Origin", "*");

        String token = req.getHeader("token");
        if (token == null || "".equals(token)) {
            HashMap<String, Object> map = Res.error(HttpStatus.SC_UNAUTHORIZED, "未获取授权凭证");
            String res = JSONUtils.toJSONString(map);
            resp.setStatus(HttpStatus.SC_OK);
            resp.getWriter().print(res);
            return false;
        }
        try {
            jwtUtil.verifierToken(token);
            int userId = jwtUtil.getUserId(token);
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            String tokenFromRedis = valueOperations.get("token-" + userId);
            if (tokenFromRedis == null || "".equals(tokenFromRedis) || !tokenFromRedis.equals(token)) {
                HashMap<String, Object> map = Res.error(HttpStatus.SC_UNAUTHORIZED, "令牌不存在或已失效，请重新获取令牌");
                String res = JSONUtils.toJSONString(map);
                resp.setStatus(HttpStatus.SC_OK);
                resp.getWriter().print(res);
//                resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
//                resp.getWriter().print("令牌不存在或已失效，请重新获取令牌");
                return false;
            } else {
                redisTemplate.expire("token-" + userId, this.timeout, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            resp.setStatus(HttpStatus.SC_OK);
            if (e instanceof JWTDecodeException) {
                HashMap<String, Object> map = Res.error(HttpStatus.SC_UNAUTHORIZED, "令牌解析错误，存在被篡改可能性");
                String res = JSONUtils.toJSONString(map);
                resp.getWriter().print(res);
            } else {
                e.printStackTrace();
                HashMap<String, Object> map = Res.error(HttpStatus.SC_UNAUTHORIZED, "无效的令牌");
                String res = JSONUtils.toJSONString(map);
                resp.getWriter().print(res);
            }
            return false;
        }
        return executeLogin(servletRequest, servletResponse);
    }

    @Override
    public void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        super.doFilterInternal(request, response, chain);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
        resp.setContentType("application/json;charset=utf-8");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        try {
            resp.getWriter().print(e.getMessage());
        } catch (IOException exception) {

        }
        return false;
    }












}
