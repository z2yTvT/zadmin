package com.z.config;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.handler.DataPermissionHandler;
import com.z.annotation.DataScope;
import com.z.bean.enums.DataScopeEnum;
import com.z.entity.sys.SUser;
import com.z.exception.ServiceException;
import com.z.utils.SecurityUtils;
import lombok.SneakyThrows;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;


import java.lang.reflect.Method;

public class DataPermissionHandlerImpl implements DataPermissionHandler {
    @SneakyThrows
    @Override
    public Expression getSqlSegment(Expression where, String mappedStatementId) {

        //where:  id = ?
        //mappedStatementId: com.z.sys.mapper.SUserMapper.selectById

        //eg:com.z.sys.mapper.SMenuRoleMapper
        Class<?> clazz = Class.forName(mappedStatementId.substring(0, mappedStatementId.lastIndexOf(".")));//com.z.sys.mapper.SUserMapper
        String methodName = mappedStatementId.substring(mappedStatementId.lastIndexOf(".") + 1);//selectById
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            DataScope dataScopeAnnotation = method.getAnnotation(DataScope.class);
            if (ObjectUtil.isNotEmpty(dataScopeAnnotation) && method.getName().equals(methodName)) {
                SUser user = SecurityUtils.getSecurityUser().getUser();
                if (ObjectUtil.isNotEmpty(user)) {
                    return dataScopeFilter(user, where, dataScopeAnnotation);
                }
            }
        }
        return where;
    }

    private Expression dataScopeFilter(SUser user, Expression where, DataScope dataScopeAnnotation) {
        DataScopeEnum dataScopeEnum = dataScopeAnnotation.dataScopeEnum();
        String deptAlias = dataScopeAnnotation.deptAlias();
        String userAlias = dataScopeAnnotation.userAlias();
        String deptAlisWithId = StrUtil.isNotBlank(deptAlias) ? deptAlias + "." + "id" : deptAlias;
        String userAliasWithId = StrUtil.isNotBlank(userAlias) ? userAlias + "." + "id" : userAlias;
        Long deptId = SecurityUtils.getSecurityUser().getUser().getDeptId();
        Long userId = SecurityUtils.getSecurityUser().getUser().getId();
        String filterSql = "";
        switch (dataScopeEnum) {
            case DATA_SCOPE_All:
                return where;
            case DATA_SCOPE_DEPT:
                filterSql = deptAlisWithId + "=" + deptId;
                break;
            case DATA_SCOPE_SELF:
                filterSql = userAliasWithId + "=" + userId;
                break;
            default:
                break;
        }
        if (StrUtil.isBlank(filterSql)) {
            return where;
        }
        Expression expression;
        try {
            expression = CCJSqlParserUtil.parseCondExpression(filterSql);
        } catch (Exception e) {
            throw new ServiceException(e, "500");
        }

        return new AndExpression(where, expression);
    }
}
