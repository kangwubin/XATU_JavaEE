package com.projects.modular.api.service.impl;

import com.projects.core.common.page.LayuiPageFactory;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.api.entity.Nongyao;
import com.projects.modular.api.mapper.NongyaoMapper;
import com.projects.modular.api.model.params.NongyaoParam;
import com.projects.modular.api.model.result.NongyaoResult;
import  com.projects.modular.api.service.NongyaoService;
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
 * 农药信息 服务实现类
 * </p>
 *
 * @author demo
 * @since 2020-04-03
 */
@Service
public class NongyaoServiceImpl extends ServiceImpl<NongyaoMapper, Nongyao> implements NongyaoService {

    @Override
    public void add(NongyaoParam param){
        Nongyao entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(NongyaoParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(NongyaoParam param){
        Nongyao oldEntity = getOldEntity(param);
        Nongyao newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public NongyaoResult findBySpec(NongyaoParam param){
        return null;
    }

    @Override
    public List<NongyaoResult> findListBySpec(NongyaoParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(NongyaoParam param){
        Page pageContext = getPageContext();
        QueryWrapper<Nongyao> objectQueryWrapper = new QueryWrapper<>();
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(NongyaoParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Nongyao getOldEntity(NongyaoParam param) {
        return this.getById(getKey(param));
    }

    private Nongyao getEntity(NongyaoParam param) {
        Nongyao entity = new Nongyao();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
