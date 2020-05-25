/**
 * 详情对话框
 */
var GroundInfoDlg = {
    data: {
        code: "",
        areaId: "",
        chang: "",
        kuan: "",
        mianji: "",
        mapLaction: "",
        haiba: "",
        tuzhi: "",
        createTime: "",
        userId: ""
    }
};

ayui.use(['form', 'admin', 'ax', 'selectPlus'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var selectPlus = layui.selectPlus;
    //让当前iframe弹层高度适应
    admin.iframeAuto();
    var ajax = new $ax(Feng.ctxPath + "/registerUser/list", function (data) {
        //初始化多选
        var mycars = new Array()
        var test = selectPlus.render({
            el: '#multiSelect',
            data: data.data,
            valueName: "name",
            values: mycars,
            valueSeparator: " - "
        });
        selectPlus.on('selectPlus(multiSelect)', function (obj) {
            console.log(obj.checked); //当前是否选中状态
            console.log(obj.values); //选中的数据
            console.log(obj.checkedData); //选中的相关数据
            console.log(obj.isAll); //是否是全选
            console.log(obj.ele); //点击的DOM
            var checkedDatas = obj.checkedData;
            var vals = "";
            for (i = 0; i < checkedDatas.length; i++) {
                vals += checkedDatas[i].id + ",";
            }


            $("#multiSelectHidden").val(vals);
        });
        var form = layui.form;
        form.render();
    }, function (data) {
        Feng.error("查询失败！" + data.responseJSON.message)
    });
    ajax.start();
    var quAjax = new $ax(Feng.ctxPath + "/area/list", function (data) {
        //初始化多选
        var mycars = new Array()
        var test = selectPlus.render({
            el: '#quyuSelect',
            data: data.data,
            valueName: "areaName",
            values: mycars,
            valueSeparator: " - "
        });
        selectPlus.on('selectPlus(quyuSelect)', function (obj) {
            console.log(obj.checked); //当前是否选中状态
            console.log(obj.values); //选中的数据
            console.log(obj.checkedData); //选中的相关数据
            console.log(obj.isAll); //是否是全选
            console.log(obj.ele); //点击的DOM
            var checkedDatas = obj.checkedData;
            var vals = "";
            for (i = 0; i < checkedDatas.length; i++) {
                vals += checkedDatas[i].id + ",";
            }
            $("#quyuSelectHidden").val(vals);
        });
        var form = layui.form;
        form.render();
    }, function (data) {
        Feng.error("查询失败！" + data.responseJSON.message)
    });
    quAjax.start();
    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/ground/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    form.val('groundForm', result.data);
    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/ground/editItem", function (data) {
            Feng.success("更新成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });
});