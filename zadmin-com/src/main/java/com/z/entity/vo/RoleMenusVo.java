package com.z.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class RoleMenusVo {

    private Long id;

    private String menuName;

    private List<RoleMenusVo> subMenu;

}
