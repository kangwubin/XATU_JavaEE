layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 地图点位管理
     */
    var MapLog = {
        tableId: "mapLogTable"
    };

    /**
     * 初始化表格的列
     */
    MapLog.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'indexId', sort: true, title: '编号'},
            {field: 'latitude', sort: true, title: '经度'},
            {field: 'longitude', sort: true, title: '纬度'},
            {field: 'speed', sort: true, title: '速度'},
            {field: 'direction', sort: true, title: ''},
            {field: 'accuracy', sort: true, title: ''},
            {field: 'satellitesNum', sort: true, title: ''},
            {field: 'address', sort: true, title: '地址'},
            {field: 'city', sort: true, title: '城市'},
            {field: 'time', sort: true, title: ''},
            {field: 'deviceId', sort: true, title: '设备编号'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    MapLog.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(MapLog.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    MapLog.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加地图点位',
            content: Feng.ctxPath + '/mapLog/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(MapLog.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    MapLog.exportExcel = function () {
        var checkRows = table.checkStatus(MapLog.tableId);
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
    MapLog.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改地图点位',
            content: Feng.ctxPath + '/mapLog/edit?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(MapLog.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    MapLog.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/mapLog/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(MapLog.tableId);
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
        elem: '#' + MapLog.tableId,
        url: Feng.ctxPath + '/mapLog/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: MapLog.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        MapLog.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        MapLog.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        MapLog.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + MapLog.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            MapLog.openEditDlg(data);
        } else if (layEvent === 'delete') {
            MapLog.onDeleteItem(data);
        }
    });
});
