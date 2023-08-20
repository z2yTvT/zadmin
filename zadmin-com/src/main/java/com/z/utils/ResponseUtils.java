package com.z.utils;


import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;

import com.z.bean.base.Response;
import com.z.bean.enums.ResponseCodeEnum;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtils {

    public static void errResp(HttpServletResponse response, ResponseCodeEnum responseCodeEnum) throws IOException {
        switch (responseCodeEnum){
            case EXPIRE_TOKEN:
            case INVALID_TOKEN:
                response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
                break;
        }
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(JSON.toJSONString(Response.error(responseCodeEnum)));
    }

}
