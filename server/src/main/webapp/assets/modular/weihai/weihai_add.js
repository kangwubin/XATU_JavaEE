/**
 * 详情对话框
 */
var WeihaiInfoDlg = {
    data: {
        name: "",
        time: "",
        buwei: "",
        zhengzhuang: "",
        sunshi: "",
        yaoName: "",
        yaoFlat: "",
        yaoMethod: "",
        yaoTime: "",
        yaoXiaoguo: "",
        userId: "",
        createTime: ""
    }
};

layui.use(['form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    //让当前iframe弹层高度适应
    admin.iframeAuto();

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/weihai/addItem", function (data) {
            Feng.success("添加成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });
});