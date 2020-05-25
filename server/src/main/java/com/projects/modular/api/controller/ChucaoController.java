package com.projects.modular.api.controller;


import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.api.entity.Chucao;
import com.projects.modular.api.model.params.ChucaoParam;
import com.projects.modular.api.service.ChucaoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 除草信息控制器
 *
 * @author demo
 * @Date 2020-04-03 19:15:58
 */
@Controller
@RequestMapping("/chucao")
public class ChucaoController extends BaseController {

    private String PREFIX = "/modular/chucao";

    @Autowired
    private ChucaoService chucaoService;

    /**
     * 跳转到主页面
     *
     * @author demo
     * @Date 2020-04-03
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/chucao.html";
    }

    /**
     * 新增页面
     *
     * @author demo
     * @Date 2020-04-03
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/chucao_add.html";
    }

    /**
     * 编辑页面
     *
     * @author demo
     * @Date 2020-04-03
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/chucao_edit.html";
    }

    /**
     * 新增接口
     *
     * @author demo
     * @Date 2020-04-03
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ChucaoParam chucaoParam) {
        this.chucaoService.add(chucaoParam);
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
    public ResponseData editItem(ChucaoParam chucaoParam) {
        this.chucaoService.update(chucaoParam);
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
    public ResponseData delete(ChucaoParam chucaoParam) {
        this.chucaoService.delete(chucaoParam);
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
    public ResponseData detail(ChucaoParam chucaoParam) {
        Chucao detail = this.chucaoService.getById(chucaoParam.getId());
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
    public LayuiPageInfo list(ChucaoParam chucaoParam) {
        return this.chucaoService.findPageBySpec(chucaoParam);
    }

}


