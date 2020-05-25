layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 危害管理
     */
    var Weihai = {
        tableId: "weihaiTable"
    };

    /**
     * 初始化表格的列
     */
    Weihai.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'name', sort: true, title: '害虫名称'},
            {field: 'time', sort: true, title: ''},
            {field: 'buwei', sort: true, title: '危害部位'},
            {field: 'zhengzhuang', sort: true, title: '症状'},
            {field: 'sunshi', sort: true, title: '损失'},
            {field: 'yaoName', sort: true, title: '农药名称'},
            {field: 'yaoFlat', sort: true, title: '农药用量'},
            {field: 'yaoMethod', sort: true, title: '农药方法'},
            {field: 'yaoTime', sort: true, title: '农药时间'},
            {field: 'yaoXiaoguo', sort: true, title: '农药效果'},
            {field: 'userId', sort: true, title: '创建人id'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Weihai.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(Weihai.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    Weihai.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加危害',
            content: Feng.ctxPath + '/weihai/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Weihai.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Weihai.exportExcel = function () {
        var checkRows = table.checkStatus(Weihai.tableId);
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
    Weihai.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改危害',
            content: Feng.ctxPath + '/weihai/edit?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Weihai.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Weihai.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/weihai/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Weihai.tableId);
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
        elem: '#' + Weihai.tableId,
        url: Feng.ctxPath + '/weihai/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Weihai.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Weihai.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Weihai.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Weihai.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Weihai.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Weihai.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Weihai.onDeleteItem(data);
        }
    });
});
