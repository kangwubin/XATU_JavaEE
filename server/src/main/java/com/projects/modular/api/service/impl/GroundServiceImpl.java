package com.projects.modular.api.service.impl;

import com.projects.core.common.page.LayuiPageFactory;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.api.entity.Ground;
import com.projects.modular.api.mapper.GroundMapper;
import com.projects.modular.api.model.params.GroundParam;
import com.projects.modular.api.model.result.GroundResult;
import  com.projects.modular.api.service.GroundService;
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
 * 农田表 服务实现类
 * </p>
 *
 * @author demo
 * @since 2020-04-03
 */
@Service
public class GroundServiceImpl extends ServiceImpl<GroundMapper, Ground> implements GroundService {

    @Override
    public void add(GroundParam param){
        Ground entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(GroundParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(GroundParam param){
        Ground oldEntity = getOldEntity(param);
        Ground newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public GroundResult findBySpec(GroundParam param){
        return null;
    }

    @Override
    public List<GroundResult> findListBySpec(GroundParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(GroundParam param){
        Page pageContext = getPageContext();
        QueryWrapper<Ground> objectQueryWrapper = new QueryWrapper<>();
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(GroundParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Ground getOldEntity(GroundParam param) {
        return this.getById(getKey(param));
    }

    private Ground getEntity(GroundParam param) {
        Ground entity = new Ground();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
