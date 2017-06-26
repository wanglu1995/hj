package com.baizhi.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * Created by lenovo on 2017/6/20.
 */
public class MyRealm extends AuthorizingRealm {
    /*授权*/
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {




        return null;
    }

    /*认证*/
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("用户名:"+token.getPrincipal());
        if("zhangsan".equals(token.getPrincipal())){
            return new SimpleAuthenticationInfo("zhangsan", "f1546e31924e82fa09f1372a43f9f809",
                    ByteSource.Util.bytes("io8e"),
                    this.getName());
        }
        return null;
    }
}
