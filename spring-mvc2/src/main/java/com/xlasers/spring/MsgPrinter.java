package com.xlasers.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The type Msg printer.
 *
 * @package: com.xlasers.spring
 * @author: Elijah.D
 * @time: CreateAt 2018/9/21 && 16:55
 * @description:
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@Slf4j
@Component
public class MsgPrinter {
    /**
     * The Msg service.
     */
    @Autowired
    MsgService msgService;

    void printMsg() {
        log.info("【spring】msgPrinter 调用, msg: {}",this.msgService.getMsg());
    }
}
