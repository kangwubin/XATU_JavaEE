package com.projects.modular.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.api.entity.Area;
import com.projects.modular.api.model.params.AreaParam;
import com.projects.modular.api.model.result.AreaResult;

import java.util.List;


/**
 * <p>
 * 区域信息 服务类
 * </p>
 *
 * @author demo
 * @since 2020-04-03
 */
public interface AreaService extends IService<Area> {

    /**
     * 新增
     *
     * @author demo
     * @Date 2020-04-03
     */
    void add(AreaParam param);

    /**
     * 删除
     *
     * @author demo
     * @Date 2020-04-03
     */
    void delete(AreaParam param);

    /**
     * 更新
     *
     * @author demo
     * @Date 2020-04-03
     */
    void update(AreaParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author demo
     * @Date 2020-04-03
     */
    AreaResult findBySpec(AreaParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author demo
     * @Date 2020-04-03
     */
    List<AreaResult> findListBySpec(AreaParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author demo
     * @Date 2020-04-03
     */
     LayuiPageInfo findPageBySpec(AreaParam param);

}
