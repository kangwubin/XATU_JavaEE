layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 除草信息管理
     */
    var Chucao = {
        tableId: "chucaoTable"
    };

    /**
     * 初始化表格的列
     */
    Chucao.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'gid', sort: true, title: '农田id'},
            {field: 'time', sort: true, title: '除草时间'},
            {field: 'descriton', sort: true, title: '除草方法'},
            {field: 'userId', sort: true, title: ''},
            {field: 'createTime', sort: true, title: '创建时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Chucao.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(Chucao.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    Chucao.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加除草信息',
            content: Feng.ctxPath + '/chucao/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Chucao.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Chucao.exportExcel = function () {
        var checkRows = table.checkStatus(Chucao.tableId);
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
    Chucao.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改除草信息',
            content: Feng.ctxPath + '/chucao/edit?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Chucao.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Chucao.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/chucao/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Chucao.tableId);
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
        elem: '#' + Chucao.tableId,
        url: Feng.ctxPath + '/chucao/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Chucao.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Chucao.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Chucao.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Chucao.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Chucao.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Chucao.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Chucao.onDeleteItem(data);
        }
    });
});
