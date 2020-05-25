package com.projects.modular.api.controller;

import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.api.entity.Ground;
import com.projects.modular.api.model.params.GroundParam;
import com.projects.modular.api.service.GroundService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 农田表控制器
 *
 * @author demo
 * @Date 2020-04-03 19:15:58
 */
@Controller
@RequestMapping("/ground")
public class GroundController extends BaseController {

    private String PREFIX = "/modular/ground";

    @Autowired
    private GroundService groundService;

    /**
     * 跳转到主页面
     *
     * @author demo
     * @Date 2020-04-03
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/ground.html";
    }

    /**
     * 新增页面
     *
     * @author demo
     * @Date 2020-04-03
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/ground_add.html";
    }

    /**
     * 编辑页面
     *
     * @author demo
     * @Date 2020-04-03
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/ground_edit.html";
    }

    /**
     * 新增接口
     *
     * @author demo
     * @Date 2020-04-03
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(GroundParam groundParam) {
        this.groundService.add(groundParam);
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
    public ResponseData editItem(GroundParam groundParam) {
        this.groundService.update(groundParam);
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
    public ResponseData delete(GroundParam groundParam) {
        this.groundService.delete(groundParam);
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
    public ResponseData detail(GroundParam groundParam) {
        Ground detail = this.groundService.getById(groundParam.getId());
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
    public LayuiPageInfo list(GroundParam groundParam) {
        return this.groundService.findPageBySpec(groundParam);
    }

}


