package com.projects.modular.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.api.entity.Fix;
import com.projects.modular.api.model.params.FixParam;
import com.projects.modular.api.model.result.FixResult;

import java.util.List;


/**
 * <p>
 * 报修 服务类
 * </p>
 *
 * @author demo
 * @since 2020-03-20
 */
public interface FixService extends IService<Fix> {

    /**
     * 新增
     *
     * @author demo
     * @Date 2020-03-20
     */
    void add(FixParam param);

    /**
     * 删除
     *
     * @author demo
     * @Date 2020-03-20
     */
    void delete(FixParam param);

    /**
     * 更新
     *
     * @author demo
     * @Date 2020-03-20
     */
    void update(FixParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author demo
     * @Date 2020-03-20
     */
    FixResult findBySpec(FixParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author demo
     * @Date 2020-03-20
     */
    List<FixResult> findListBySpec(FixParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author demo
     * @Date 2020-03-20
     */
    LayuiPageInfo findPageBySpec(FixParam param);

}
