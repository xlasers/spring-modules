package com.xlasers.spring;

/**
 * The type Msg service.
 *
 * @package: com.xlasers.spring
 * @author: Elijah.D
 * @time: CreateAt 2018/9/21 && 16:53
 * @description:
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
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
