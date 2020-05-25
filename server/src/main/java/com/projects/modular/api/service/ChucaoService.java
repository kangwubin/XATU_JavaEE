package com.projects.modular.api.service;

import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.api.entity.Chucao;
import com.projects.modular.api.model.params.ChucaoParam;
import com.projects.modular.api.model.result.ChucaoResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 除草信息 服务类
 * </p>
 *
 * @author demo
 * @since 2020-04-03
 */
public interface ChucaoService extends IService<Chucao> {

    /**
     * 新增
     *
     * @author demo
     * @Date 2020-04-03
     */
    void add(ChucaoParam param);

    /**
     * 删除
     *
     * @author demo
     * @Date 2020-04-03
     */
    void delete(ChucaoParam param);

    /**
     * 更新
     *
     * @author demo
     * @Date 2020-04-03
     */
    void update(ChucaoParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author demo
     * @Date 2020-04-03
     */
    ChucaoResult findBySpec(ChucaoParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author demo
     * @Date 2020-04-03
     */
    List<ChucaoResult> findListBySpec(ChucaoParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author demo
     * @Date 2020-04-03
     */
     LayuiPageInfo findPageBySpec(ChucaoParam param);

}
