package com.projects.modular.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.api.entity.Device;
import com.projects.modular.api.model.params.DeviceParam;
import com.projects.modular.api.model.result.DeviceResult;

import java.util.List;

/**
 * <p>
 * 设备 服务类
 * </p>
 *
 * @author demo
 * @since 2020-04-14
 */
public interface DeviceService extends IService<Device> {

    /**
     * 新增
     *
     * @author demo
     * @Date 2020-04-14
     */
    void add(DeviceParam param);

    /**
     * 删除
     *
     * @author demo
     * @Date 2020-04-14
     */
    void delete(DeviceParam param);

    /**
     * 更新
     *
     * @author demo
     * @Date 2020-04-14
     */
    void update(DeviceParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author demo
     * @Date 2020-04-14
     */
    DeviceResult findBySpec(DeviceParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author demo
     * @Date 2020-04-14
     */
    List<DeviceResult> findListBySpec(DeviceParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author demo
     * @Date 2020-04-14
     */
     LayuiPageInfo findPageBySpec(DeviceParam param);

}
