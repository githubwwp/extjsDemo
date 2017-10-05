Ext.onReady(function() {

    // 树模型
    var treeModel = Ext.define('User', {
        extend : 'Ext.data.Model',
        fields : [ 'menuId', 'pmenuId', 'menuName', 'menuUrl', 'leaf', 'level', 'menuRemark', 'order' ]
    });

    // 菜单store
    var menuStore = Ext.create('Ext.data.Store', {
        model : treeModel,
        proxy : {
            type : 'ajax',
            url : contextPath + '/test/getAllMenu.do',
            reader : {
                type : 'json',
                root : 'menus'
            }
        }
    });

    // 加载菜单menu
    menuStore.load({
        callback : function() {
            console.log(menuStore);
            console.log(menuStore.getCount());
        }
    });

    // 树节点配置[root>>children]每个节点有： text显示文本, leaf是否是叶子, expanded是否展开，等属性。
    var treeStore = Ext.create('Ext.data.TreeStore', {
        fields : [ 'menuId', 'pmenuId', 'menuName', 'menuUrl', 'leaf', 'level', 'menuRemark', 'order', 'text' ],
        proxy : {
            type : 'ajax',
            url : contextPath + '/test/getAllMenu.do',
            reader : {
                type : 'json',
                root : 'menus'
            }
        }
    });

    // 同步树store
    var synTreeStore = Ext.create('Ext.data.TreeStore', {
        fields : [ 'menuId', 'pmenuId', 'menuName', 'menuUrl', 'leaf', 'level', 'menuRemark', 'order', 'text' ],
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
        rootVisible : false,
        store : synTreeStore
    });

    // 页面显示
    Ext.create('Ext.container.Viewport', {
        layout : 'fit',
        items : [ treePanel ]
    });
});