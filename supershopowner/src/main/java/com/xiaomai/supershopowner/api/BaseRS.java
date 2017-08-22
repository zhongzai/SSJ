package com.xiaomai.supershopowner.api;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class BaseRS {
	
	public final String TIME_START_DEFAULT = " 00:00:00";
	public final String TIME_END_DEFAULT = " 23:59:59";

    // 子类可以直接使用request对象,开发测试期加required=false，生产环境去掉
    @Autowired(required = false)
    protected HttpServletRequest request;

    /**
     * 用于日期格式的转换，页面中有日期格式的需要方面做转换
     * 
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    /**
     * 获取当前系统登录用户
     * 
     * @return
     */
//    protected Admin getAdminLoginUser() {
//        HttpSession session = request.getSession();
//        return null;
//    }

    /**
     * 获取当前登录用户
     * 
     * @return
     */
    protected String getLoginUser() {
        HttpSession session = request.getSession();
        return (String) session.getAttribute("user");

    }
    
    protected HttpSession newSession(HttpServletRequest request) {
		HttpSession oldSession = request.getSession();
		if (null != oldSession) {
			oldSession.invalidate();
		}
		return request.getSession(true);
	}
    

    protected HashMap<String, Object> getQueryMap() {
    	int pageNum = 1;
        int pageSize = 10;

        // 翻页相关参数
        HashMap<String, Object> map = new HashMap<String, Object>();
        pageNum = StringUtils.isNotBlank(request.getHeader("pageNum"))
                        && StringUtils.isNumeric(request.getHeader("pageNum")) ? Integer
                        .parseInt(request.getHeader("pageNum")) : pageNum;
        pageSize =
                StringUtils.isNotBlank(request.getHeader("pageSize"))
                        && StringUtils.isNumeric(request.getHeader("pageSize")) ? Integer
                        .parseInt(request.getHeader("pageSize")) : pageSize;
        int startIndex = (pageNum - 1) * pageSize < 0 ? 0 : (pageNum - 1) * pageSize;
        map.put("startIndex", startIndex);
        map.put("pageSize", pageSize);
        return map;
    }

    /**
     * 参数转换为Bean对象，用于多文件上传file名不同时，无法自动封装Bean的场景
     *
     * @param request
     * @param clazz
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    protected <T> T transformParamToBean(HttpServletRequest request, Class<T> clazz)
            throws Exception {
        Object result = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (StringUtils.isNotBlank(request.getParameter(field.getName()))) {
                field.setAccessible(true);
                if (field.getType() == Integer.class) {
                    field.set(result, Integer.valueOf(request.getParameter(field.getName())));
                } else if (field.getType() == Long.class) {
                    field.set(result, Long.valueOf(request.getParameter(field.getName())));
                } else if (field.getType() == Date.class) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    dateFormat.setLenient(false);
                    field.set(result, dateFormat.parse(request.getParameter(field.getName())));
                } else {
                    field.set(result, request.getParameter(field.getName()));
                }
            }
        }

        return (T) result;
    }
}
