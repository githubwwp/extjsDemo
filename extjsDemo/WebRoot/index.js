Ext.onReady(function() {

    // 同步树store
    var synTreeStore = Ext.create('Ext.data.TreeStore', {
        fields : [ 'menuId', 'pmenuId', 'menuName', 'menu_url', 'leaf', 'level', 'menu_remark', 'order', 'text' ],
        proxy : {
            type : 'ajax',
            url : contextPath + '/menu/getAllTreeMenus.do',
            reader : {
                type : 'json'
            }
        },
        root : {
            text : 'root',
            expanded : true
        }
    });

    // 创建TreePanel rootVisible表示根节点是否显示
    var treePanel = Ext.create('Ext.tree.Panel', {
        id : 'navTree',
        border : null,
        el : 'treePanel',
        animate : true,
        autoScroll : true,
        autoHeight : false,
        rootVisible : false,
        store : synTreeStore,
        listeners : {
            'itemclick' : itemClickFn
        }
    });
    treePanel.expandAll();

    Ext.create('Ext.container.Viewport', {
        layout : 'border',
        items : [ {
            id : 'north',
            region : 'north',
            border : false,
            margins : '0 0 5 0',
            title : 'top',
            tbar : [ {
                xtype : 'button',
                text : '添加选项卡',
                handler : addTab
            }, {
                xtype : 'button',
                text : '添加百度页面',
                handler : addBaiDu
            }, {
                text : '添加 google',
                handler : addGoogle
            }, {
                xtype : 'button',
                text : 'model test',
                handler : modelTest
            }, {
                xtype : 'button',
                text : '目录树',
                handler : showTree
            } ]

        }, {
            id : 'west',
            region : 'west',
            collapsible : true,
            title : 'Navigation',
            layout : 'fit',
            autoScroll : true,
            width : 150,
            items : [ treePanel ]
        // could use a TreePanel or AccordionLayout for navigational items
        }, {
            id : 'south',
            region : 'south',
            title : 'South Panel',
            collapsible : true,
            html : 'Information goes here',
            split : true,
            height : 100,
            minHeight : 100
        }, {
            id : 'east',
            region : 'east',
            title : 'East Panel',
            collapsible : true,
            split : true,
            width : 150
        }, {
            id : 'center',
            region : 'center',
            xtype : 'tabpanel', // TabPanel itself has no title
            activeTab : 0, // First tab active by default
            items : {
                title : 'Default Tab',
                html : 'The first tab\'s content. Others may be added dynamically'
            }
        } ]
    });
});

function addTab() {
    var panel = Ext.create('Ext.panel.Panel', {
        bodyPadding : 5, // Don't want content to crunch against the borders
        closable : true,
        title : 'Filters',
        items : [ {
            xtype : 'datefield',
            fieldLabel : 'Start date'
        }, {
            xtype : 'datefield',
            fieldLabel : 'End date'
        } ]
    });
    var centerTabs = Ext.getCmp("center");
    centerTabs.add(panel);
}

// 添加百度
function addBaiDu() {
    var url = "http://www.baidu.com";
    var panel = Ext.create('Ext.panel.Panel', {
        bodyPadding : 5, // Don't want content to crunch against the borders
        closable : true,
        title : 'baidu ',
        html : "<iframe name='baiduIframe' src='" + url + "' style='overflow:auto; width:100%; height:100%;' frameborder='0'></iframe>"
    });
    var centerTabs = Ext.getCmp("center");
    centerTabs.add(panel);
}

// 添加google
function addGoogle() {
    var url = "https://www.google.com";
    var panel = Ext.create('Ext.panel.Panel', {
        bodyPadding : 5, // Don't want content to crunch against the borders
        closable : true,
        title : 'google ',
        html : "<iframe name='google' src='" + url + "' style='overflow:auto; width:100%; height:100%;' frameborder='0'></iframe>"
    });
    var centerTabs = Ext.getCmp("center");
    centerTabs.add(panel);
}

// 动态添加
function addCenterTab(title, url) {

    // title 或 url 为空， 提示错误
    if (!title || !url) {
        Ext.Msg.alert("提示：", "地址不存在");
        return;
    }

    var centerTabs = Ext.getCmp("center");
    var tabId = title + "_id";
    var tab = centerTabs.getComponent(tabId);

    // 如果panel不存在，新建一个panel，并添加到tabPanel中。
    if (!tab) {
        tab = Ext.create('Ext.panel.Panel', {
            id : tabId,
            bodyPadding : 5,
            closable : true,
            title : title,
            html : "<iframe name='google' src='" + url + "' style='overflow:auto; width:100%; height:100%;' frameborder='0'></iframe>"
        });
        centerTabs.add(tab);
    }

    centerTabs.setActiveTab(tab); // 设置显示当前面板
}

// 显示树
function showTree() {
    addCenterTab('目录树', contextPath + '/jsp/jsptest/treeTest.jsp');
}

// model test
function modelTest() {
    // 建立数据模型
    var myModel = Ext.define('Company', {
        extend : 'Ext.data.Model',
        fields : [ {
            name : 'name',
            type : 'string'
        }, {
            name : 'addr',
            type : 'string'
        }, {
            name : 'tel',
            type : 'string'
        }, {
            name : 'isMarket',
            type : 'boolean'
        } ]
    });

    // store
    var testStore = Ext.create('Ext.data.Store', {
        model : myModel,
        proxy : {
            type : 'ajax',
            url : contextPath + '/test/model.do',
            reader : {
                type : 'json',
                root : 'companies'
            }
        }
    });
    // 加载数据
    testStore.load({
        scope : this,
        callback : function() {
            console.log(testStore.getCount());
        }
    });
}

// 导航点击函数
function itemClickFn(obj, record, item, index, e, eOptes) {
    var leaf = record.get('leaf');
    var text = record.get('text');
    var menu_url = record.get('menu_url');
    // 子叶才有url(如果以http 开头不做处理，否则加上项目根目录)
    if (leaf) {
        if (isInnerUrl(menu_url)) {
            menu_url = contextPath + menu_url;
        }
        console.log(menu_url);
        addCenterTab(text, menu_url);
    }
}

// 判断是否为项目内部url
function isInnerUrl(url) {
    var url = url + "";
    var grep = /^\//; // 验证是否以 ‘/’ 开头
    return grep.test(url);
}

/*
 * Ext.Ajax.request({ url : contextPath + '/test/model.do', method : 'post',
 * params : {}, success : function(rst) { var ss = Ext.decode(rst.responseText);
 * console.log(ss); for ( var i in rst) { console.log(i + ": " + rst[i] + ": " +
 * typeof (rst[i])); } } });
 */

