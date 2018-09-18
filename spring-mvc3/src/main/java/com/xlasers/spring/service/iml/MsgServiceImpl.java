package com.xlasers.spring.service.iml;

import com.xlasers.spring.service.MsgService;
import org.springframework.stereotype.Service;

/**
 * The type Msg service.
 *
 * @package: com.xlasers.spring.service.iml
 * @author: Elijah.D
 * @time: CreateAt 2018/9/21 && 17:35
 * @description:
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@Service
public class MsgServiceImpl implements MsgService {
    /**
     * Gets msg.
     *
     * @return the msg
     */
    @Override
    public String getMsg() {
        return "Hello Spring !";
    }
}
