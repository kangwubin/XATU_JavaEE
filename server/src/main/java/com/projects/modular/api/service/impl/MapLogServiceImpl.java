package com.projects.modular.api.service.impl;


import com.projects.core.common.page.LayuiPageFactory;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.api.entity.MapLog;
import com.projects.modular.api.mapper.MapLogMapper;
import com.projects.modular.api.model.params.MapLogParam;
import com.projects.modular.api.model.result.MapLogResult;
import  com.projects.modular.api.service.MapLogService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 地图点位 服务实现类
 * </p>
 *
 * @author demo
 * @since 2020-04-14
 */
@Service
public class MapLogServiceImpl extends ServiceImpl<MapLogMapper, MapLog> implements MapLogService {

    @Override
    public void add(MapLogParam param){
        MapLog entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(MapLogParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(MapLogParam param){
        MapLog oldEntity = getOldEntity(param);
        MapLog newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public MapLogResult findBySpec(MapLogParam param){
        return null;
    }

    @Override
    public List<MapLogResult> findListBySpec(MapLogParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(MapLogParam param){
        Page pageContext = getPageContext();
        QueryWrapper<MapLog> objectQueryWrapper = new QueryWrapper<>();
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(MapLogParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private MapLog getOldEntity(MapLogParam param) {
        return this.getById(getKey(param));
    }

    private MapLog getEntity(MapLogParam param) {
        MapLog entity = new MapLog();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
