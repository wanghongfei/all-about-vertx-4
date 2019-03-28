package cn.fh.vertx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.fh.vertx.dao")
public class MybatisConfig {
}
