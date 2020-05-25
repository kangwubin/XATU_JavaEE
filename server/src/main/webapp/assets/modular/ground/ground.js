layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 农田表管理
     */
    var Ground = {
        tableId: "groundTable"
    };

    /**
     * 初始化表格的列
     */
    Ground.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'code', sort: true, title: '农田编号'},
            {field: 'areaId', sort: true, title: ''},
            {field: 'chang', sort: true, title: '长'},
            {field: 'kuan', sort: true, title: '宽'},
            {field: 'mianji', sort: true, title: '面积'},
            {field: 'mapLaction', sort: true, title: '经纬度'},
            {field: 'haiba', sort: true, title: '海拔'},
            {field: 'tuzhi', sort: true, title: '土质'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'userId', sort: true, title: ''},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Ground.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(Ground.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    Ground.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加农田表',
            content: Feng.ctxPath + '/ground/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Ground.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Ground.exportExcel = function () {
        var checkRows = table.checkStatus(Ground.tableId);
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
    Ground.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改农田表',
            content: Feng.ctxPath + '/ground/edit?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Ground.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Ground.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/ground/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Ground.tableId);
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
        elem: '#' + Ground.tableId,
        url: Feng.ctxPath + '/ground/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Ground.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Ground.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Ground.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Ground.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Ground.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Ground.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Ground.onDeleteItem(data);
        }
    });
});
