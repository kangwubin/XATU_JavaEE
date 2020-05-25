package com.projects.modular.comment.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 评价
 * </p>
 *
 * @author demo
 * @since 2020-01-07
 */
@Data
public class CommentParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long commentId;

    /**
     * 评价人
     */
    private String name;

    /**
     * 评价时间
     */
    private Date createTime;

    /**
     * 评价内容
     */
    private String content;
    
    private Long lostId;

    @Override
    public String checkParam() {
        return null;
    }

}
