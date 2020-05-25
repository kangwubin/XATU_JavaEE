package com.projects.modular.api.controller;

import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.api.entity.MapLog;
import com.projects.modular.api.model.params.MapLogParam;
import com.projects.modular.api.service.MapLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;


/**
 * 地图点位控制器
 *
 * @author demo
 * @Date 2020-04-14 17:57:00
 */
@Controller
@RequestMapping("/mapLog")
public class MapLogController extends BaseController {

    private String PREFIX = "/modular/mapLog";

    @Autowired
    private MapLogService mapLogService;

    /**
     * 跳转到主页面
     *
     * @author demo
     * @Date 2020-04-14
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/mapLog.html";
    }

    /**
     * 新增页面
     *
     * @author demo
     * @Date 2020-04-14
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/mapLog_add.html";
    }

    /**
     * 编辑页面
     *
     * @author demo
     * @Date 2020-04-14
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/mapLog_edit.html";
    }

    /**
     * 新增接口
     *
     * @author demo
     * @Date 2020-04-14
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(MapLogParam mapLogParam) {
        this.mapLogService.add(mapLogParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author demo
     * @Date 2020-04-14
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(MapLogParam mapLogParam) {
        this.mapLogService.update(mapLogParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author demo
     * @Date 2020-04-14
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(MapLogParam mapLogParam) {
        this.mapLogService.delete(mapLogParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author demo
     * @Date 2020-04-14
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(MapLogParam mapLogParam) {
        MapLog detail = this.mapLogService.getById(mapLogParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author demo
     * @Date 2020-04-14
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(MapLogParam mapLogParam) {
        return this.mapLogService.findPageBySpec(mapLogParam);
    }

}


