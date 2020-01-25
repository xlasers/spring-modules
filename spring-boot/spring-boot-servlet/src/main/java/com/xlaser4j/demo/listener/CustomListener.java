package com.xlaser4j.demo.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * {@link ServletRequestListener,javax.servlet.ServletContextListener,javax.servlet.ServletContextAttributeListener}
 *
 * @package: com.xlaser4j.demo.listener
 * @author: Elijah.D
 * @time: 2020/1/25 22:55
 * @description:
 * @modified: Elijah.D
 */
@WebListener
public class CustomListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("Test RequestListener RequestDestroyed!");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("Test RequestListener RequestInitialized!");
    }
}
