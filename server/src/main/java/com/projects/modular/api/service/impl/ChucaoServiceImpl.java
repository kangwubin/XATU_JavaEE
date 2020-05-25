package com.projects.modular.api.service.impl;


import com.projects.core.common.page.LayuiPageFactory;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.api.entity.Chucao;
import com.projects.modular.api.mapper.ChucaoMapper;
import com.projects.modular.api.model.params.ChucaoParam;
import com.projects.modular.api.model.result.ChucaoResult;
import  com.projects.modular.api.service.ChucaoService;
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
 * 除草信息 服务实现类
 * </p>
 *
 * @author demo
 * @since 2020-04-03
 */
@Service
public class ChucaoServiceImpl extends ServiceImpl<ChucaoMapper, Chucao> implements ChucaoService {

    @Override
    public void add(ChucaoParam param){
        Chucao entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(ChucaoParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(ChucaoParam param){
        Chucao oldEntity = getOldEntity(param);
        Chucao newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ChucaoResult findBySpec(ChucaoParam param){
        return null;
    }

    @Override
    public List<ChucaoResult> findListBySpec(ChucaoParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(ChucaoParam param){
        Page pageContext = getPageContext();
        QueryWrapper<Chucao> objectQueryWrapper = new QueryWrapper<>();
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(ChucaoParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Chucao getOldEntity(ChucaoParam param) {
        return this.getById(getKey(param));
    }

    private Chucao getEntity(ChucaoParam param) {
        Chucao entity = new Chucao();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
