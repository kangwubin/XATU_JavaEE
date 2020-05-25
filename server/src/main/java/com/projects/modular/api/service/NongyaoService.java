package com.projects.modular.api.service;

import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.api.entity.Nongyao;
import com.projects.modular.api.model.params.NongyaoParam;
import com.projects.modular.api.model.result.NongyaoResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 农药信息 服务类
 * </p>
 *
 * @author demo
 * @since 2020-04-03
 */
public interface NongyaoService extends IService<Nongyao> {

    /**
     * 新增
     *
     * @author demo
     * @Date 2020-04-03
     */
    void add(NongyaoParam param);

    /**
     * 删除
     *
     * @author demo
     * @Date 2020-04-03
     */
    void delete(NongyaoParam param);

    /**
     * 更新
     *
     * @author demo
     * @Date 2020-04-03
     */
    void update(NongyaoParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author demo
     * @Date 2020-04-03
     */
    NongyaoResult findBySpec(NongyaoParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author demo
     * @Date 2020-04-03
     */
    List<NongyaoResult> findListBySpec(NongyaoParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author demo
     * @Date 2020-04-03
     */
     LayuiPageInfo findPageBySpec(NongyaoParam param);

}
