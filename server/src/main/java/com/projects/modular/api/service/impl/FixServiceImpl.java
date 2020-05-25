package com.projects.modular.api.service.impl;

import com.projects.core.common.page.LayuiPageFactory;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.api.entity.Fix;
import com.projects.modular.api.mapper.FixMapper;
import com.projects.modular.api.model.params.FixParam;
import com.projects.modular.api.model.result.FixResult;
import  com.projects.modular.api.service.FixService;
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
 * 报修 服务实现类
 * </p>
 *
 * @author demo
 * @since 2020-03-20
 */
@Service
public class FixServiceImpl extends ServiceImpl<FixMapper, Fix> implements FixService {

    @Override
    public void add(FixParam param){
        Fix entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(FixParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(FixParam param){
        Fix oldEntity = getOldEntity(param);
        Fix newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public FixResult findBySpec(FixParam param){
        return null;
    }

    @Override
    public List<FixResult> findListBySpec(FixParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(FixParam param){
        Page pageContext = getPageContext();
        QueryWrapper<Fix> objectQueryWrapper = new QueryWrapper<>();
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(FixParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Fix getOldEntity(FixParam param) {
        return this.getById(getKey(param));
    }

    private Fix getEntity(FixParam param) {
        Fix entity = new Fix();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
