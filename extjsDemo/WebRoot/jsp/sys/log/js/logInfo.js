Ext.onReady(function() {

    var pageSize = 5;

    var logStore = Ext.create('Ext.data.Store', {
        fields : [ 'id', 'account', 'userId', 'menu1', 'menu2', 'logDesc', 'resultCode', 'msg', 'exceptionInfo', 'params', 'createTime' ],
        pageSize : pageSize,
        proxy : {
            type : 'ajax',
            url : contextPath + '/log/getPageLog.do',
            reader : {
                type : 'json',
                root : 'logs',
                totalProperty : 'total'
            }
        }
    });

    logStore.load({
        params : {
            start : 0,
            limit : pageSize
        }
    });

    var logPageGrid = Ext.create('Ext.grid.Panel', {
        id : 'logPageGrid',
        store : logStore,
        columns : [ {
            header : 'id',
            dataIndex : 'id',
            width : 100
        }, {
            header : '账号',
            dataIndex : 'account',
            width : 100
        }, {
            header : '用户id',
            dataIndex : 'userId',
            width : 100
        }, {
            header : '类',
            dataIndex : 'menu1',
            width : 100
        }, {
            header : '方法',
            dataIndex : 'menu2',
            width : 100
        }, {
            header : '日志描述',
            dataIndex : 'logDesc',
            width : 100
        }, {
            header : '返回码',
            dataIndex : 'resultCode',
            width : 100
        }, {
            header : '返回信息',
            dataIndex : 'msg',
            width : 100
        }, {
            header : '异常信息',
            dataIndex : 'exceptionInfo',
            width : 100
        }, {
            header : '参数',
            dataIndex : 'params',
            width : 100
        }, {
            header : '创建时间',
            dataIndex : 'createTime',
            width : 100,
            renderer : function(val) {
                console.log(val);
                return Ext.Date.format(new Date(val.time), 'Y-m-d');
            }
        } ],
        dockedItems : [ {
            xtype : 'pagingtoolbar',
            store : logStore, // GridPanel中使用的数据
            dock : 'bottom',
            displayInfo : true
        } ],
    });

    var grid = Ext.create('Ext.grid.Panel', {
        id : 'logInfoGrid',
        title : 'log list',
        viewConfig : {
            forceFit : true,
            stripeRows : true
        // 在表格中显示斑马线
        },
        store : {
            fields : [ 'id', 'account', 'userId', 'menu1', 'menu2', 'logDesc', 'resultCode', 'msg', 'exceptionInfo', 'params', 'createTime' ]
        },
        columns : [ {
            header : 'id',
            dataIndex : 'id',
            width : 100
        }, {
            header : '账号',
            dataIndex : 'account',
            width : 100
        }, {
            header : '用户id',
            dataIndex : 'userId',
            width : 100
        }, {
            header : '类',
            dataIndex : 'menu1',
            width : 100
        }, {
            header : '方法',
            dataIndex : 'menu2',
            width : 100
        }, {
            header : '日志描述',
            dataIndex : 'logDesc',
            width : 100
        }, {
            header : '返回码',
            dataIndex : 'resultCode',
            width : 100
        }, {
            header : '返回信息',
            dataIndex : 'msg',
            width : 100
        }, {
            header : '异常信息',
            dataIndex : 'exceptionInfo',
            width : 100
        }, {
            header : '参数',
            dataIndex : 'params',
            width : 100
        }, {
            header : '创建时间',
            dataIndex : 'createTime',
            width : 100
        } ]
    });

    // 页面显示
    Ext.create('Ext.container.Viewport', {
        layout : 'fit',
        items : [ logPageGrid ]
    });

    // 异步加载数据
    /*
     * Ext.Ajax.request({ url : contextPath + '/log/getAll.do', method : 'post',
     * params : {}, success : function(rst) { var ss =
     * Ext.decode(rst.responseText);
     * Ext.getCmp('logInfoGrid').getStore().add(ss.logs); } });
     */

});