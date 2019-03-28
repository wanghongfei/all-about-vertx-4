package cn.fh.vertx;

import cn.fh.vertx.service.UserService;
import cn.fh.vertx.vo.UserVo;
import cn.fh.vertx.vo.VertxResponse;
import com.alibaba.fastjson.JSON;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by wanghongfei on 2019-03-27.
 */
@Component
@Slf4j
public class UserHandler implements Handler<RoutingContext> {
    @Autowired
    private UserService userService;

    @Override
    public void handle(RoutingContext ctx) {
        // 取出参数
        String username = ctx.request().params().get("username");
        // 检查参数
        if (StringUtils.isEmpty(username)) {
            endResponse(buildResponse(-1, null), ctx);
            return;
        }

        // 构造Future
        Future<VertxResponse> dbFut = Future.future();
        // 在worker线程中执行block代码
        ctx.vertx().executeBlocking(
                fut -> {
                    UserVo user = userService.findUser(username);
                    fut.complete(buildResponse(0, user));
                },
                dbFut.completer()
        );

        // dbFut完成之后会调用此方法
        dbFut.setHandler(res -> {
            // 先判断是否发生了异常
            if (!res.succeeded()) {
                Throwable err = res.cause();
                endResponse(buildResponse(1, err.getMessage()), ctx);
                return;
            }

            log.info("send response: {}", res.result());
            endResponse(res.result(), ctx);
        });

    }

    private VertxResponse buildResponse(int code, Object data) {
        return new VertxResponse(code, data);
    }

    private void endResponse(Object dataObj, RoutingContext ctx) {
        ctx.response().putHeader("Content-Type", "application/json;charset=utf8");

        String json = JSON.toJSONString(dataObj);
        ctx.response().end(json);
    }
}
