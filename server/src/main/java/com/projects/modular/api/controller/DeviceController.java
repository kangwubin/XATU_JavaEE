package com.projects.modular.api.controller;

import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.api.entity.Device;
import com.projects.modular.api.model.params.DeviceParam;
import com.projects.modular.api.service.DeviceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;


/**
 * 设备控制器
 *
 * @author demo
 * @Date 2020-04-14 17:57:00
 */
@Controller
@RequestMapping("/device")
public class DeviceController extends BaseController {

    private String PREFIX = "/modular/device";

    @Autowired
    private DeviceService deviceService;

    /**
     * 跳转到主页面
     *
     * @author demo
     * @Date 2020-04-14
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/device.html";
    }

    /**
     * 新增页面
     *
     * @author demo
     * @Date 2020-04-14
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/device_add.html";
    }

    /**
     * 编辑页面
     *
     * @author demo
     * @Date 2020-04-14
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/device_edit.html";
    }

    /**
     * 新增接口
     *
     * @author demo
     * @Date 2020-04-14
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(DeviceParam deviceParam) {
        this.deviceService.add(deviceParam);
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
    public ResponseData editItem(DeviceParam deviceParam) {
        this.deviceService.update(deviceParam);
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
    public ResponseData delete(DeviceParam deviceParam) {
        this.deviceService.delete(deviceParam);
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
    public ResponseData detail(DeviceParam deviceParam) {
        Device detail = this.deviceService.getById(deviceParam.getId());
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
    public LayuiPageInfo list(DeviceParam deviceParam) {
        return this.deviceService.findPageBySpec(deviceParam);
    }

}


