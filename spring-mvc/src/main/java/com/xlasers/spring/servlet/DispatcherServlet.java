package com.xlasers.spring.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.ReflectUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xlasers.spring.annotation.XController;
import com.xlasers.spring.annotation.XRequestMapping;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import static cn.hutool.core.util.ReUtil.replaceAll;
import static cn.hutool.core.util.StrUtil.lowerFirst;
import static cn.hutool.core.util.StrUtil.replace;

/**
 * The type X dispatcher servlet.
 *
 * @package: com.Xlasers.spring.servlet
 * @author: Elijah.D
 * @time: CreateAt 2018/9/18 && 20:25
 * @description: Dispatcher中央处理器
 * @copyright: Copyright © 2018 Xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@Slf4j
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 4601928859165666892L;

    private final Properties properties = new Properties();

    private final List<String> classNames = Lists.newArrayListWithCapacity(1);

    private final Map<String, Object> ioc = Maps.newHashMap();

    private final Map<String, Method> handlerMappings = Maps.newHashMap();

    private final Map<String, Object> controllers = Maps.newHashMap();

    /**
     * servlet启动: 初始化
     *
     * @param config
     */
    @Override
    public void init(ServletConfig config) {

        //初始化配置文件,读取initParam下的值(将web.xml加载到properties中)
        loadConfig(config.getInitParameter("contextConfigLocation"));

        //扫描指定包下的class文件,获取className
        scanClass(properties.getProperty("scanPackage"));

        //初始化ioc,根据上述的className反射获取实例,name-bean放到ioc中
        initIoc();

        //初始化handlerMapping,将url与method对应
        initHandlerMapping();
    }

    /**
     * get
     *
     * @param req
     * @param res
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) {
        doPost(req, res);
    }

    /**
     * post
     *
     * @param req
     * @param res
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) {
        doDispatch(req, res);
    }

    /**
     * 真正处理请求的doDispatch
     *
     * @param req
     * @param res
     */
    private void doDispatch(HttpServletRequest req, HttpServletResponse res) {

        if (handlerMappings.isEmpty()) {
            return;
        }

        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = replace(url, contextPath, "").replaceAll("/+", "/");

        if (!handlerMappings.containsKey(url)) {
            try {
                res.getWriter().write("Ops ! 404 ! sorry not found !");
            } catch (IOException e) {
                log.error("【dispatcherServlet】 io异常,输出响应信息失败 ! {}", e.getMessage());
            }
            return;
        }

        Method method = handlerMappings.get(url);

        //获取方法参数类型列表
        Class<?>[] paramTypes = method.getParameterTypes();

        //获取实际请求参数值
        Map<String, String[]> paramMap = req.getParameterMap();

        //参数容器
        Object[] paramValues = new Object[paramTypes.length];

        //判断参数类型,参数值进行转换
        for (int i = 0; i < paramTypes.length; i++) {
            String requestType = paramTypes[i].getSimpleName();

            if ("String".equals(requestType)) {
                for (Map.Entry<String, String[]> param : paramMap.entrySet()) {
                    String value = replaceAll(Arrays.toString(param.getValue()), "\\[|]", "").replaceAll(",\\s", ",");
                    paramValues[i] = value;
                }
                continue;
            }

            if ("HttpServletRequest".equals(requestType)) {
                paramValues[i] = req;
            }

            if ("HttpServletResponse".equals(requestType)) {
                paramValues[i] = res;
            }
        }

        //传入对应的method,param反射调用
        try {
            method.invoke(controllers.get(url), paramValues);
        } catch (Exception e) {
            log.error("【dispatcherServlet】 反射异常,调用方法失败 ! {}", e.getMessage());
        }
    }

    /**
     * Properties流式加载web.xml的内容
     *
     * @param location {@link ServletConfig#getInitParameter(String)}
     */
    private void loadConfig(String location) {

        try {
            @Cleanup InputStream stream = getClass().getClassLoader().getResourceAsStream(location);
            properties.load(stream);
        } catch (IOException e) {
            log.error("【dispatcherServlet】 io异常,资源文件读取失败 ! {}", e.getMessage());
        }
    }

    /**
     * Recursive递归获取指定包下所有class文件名称
     *
     * @param packageName
     */
    private void scanClass(String packageName) {
        URL url = getClass().getClassLoader().getResource("/" + replaceAll(packageName, "\\.", "/"));

        File dir = new File(url.getFile());

        for (File file : dir.listFiles()) {

            if (file.isDirectory()) {
                scanClass(packageName + "." + file.getName());
            }

            String className = packageName + "." + replace(file.getName(), ".class", "");
            classNames.add(className);
        }
    }

    /**
     * 初始化ioc,实例化装载bean
     */
    private void initIoc() {

        if (classNames.isEmpty()) {
            return;
        }

        for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(XController.class)) {
                    ioc.put(lowerFirst(clazz.getSimpleName()), clazz.newInstance());
                }
            } catch (Exception e) {
                log.error("【dispatcherServlet】 反射异常,创建类实例失败 ! {}", e.getMessage());
            }
        }
    }

    /**
     * 初始化handlerMapping,创建url && methods对应关系
     */
    private void initHandlerMapping() {

        if (ioc.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Object> entry : ioc.entrySet()) {

            Class<?> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(XController.class)) {
                return;
            }

            String controllerUrl = "";
            if (clazz.isAnnotationPresent(XRequestMapping.class)) {
                XRequestMapping annotation = clazz.getAnnotation(XRequestMapping.class);
                controllerUrl = annotation.value();
            }

            Method[] methods = ReflectUtil.getMethods(clazz);
            for (Method method : methods) {
                if (method.isAnnotationPresent(XRequestMapping.class)) {

                    String methodUrl = method.getAnnotation(XRequestMapping.class).value();
                    handlerMappings.put(controllerUrl + methodUrl, method);

                    try {
                        controllers.put(controllerUrl + methodUrl, clazz.newInstance());
                    } catch (Exception e) {
                        log.error("【dispatcherServlet】 反射异常,创建类实例失败 ! {}", e.getMessage());
                    }

                    log.info("【dispatcherServlet】 反射获取注解内容,创建对应的关系,url: {}, method: {}", controllerUrl + methodUrl, method);
                }
            }
        }
    }
}
