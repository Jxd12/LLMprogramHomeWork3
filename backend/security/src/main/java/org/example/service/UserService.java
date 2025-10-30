package org.example.service;

import org.example.entity.VO.UserSecurityVO;

public interface UserService {
    UserSecurityVO login(UserSecurityVO user);
    UserSecurityVO register(UserSecurityVO user);
}
