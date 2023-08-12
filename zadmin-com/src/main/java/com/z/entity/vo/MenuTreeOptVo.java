package com.z.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class MenuTreeOptVo {

    private Long id;

    private String label;

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private List<MenuTreeOptVo> children;

}
