package com.gongguiljeong.global.base_model;

import com.gongguiljeong.domain.admin.model.Role;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserAdmin extends UserDetails {

    Long getId();

    Role getRole();


}
