package cn.fh.vertx.vo;

import cn.fh.vertx.dao.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by wanghongfei on 2019-03-27.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private Long userId;
    private String username;

    public static UserVo fromModel(UserModel model) {
        return new UserVo(model.getUserId(), model.getUsername());
    }
}
