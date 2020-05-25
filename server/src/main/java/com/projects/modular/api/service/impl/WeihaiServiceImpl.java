package com.projects.modular.api.service.impl;

import com.projects.core.common.page.LayuiPageFactory;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.api.entity.Weihai;
import com.projects.modular.api.mapper.WeihaiMapper;
import com.projects.modular.api.model.params.WeihaiParam;
import com.projects.modular.api.model.result.WeihaiResult;
import  com.projects.modular.api.service.WeihaiService;
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
 * 危害 服务实现类
 * </p>
 *
 * @author demo
 * @since 2020-04-03
 */
@Service
public class WeihaiServiceImpl extends ServiceImpl<WeihaiMapper, Weihai> implements WeihaiService {

    @Override
    public void add(WeihaiParam param){
        Weihai entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WeihaiParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WeihaiParam param){
        Weihai oldEntity = getOldEntity(param);
        Weihai newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WeihaiResult findBySpec(WeihaiParam param){
        return null;
    }

    @Override
    public List<WeihaiResult> findListBySpec(WeihaiParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WeihaiParam param){
        Page pageContext = getPageContext();
        QueryWrapper<Weihai> objectQueryWrapper = new QueryWrapper<>();
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WeihaiParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Weihai getOldEntity(WeihaiParam param) {
        return this.getById(getKey(param));
    }

    private Weihai getEntity(WeihaiParam param) {
        Weihai entity = new Weihai();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
