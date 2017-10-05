Ext.onReady(function() {
    /**
     * 创建视图
     */
    Ext.create('Ext.container.Viewport', {
        layout : 'border',
        items : [ topPanel, leftPanel, centerPanel ]
    });
});

var store = Ext.create('Ext.data.TreeStore', {
    root : {
        expanded : false,// 默认不展开
        children : [ // 这里是树的节点 有层级的
        {
            text : "一级节点",
            leaf : true,
            id : 'firstNode',
            checked : false
        }, {
            text : "一级节点1",
            expanded : true,
            checked : false,
            children : [ {
                text : "二级节点",
                leaf : true,
                checked : false
            }, {
                text : "二级节点1",
                leaf : true,
                checked : false
            } ]
        }, {
            text : "无限加载子节点",
            leaf : false,
            checked : false
        } // leaf:false表示有下级节点
        ]
    }
});

// 导航树
var navTree = Ext.create('Ext.tree.Panel', {
    title : 'Simple Tree',
    id : 'tree',
    store : store
});

/**
 * 定义右侧面版
 */
Ext.define('mainTabPanel', {
    extend : 'Ext.tab.Panel',
    // 重写页面加载方法，在该方法中，定义一个iframe，用来装载JSP页面
    loadPage : function(url, id, title, icon, reload) {
        var tab = this.getComponent(id);
        debugger;
        if (tab) {
            this.setActiveTab(tab);
        } else {
            var p = this.add(new Ext.panel.Panel({
                id : id,
                title : title,
                closable : true,
                icon : icon,
                html : '<iframe src="' + url + '"width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>'

            }));
            this.setActiveTab(p);
        }
    }
});

/**
 * 创建顶部面板
 */
var topPanel = Ext.create('Ext.panel.Panel', {
    region : 'north',
    height : 55
});
/**
 * 定义顶左侧面板
 */
var leftPanel = Ext.create('Ext.panel.Panel', {
    region : 'west',
    title : '导航栏',
    width : 230,
    split : true,
    collapsible : true,
    items : [ navTree ]
});

/**
 * 创建中间面板
 */
var centerPanel = Ext.create('mainTabPanel', {
    region : 'center',
    layout : 'fit',
    tabWidth : 120,
    items : [ {
        title : '首页'
    } ]
});
