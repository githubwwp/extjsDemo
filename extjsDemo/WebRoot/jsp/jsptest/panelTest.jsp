<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>panelTest</title>

<jsp:include page="/jsp/basejsp/extJsp.jsp"></jsp:include>

</head>
<body>

</body>
<script>
	

    var prdDepartNameStore = Ext.create('Ext.data.Store', {
        fields : [ 'value', 'text' ],
        data : [ {
            "value" : "软件",
            "text" : "软件"
        }, {
            "value" : "信息产品",
            "text" : "信息产品"
        }, {
            "value" : "系统集成",
            "text" : "系统集成"
        } ]
    });

    Ext.onReady(function() {
        Ext.define('User', {
            extend : 'Ext.data.Model',
            fields : [ {
                name : 'name',
                type : 'string'
            }, {
                name : 'age',
                type : 'int'
            }, {
                name : 'phone',
                type : 'string'
            }, {
                name : 'gender',
                type : 'string'
            }, {
                name : 'username',
                type : 'string'
            }, {
                name : 'alive',
                type : 'boolean',
                defaultValue : true
            } ]
        });
        user1 = Ext.create('User', {
            name : 'aa',
            age : '323',
            tt : 'ew'
        });

        Ext.create('Ext.panel.Panel', {
            bodyPadding : 5, // Don't want content to crunch against the borders
            title : 'Filters',
            items : [ {
                xtype : 'datefield',
                fieldLabel : 'Start date'
            }, {
                xtype : 'datefield',
                fieldLabel : 'End date'
            } ],
            renderTo : Ext.getBody()
        });
    });
</script>

</html>