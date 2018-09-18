package com.xlasers.spring.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.extern.slf4j.Slf4j;

/**
 * The type My generator.
 *
 * @package: com.xlasers.spring.generator
 * @author: Elijah.D
 * @time: CreateAt 2018/9/26 && 9:16
 * @description: generator
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@Slf4j
public class MyGenerator {
    /**
     * <p>
     * MySQL 生成演示
     * </p>
     *
     *
     * <p>策略配置 {@link StrategyConfig}
     * <p>生成策略 {@link NamingStrategy}
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        AutoGenerator generator = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("C:\\Users\\65734\\Desktop\\Code\\spring-modules\\spring-boot4\\src\\test\\java");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columnList
        gc.setBaseColumnList(false);
        // 自定义文件命名，不设置默认为,%s自动填充表实体属性!
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setAuthor("Elijah.D");

        // 数据源配置
        DataSourceConfig dataConfig = new DataSourceConfig();
        dataConfig.setDbType(DbType.MYSQL);

        //setTypeConvert 数据库字段类型转换,由于mybatis-plus升级原因,暂时未知情况??
        dataConfig.setDriverName("com.mysql.jdbc.Driver");
        dataConfig.setUsername("root");
        dataConfig.setPassword("1234");
        dataConfig.setUrl("jdbc:mysql://127.0.0.1:3306/fast_ren?characterEncoding=utf8");
        generator.setDataSource(dataConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("sys_user");
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.xlasers.mybatis");
        packageConfig.setModuleName("");

        generator.setGlobalConfig(gc);
        generator.setStrategy(strategy);
        generator.setPackageInfo(packageConfig);

        // 执行生成
        generator.execute();
    }
}