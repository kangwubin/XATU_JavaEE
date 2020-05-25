package com.projects.modular.api.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 校园公告
 * </p>
 *
 * @author demo
 * @since 2019-08-03
 */
@Data
public class NoticeParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 文本
     */
    private String content;

    /**
     * 发布时间
     */
    private Date createTime;

    @Override
    public String checkParam() {
        return null;
    }

}
