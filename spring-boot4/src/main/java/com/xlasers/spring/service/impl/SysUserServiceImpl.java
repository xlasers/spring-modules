package com.xlasers.spring.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlasers.spring.annotation.DataSource;
import com.xlasers.spring.mapper.SysUserMapper;
import com.xlasers.spring.model.SysUser;
import com.xlasers.spring.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.xlasers.spring.constant.DataSourceNames.SECOND;

/**
 * The type Sys user service.
 *
 * @package: com.xlasers.spring.service.impl
 * @author: Elijah.D
 * @time: CreateAt 2018/10/2 && 22:26
 * @description: 系统用户, 服务实现类
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    /**
     * Trans: 建议手动回滚3种方式(去掉rollbackFor = Exception.class,查看编译提示)
     */
    @Transactional(rollbackFor = Exception.class)
    public void trans() {
        log.info("【事务控制 ???】");
        log.info("【事务控制 ???】");
    }

    /**
     * 获取user信息: first db
     *
     * @param id the id
     * @return
     */
    @Override
    public SysUser getUserByIdOfFirstDb(long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 获取user信息: second db
     *
     * <p>带有注解@DataSource,被aop切入动态切换数据源
     *
     * @param id the id
     * @return
     */
    @Override
    @DataSource(name = SECOND)
    public SysUser getUserByIdOfSecondDb(long id) {
        trans();
        return baseMapper.selectById(id);
    }

    /**
     * 获取user信息: first & second db
     *
     * @param id the id
     * @return
     */
    @Override
    public SysUser getUserByIdOfFirstAndSecond(long id) {

        SysUser userOne = getUserByIdOfFirstDb(id);
        log.info("【FirstDb】获取user信息,user: {}", userOne);

        SysUser userTwo = getUserByIdOfSecondDb(id);
        log.info("【SecondDb】获取user信息,user: {}", userTwo);

        return null;
    }
}
