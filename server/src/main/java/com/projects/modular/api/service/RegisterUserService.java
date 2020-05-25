package com.projects.modular.api.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.api.entity.RegisterUser;
import com.projects.modular.api.model.params.RegisterUserParam;
import com.projects.modular.api.model.result.RegisterUserResult;

/**
 * <p>
 * 注册用户 服务类
 * </p>
 *
 * @author demo
 * @since 2019-07-29
 */
public interface RegisterUserService extends IService<RegisterUser> {

    /**
     * 新增
     *
     * @author demo
     * @Date 2019-07-29
     */
    void add(RegisterUserParam param);

    /**
     * 删除
     *
     * @author demo
     * @Date 2019-07-29
     */
    void delete(RegisterUserParam param);

    /**
     * 更新
     *
     * @author demo
     * @Date 2019-07-29
     */
    void update(RegisterUserParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author demo
     * @Date 2019-07-29
     */
    RegisterUserResult findBySpec(RegisterUserParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author demo
     * @Date 2019-07-29
     * @return
     */
    List<RegisterUser> findListBySpec(RegisterUserParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author demo
     * @Date 2019-07-29
     */
     LayuiPageInfo findPageBySpec(RegisterUserParam param);

    void changePwd(String oldPassword, String newPassword, Long userId);
}
