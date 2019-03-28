package cn.fh.vertx.service;

import cn.fh.vertx.dao.UserModelMapper;
import cn.fh.vertx.dao.model.UserModel;
import cn.fh.vertx.dao.model.UserModelExample;
import cn.fh.vertx.error.BusinessException;
import cn.fh.vertx.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by wanghongfei on 2019-02-25.
 */
@Service
public class UserService {
    @Autowired
    private UserModelMapper userMapper;

    public UserVo findUser(String username) {
        UserModelExample example = new UserModelExample();
        example.createCriteria().andUsernameEqualTo(username);

        List<UserModel> users = userMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users)) {
            throw new BusinessException("user " + username + " not found");
        }

        UserModel user = users.get(0);

        return UserVo.fromModel(user);
    }
}
