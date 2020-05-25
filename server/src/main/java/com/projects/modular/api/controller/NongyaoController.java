package com.projects.modular.api.controller;

import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.api.entity.Nongyao;
import com.projects.modular.api.model.params.NongyaoParam;
import com.projects.modular.api.service.NongyaoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 农药信息控制器
 *
 * @author demo
 * @Date 2020-04-03 19:15:58
 */
@Controller
@RequestMapping("/nongyao")
public class NongyaoController extends BaseController {

    private String PREFIX = "/modular/nongyao";

    @Autowired
    private NongyaoService nongyaoService;

    /**
     * 跳转到主页面
     *
     * @author demo
     * @Date 2020-04-03
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/nongyao.html";
    }

    /**
     * 新增页面
     *
     * @author demo
     * @Date 2020-04-03
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/nongyao_add.html";
    }

    /**
     * 编辑页面
     *
     * @author demo
     * @Date 2020-04-03
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/nongyao_edit.html";
    }

    /**
     * 新增接口
     *
     * @author demo
     * @Date 2020-04-03
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(NongyaoParam nongyaoParam) {
        this.nongyaoService.add(nongyaoParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author demo
     * @Date 2020-04-03
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(NongyaoParam nongyaoParam) {
        this.nongyaoService.update(nongyaoParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author demo
     * @Date 2020-04-03
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(NongyaoParam nongyaoParam) {
        this.nongyaoService.delete(nongyaoParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author demo
     * @Date 2020-04-03
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(NongyaoParam nongyaoParam) {
        Nongyao detail = this.nongyaoService.getById(nongyaoParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author demo
     * @Date 2020-04-03
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(NongyaoParam nongyaoParam) {
        return this.nongyaoService.findPageBySpec(nongyaoParam);
    }

}


