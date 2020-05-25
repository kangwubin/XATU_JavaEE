package com.projects.modular.api.service.impl;

import com.projects.core.common.page.LayuiPageFactory;
import com.projects.core.common.page.LayuiPageInfo;

import com.projects.modular.api.entity.Area;
import com.projects.modular.api.mapper.AreaMapper;
import com.projects.modular.api.model.params.AreaParam;
import com.projects.modular.api.model.result.AreaResult;
import  com.projects.modular.api.service.AreaService;
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
 * 区域信息 服务实现类
 * </p>
 *
 * @author demo
 * @since 2020-04-03
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {

    @Override
    public void add(AreaParam param){
        Area entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(AreaParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(AreaParam param){
        Area oldEntity = getOldEntity(param);
        Area newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public AreaResult findBySpec(AreaParam param){
        return null;
    }

    @Override
    public List<AreaResult> findListBySpec(AreaParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(AreaParam param){
        Page pageContext = getPageContext();
        QueryWrapper<Area> objectQueryWrapper = new QueryWrapper<>();
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(AreaParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Area getOldEntity(AreaParam param) {
        return this.getById(getKey(param));
    }

    private Area getEntity(AreaParam param) {
        Area entity = new Area();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
