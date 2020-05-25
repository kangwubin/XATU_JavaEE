package com.projects.modular.comment.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.comment.entity.Comment;
import com.projects.modular.comment.model.params.CommentParam;
import com.projects.modular.comment.model.result.CommentResult;

/**
 * <p>
 * 评价 服务类
 * </p>
 *
 * @author demo
 * @since 2020-01-07
 */
public interface CommentService extends IService<Comment> {

    /**
     * 新增
     *
     * @author demo
     * @Date 2020-01-07
     */
    void add(CommentParam param);

    /**
     * 删除
     *
     * @author demo
     * @Date 2020-01-07
     */
    void delete(CommentParam param);

    /**
     * 更新
     *
     * @author demo
     * @Date 2020-01-07
     */
    void update(CommentParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author demo
     * @Date 2020-01-07
     */
    CommentResult findBySpec(CommentParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author demo
     * @Date 2020-01-07
     */
    List<CommentResult> findListBySpec(CommentParam param);
    
    List<Comment> getCommentList(Long lostId);

    /**
     * 查询分页数据，Specification模式
     *
     * @author demo
     * @Date 2020-01-07
     */
     LayuiPageInfo findPageBySpec(CommentParam param);

}
