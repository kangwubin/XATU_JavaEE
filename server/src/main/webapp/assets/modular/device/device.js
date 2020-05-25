layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 设备管理
     */
    var Device = {
        tableId: "deviceTable"
    };

    /**
     * 初始化表格的列
     */
    Device.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'name', sort: true, title: '设备名称'},
            {field: 'description', sort: true, title: '设备备注'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'status', sort: true, title: '状态'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Device.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(Device.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    Device.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加设备',
            content: Feng.ctxPath + '/device/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Device.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Device.exportExcel = function () {
        var checkRows = table.checkStatus(Device.tableId);
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
    Device.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改设备',
            content: Feng.ctxPath + '/device/edit?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Device.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Device.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/device/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Device.tableId);
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
        elem: '#' + Device.tableId,
        url: Feng.ctxPath + '/device/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Device.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Device.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Device.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Device.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Device.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Device.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Device.onDeleteItem(data);
        }
    });
});
