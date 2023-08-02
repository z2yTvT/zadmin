package com.z.entity.dto;


import com.alibaba.fastjson.annotation.JSONField;
import com.z.entity.sys.SUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class SecurityUserDto implements UserDetails {

    private SUser user;

    /**
     * 权限列表
     */
    private List<AuthorityDto> permissions;

    public SecurityUserDto(SUser user, List<AuthorityDto>  permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @Override
    @JSONField(serialize = false)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions;
    }

    @Override
    @JSONField(serialize = false)
    public String getPassword() {
        return this.user.getPassWord();
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
    }

    /**
     * 账户是否过期
     */
    @Override
    @JSONField(serialize = false)
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否过期被锁定
     */
    @Override
    @JSONField(serialize = false)
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 账户凭证是否过期
     */
    @Override
    @JSONField(serialize = false)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账户是否可用
     */
    @Override
    @JSONField(serialize = false)
    public boolean isEnabled() {
        return true;
    }
}
