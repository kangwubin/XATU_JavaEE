package com.projects.modular.api.service;

import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.api.entity.Weihai;
import com.projects.modular.api.model.params.WeihaiParam;
import com.projects.modular.api.model.result.WeihaiResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 危害 服务类
 * </p>
 *
 * @author demo
 * @since 2020-04-03
 */
public interface WeihaiService extends IService<Weihai> {

    /**
     * 新增
     *
     * @author demo
     * @Date 2020-04-03
     */
    void add(WeihaiParam param);

    /**
     * 删除
     *
     * @author demo
     * @Date 2020-04-03
     */
    void delete(WeihaiParam param);

    /**
     * 更新
     *
     * @author demo
     * @Date 2020-04-03
     */
    void update(WeihaiParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author demo
     * @Date 2020-04-03
     */
    WeihaiResult findBySpec(WeihaiParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author demo
     * @Date 2020-04-03
     */
    List<WeihaiResult> findListBySpec(WeihaiParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author demo
     * @Date 2020-04-03
     */
     LayuiPageInfo findPageBySpec(WeihaiParam param);

}
