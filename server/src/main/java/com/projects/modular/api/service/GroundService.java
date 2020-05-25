package com.projects.modular.api.service;

import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.api.entity.Ground;
import com.projects.modular.api.model.params.GroundParam;
import com.projects.modular.api.model.result.GroundResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 农田表 服务类
 * </p>
 *
 * @author demo
 * @since 2020-04-03
 */
public interface GroundService extends IService<Ground> {

    /**
     * 新增
     *
     * @author demo
     * @Date 2020-04-03
     */
    void add(GroundParam param);

    /**
     * 删除
     *
     * @author demo
     * @Date 2020-04-03
     */
    void delete(GroundParam param);

    /**
     * 更新
     *
     * @author demo
     * @Date 2020-04-03
     */
    void update(GroundParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author demo
     * @Date 2020-04-03
     */
    GroundResult findBySpec(GroundParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author demo
     * @Date 2020-04-03
     */
    List<GroundResult> findListBySpec(GroundParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author demo
     * @Date 2020-04-03
     */
     LayuiPageInfo findPageBySpec(GroundParam param);

}
