package com.z.bean.admin.req.role;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RelateRoleMenusReq {

    @NotNull(message = "角色id不能为空！")
    private Long rid;

    private List<Long> mIds;

    public List<Long> getmIds() {
        return mIds;
    }

    public void setmIds(List<Long> mIds) {
        this.mIds = mIds;
    }

}
