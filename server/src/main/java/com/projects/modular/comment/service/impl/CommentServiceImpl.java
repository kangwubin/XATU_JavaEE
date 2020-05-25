package com.projects.modular.comment.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projects.core.common.page.LayuiPageFactory;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.comment.entity.Comment;
import com.projects.modular.comment.mapper.CommentMapper;
import com.projects.modular.comment.model.params.CommentParam;
import com.projects.modular.comment.model.result.CommentResult;
import  com.projects.modular.comment.service.CommentService;

import cn.stylefeng.roses.core.util.ToolUtil;

/**
 * <p>
 * 评价 服务实现类
 * </p>
 *
 * @author demo
 * @since 2020-01-07
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public void add(CommentParam param){
        Comment entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(CommentParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(CommentParam param){
        Comment oldEntity = getOldEntity(param);
        Comment newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public CommentResult findBySpec(CommentParam param){
        return null;
    }

    @Override
    public List<CommentResult> findListBySpec(CommentParam param){
        return null;
    }

    
    
    
    @Override
	public List<Comment> getCommentList(Long lostId) {
    	 QueryWrapper<Comment> objectQueryWrapper = new QueryWrapper<>();
    	 objectQueryWrapper.eq("lost_id",lostId);
    	 List<Comment> list = baseMapper.selectList(objectQueryWrapper);
    	 
		return list;
	}

	@Override
    public LayuiPageInfo findPageBySpec(CommentParam param){
        Page pageContext = getPageContext();
        QueryWrapper<Comment> objectQueryWrapper = new QueryWrapper<>();
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(CommentParam param){
        return param.getCommentId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Comment getOldEntity(CommentParam param) {
        return this.getById(getKey(param));
    }

    private Comment getEntity(CommentParam param) {
        Comment entity = new Comment();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
