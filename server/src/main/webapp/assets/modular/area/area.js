layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 区域信息管理
     */
    var Area = {
        tableId: "areaTable"
    };

    /**
     * 初始化表格的列
     */
    Area.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'areaName', sort: true, title: '区域名称'},
            {field: 'daqi', sort: true, title: '大气情况'},
            {field: 'jiangshui', sort: true, title: '降水情况'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Area.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(Area.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    Area.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加区域信息',
            content: Feng.ctxPath + '/area/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Area.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Area.exportExcel = function () {
        var checkRows = table.checkStatus(Area.tableId);
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
    Area.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改区域信息',
            content: Feng.ctxPath + '/area/edit?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Area.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Area.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/area/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Area.tableId);
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
        elem: '#' + Area.tableId,
        url: Feng.ctxPath + '/area/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Area.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Area.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Area.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Area.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Area.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Area.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Area.onDeleteItem(data);
        }
    });
});
