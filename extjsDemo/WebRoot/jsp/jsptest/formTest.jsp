<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<jsp:include page="/jsp/basejsp/extJsp.jsp"></jsp:include>

</head>
<body>

</body>
<script type="text/javascript">
    Ext.onReady(function() {
        var testForm = Ext.create('Ext.form.Panel', {
            title : 'Simple Form',
            bodyPadding : 5,
            // 将会通过 AJAX 请求提交到此URL
            url : 'save-form.php',

            // 表单域 Fields 将被竖直排列, 占满整个宽度
            layout : 'anchor',
            defaults : {
                anchor : '100%'
            },

            // The fields
            defaultType : 'textfield',
            items : [ {
                fieldLabel : 'First Name',
                name : 'first',
                allowBlank : false
            }, {
                fieldLabel : 'Last Name',
                name : 'last',
                allowBlank : false
            }, {
                fieldLabel : 'Last Name',
                name : 'last',
                allowBlank : false
            }, {
                fieldLabel : 'Last Name',
                name : 'last',
                allowBlank : false
            }, {
                fieldLabel : 'Last Name',
                name : 'last',
                allowBlank : false
            } ],

            // 重置 和 保存 按钮.
            buttons : [ {
                text : '重置',
                handler : function() {
                    this.up('form').getForm().reset();
                }
            }, {
                text : '保存',
                formBind : true, //only enabled once the form is valid
                disabled : true,
                handler : function() {
                    var form = this.up('form').getForm();
                    if (form.isValid()) {
                        form.submit({
                            success : function(form, action) {
                                Ext.Msg.alert('保存成功', action.result.msg);
                            },
                            failure : function(form, action) {
                                Ext.Msg.alert('操作失败', action.result.msg);
                            }
                        });
                    }
                }
            } ]
        });

        Ext.create('Ext.container.Viewport', {
            layout : 'anchor',
            autoScroll: true,
            items : [ 
                testForm,
                testForm,
                testForm,
                testForm
             ]
        });
    });
</script>
</html>
