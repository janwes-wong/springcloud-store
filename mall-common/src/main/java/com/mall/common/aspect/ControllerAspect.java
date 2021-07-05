package com.mall.common.aspect;

import com.alibaba.fastjson.JSON;
import com.mall.common.exception.BaseException;
import com.mall.common.model.Result;
import com.mall.common.model.StatusCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validator;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Janwes
 * @version 1.0
 * @package com.janwes.common.aspect
 * @date 2021/6/29 17:35
 * @description controller层切面类
 */
@Aspect
@Component
public class ControllerAspect {

    @Autowired
    private Validator validator;

    private static final Logger log = LoggerFactory.getLogger(ControllerAspect.class);

    /**
     * 定义切点Pointcut
     */
    @Pointcut("execution(* com.mall.*.controller.*.*(..))")
    public void executeService() {
    }

    /**
     * 环绕通知
     *
     * @return
     */
    @Around("executeService()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        // 获取当前request请求属性对象
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        // 获取请求路径
        String url = request.getRequestURL().toString();
        // 获取请求方法
        String method = request.getMethod();
        // 获取连接参数 例如 http://localhost:8080/login?username=jack 得到的为：username=jack
        String queryString = request.getQueryString();

        Object[] args = joinPoint.getArgs();
        String params = "";
        if (args.length > 0) {
            // post请求
            if ("POST".equals(method)) {
                Object object;
                object = args[0];
                Map<String, Object> map = this.getKeyAndValue(object);
                params = JSON.toJSONString(map);
            } else if ("GET".equals(method)) {
                // get请求
                params = queryString;
            }
        }
        log.info("api request === method:{} url:{}", method, url);
        log.info("api request === parameters:{}", params);

        // 校验参数
        checkParams(args);

        // result的值为被拦截方法的返回值
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        log.info("api response === result:{}", JSON.toJSONString(result));
        log.info("api response === runtime:{}", (end - start) + " ms");
        return result;
    }

    private Map<String, Object> getKeyAndValue(Object obj) {
        Map<String, Object> map = new HashMap<>();
        // 获取类对象
        if (obj == null) {
            obj = new Object();
        }
        Class<?> clazz = obj.getClass();
        // 获取类中所有属性集合
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 设置私有属性可以访问
            field.setAccessible(true);
            try {
                // 获取到属性的值
                Object val = field.get(obj);
                // 设置map集合的键值
                map.put(field.getName(), val);
            } catch (IllegalAccessException e) {
                log.error(e.getMessage(), e);
            }
        }
        return map;
    }

    /**
     * 检验参数是否通过
     *
     * @param args
     * @return
     */
    public boolean checkParams(Object[] args) {
        for (Object arg : args) {
            if (arg != null) {
                Result<Object> result = new Result<>(StatusCode.INVALID_PARAMS.code(), StatusCode.INVALID_PARAMS.message());

                Set<ConstraintViolation<Object>> constraintViolations = validator.validate(arg);
                if (constraintViolations != null && constraintViolations.size() > 0) {
                    for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
                        Path propertyPath = constraintViolation.getPropertyPath();
                        // 迭代器获取
                        String name = propertyPath.iterator().next().getName();
                        String errorMessage = "[" + name + "]" + constraintViolation.getMessage();
                        result.setMessage(errorMessage);
                        if (!StringUtils.isEmpty(errorMessage)) {
                            break;
                        }
                    }
                    String responseMessage = JSON.toJSONString(result);
                    log.error(responseMessage);
                    throw new BaseException(result.getCode(), result.getMessage());
                }
            }
        }
        return true;
    }
}
