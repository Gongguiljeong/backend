package com.gongguiljeong.domain.common.domain;

import com.gongguiljeong.domain.admin.domain.Role;
import org.springframework.security.core.userdetails.UserDetails;

public interface Member extends UserDetails {

    Long getId();
    Role getRole();
}
