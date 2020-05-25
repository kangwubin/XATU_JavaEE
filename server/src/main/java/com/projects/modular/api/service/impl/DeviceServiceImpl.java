package com.projects.modular.api.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projects.core.common.page.LayuiPageFactory;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.api.entity.Device;
import com.projects.modular.api.mapper.DeviceMapper;
import com.projects.modular.api.model.params.DeviceParam;
import com.projects.modular.api.model.result.DeviceResult;
import com.projects.modular.api.service.DeviceService;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

import cn.stylefeng.roses.core.util.ToolUtil;

/**
 * <p>
 * 设备 服务实现类
 * </p>
 *
 * @author demo
 * @since 2020-04-14
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {

    @Override
    public void add(DeviceParam param) {
        Device entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(DeviceParam param) {
        this.removeById(getKey(param));
    }

    @Override
    public void update(DeviceParam param) {
        Device oldEntity = getOldEntity(param);
        Device newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public DeviceResult findBySpec(DeviceParam param) {
        return null;
    }

    @Override
    public List<DeviceResult> findListBySpec(DeviceParam param) {
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(DeviceParam param) {
        Page pageContext = getPageContext();
        QueryWrapper<Device> objectQueryWrapper = new QueryWrapper<>();
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(DeviceParam param) {
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Device getOldEntity(DeviceParam param) {
        return this.getById(getKey(param));
    }

    private Device getEntity(DeviceParam param) {
        Device entity = new Device();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
