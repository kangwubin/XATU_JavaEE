layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 农药信息管理
     */
    var Nongyao = {
        tableId: "nongyaoTable"
    };

    /**
     * 初始化表格的列
     */
    Nongyao.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'name', sort: true, title: ''},
            {field: 'time', sort: true, title: '施肥时间'},
            {field: 'flat', sort: true, title: '肥料重量'},
            {field: 'type', sort: true, title: '0是底肥，1是追肥'},
            {field: 'content', sort: true, title: '方法'},
            {field: 'userId', sort: true, title: ''},
            {field: 'createTime', sort: true, title: '创建时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Nongyao.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(Nongyao.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    Nongyao.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加农药信息',
            content: Feng.ctxPath + '/nongyao/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Nongyao.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Nongyao.exportExcel = function () {
        var checkRows = table.checkStatus(Nongyao.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    Nongyao.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改农药信息',
            content: Feng.ctxPath + '/nongyao/edit?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Nongyao.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Nongyao.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/nongyao/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Nongyao.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Nongyao.tableId,
        url: Feng.ctxPath + '/nongyao/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Nongyao.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Nongyao.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Nongyao.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Nongyao.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Nongyao.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Nongyao.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Nongyao.onDeleteItem(data);
        }
    });
});
