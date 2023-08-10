package com.z.service;


import java.util.List;


public interface SUserRoleService  {

    Boolean addUserRoles(List<Long> roleIds, Long userId);

    void deleteUserRole(Long id, List<Long> roleIds);
}
