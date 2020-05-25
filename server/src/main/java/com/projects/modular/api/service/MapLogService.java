package com.projects.modular.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.api.entity.MapLog;
import com.projects.modular.api.model.params.MapLogParam;
import com.projects.modular.api.model.result.MapLogResult;

import java.util.List;

/**
 * <p>
 * 地图点位 服务类
 * </p>
 *
 * @author demo
 * @since 2020-04-14
 */
public interface MapLogService extends IService<MapLog> {

    /**
     * 新增
     *
     * @author demo
     * @Date 2020-04-14
     */
    void add(MapLogParam param);

    /**
     * 删除
     *
     * @author demo
     * @Date 2020-04-14
     */
    void delete(MapLogParam param);

    /**
     * 更新
     *
     * @author demo
     * @Date 2020-04-14
     */
    void update(MapLogParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author demo
     * @Date 2020-04-14
     */
    MapLogResult findBySpec(MapLogParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author demo
     * @Date 2020-04-14
     */
    List<MapLogResult> findListBySpec(MapLogParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author demo
     * @Date 2020-04-14
     */
     LayuiPageInfo findPageBySpec(MapLogParam param);

}
