package com.z.entity.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptOptVO {

    private Long id;

    private String label;

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private List<DeptOptVO> children;
}
