package cn.fh.vertx.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by wanghongfei on 2019-03-27.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VertxResponse {
    private int code;
    private Object data;
}
