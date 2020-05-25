package com.projects.modular.api.controller;

import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.api.entity.Weihai;
import com.projects.modular.api.model.params.WeihaiParam;
import com.projects.modular.api.service.WeihaiService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 危害控制器
 *
 * @author demo
 * @Date 2020-04-03 19:15:58
 */
@Controller
@RequestMapping("/weihai")
public class WeihaiController extends BaseController {

    private String PREFIX = "/modular/weihai";

    @Autowired
    private WeihaiService weihaiService;

    /**
     * 跳转到主页面
     *
     * @author demo
     * @Date 2020-04-03
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/weihai.html";
    }

    /**
     * 新增页面
     *
     * @author demo
     * @Date 2020-04-03
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/weihai_add.html";
    }

    /**
     * 编辑页面
     *
     * @author demo
     * @Date 2020-04-03
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/weihai_edit.html";
    }

    /**
     * 新增接口
     *
     * @author demo
     * @Date 2020-04-03
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WeihaiParam weihaiParam) {
        this.weihaiService.add(weihaiParam);
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
    public ResponseData editItem(WeihaiParam weihaiParam) {
        this.weihaiService.update(weihaiParam);
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
    public ResponseData delete(WeihaiParam weihaiParam) {
        this.weihaiService.delete(weihaiParam);
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
    public ResponseData detail(WeihaiParam weihaiParam) {
        Weihai detail = this.weihaiService.getById(weihaiParam.getId());
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
    public LayuiPageInfo list(WeihaiParam weihaiParam) {
        return this.weihaiService.findPageBySpec(weihaiParam);
    }

}


