package com.tang.wx.service.impl;

import com.tang.wx.db.dao.TbUserMapper;
import com.tang.wx.db.pojo.TbUser;
import com.tang.wx.service.UserService;
import com.tang.wx.utils.CustomException;
import com.tang.wx.utils.JwtUtil;
import com.tang.wx.utils.Res;
import com.tang.wx.vo.LoginInfoVO;
import com.tang.wx.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private TbUserMapper tbUserMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private JwtUtil jwtUtil;
    @Value("${custom.jwt.timeout}")
    private long timeout;

    @Override
    public Res registry(HashMap map) {
        if ((map.get("registryCode").equals("000000")) && this.hasRootUser()) {
            return Res.error("已注册管理员");
        } else {
            Integer id = tbUserMapper.increaseId();
            HashMap sqlMap = new HashMap();
            sqlMap.putAll(map);
            sqlMap.put("id", id == null ? 1 : id + 1);
            sqlMap.put("createTime", new Date());
            sqlMap.put("nickname", map.get("nickname") != null && !map.get("nickname").equals("") ? map.get("nickname") : "未命名");
            sqlMap.put("root", map.get("registryCode").equals("000000") ? 1 : 0);
            sqlMap.put("role", map.get("registryCode").equals("000000") ? "[0]" : "[3]");
            this.insertUser(sqlMap);
            return Res.ok().put("msg", "注册成功");
        }
    }

    @Override
    public TbUser selectById(int id) {
        return tbUserMapper.selectById(id);
    }

    @Override
    public Res getUserInfo(int id) {
        TbUser user = tbUserMapper.selectById(id);
        if (user == null) {
            return Res.error("用户不存在");
        }
        Set<String> permissions = tbUserMapper.getUserPermission(id);
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setUser(user);
        userInfoVO.setPermissions(permissions);
        return Res.ok().put("data", userInfoVO);
    }

    @Override
    public Res login(String nickname, String openId) {
        TbUser user = tbUserMapper.selectByLogin(nickname, openId);
        if (user == null) {
            return Res.error("账号密码不存在");
        }
        if (user.getStatus() == 0) {
            return Res.error("该账号已冻结，无法登录");
        }
        String token = jwtUtil.createToken(user.getId());
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("token-" + user.getId(), token, this.timeout, TimeUnit.SECONDS);
        LoginInfoVO loginInfoVO = new LoginInfoVO(token, user);
        return Res.ok().put("data", loginInfoVO);
    }


    public void testException() {
       throw new CustomException("手动mock的异常");
    }

    private boolean hasRootUser() {
        boolean bool = tbUserMapper.hasRootUser();
        System.out.println("bool = " + bool);
        return bool;
    }



    public int insertUser(HashMap map) {
        return tbUserMapper.insertUser(map);
    }


}
