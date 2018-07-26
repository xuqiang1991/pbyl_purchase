package com.cn.config;

import com.alibaba.fastjson.JSONObject;
import com.cn.common.utils.CommonUtil;
import com.cn.framework.mvc.controller.RespBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * 全局异常处理
 * Created by xuqiang on 2018/07/26.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Model model, Exception e) throws Exception {
        logger.info("GlobalException error", e);
		if (CommonUtil.isAjax(request)){ //如果是ajax请求响应头会有x-requested-with
			RespBody respBody = new RespBody();
			respBody.setResult("系统异常，请联系管理员！");
			respBody.setStatus(RespBody.StatusEnum.ERROR);

			OutputStream out = null;
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json; charset=utf-8");
			try {
				out = response.getOutputStream();
				response.getWriter().write(JSONObject.toJSON(respBody).toString());
				out.flush();
				out.close();
			} catch (Exception ex) {
				logger.error("返回数据错误", ex);
			} finally {
				if (out != null) {
					out.close();
				}
			}
            return null;
        }else{
            return "error";
        }
    }
}
