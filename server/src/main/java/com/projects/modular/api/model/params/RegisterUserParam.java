package com.projects.modular.api.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;

import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 注册用户
 * </p>
 *
 * @author demo
 * @since 2019-07-29
 */
@Data
public class RegisterUserParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String userPass;

    /**
     * 姓名
     */
    private String name;
    /**
     * 图片
     */
    private String pic;

    /**
     * 创建时间
     */
    private Date createTime;
    private String headImage;
    private String phone;
    private String email;
    private String sex;

    @Override
    public String checkParam() {
        return null;
    }

}
