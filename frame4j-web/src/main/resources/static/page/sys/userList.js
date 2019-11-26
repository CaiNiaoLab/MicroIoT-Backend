layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //用户列表
    var tableIns = table.render({
        elem: '#userList',
        url : 'data',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,50],
        limit : 10,
        id : "userListTable",
        toolbar : '#userListLeftBar',
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'loginName', title: '登录名', minWidth:100, align:"center",templet:function(data){
                return '<a href="#" lay-event="edit">'+data.loginName+'</a>';
            }},
            {field: 'password', title: '密码', minWidth:200, align:'center'},
            {field: 'nick', title: '昵称', minWidth:200, align:'center'},
            {field: 'email', title: '邮件', minWidth:200, align:'center'},
            {field: 'phone', title: '手机号', minWidth:200, align:'center'},
            {field: 'loginIp', title: '最后登录IP', minWidth:200, align:'center'},
            {field: 'loginDate', title: '最后登录时间', minWidth:200, align:'center'},
            {field: 'loginFlag', title: '是否允许登陆', minWidth:200, align:'center'},
            {field: 'photo', title: '头像', minWidth:200, align:'center'},
            {field: 'loginExceptionCount', title: '登录异常次数', minWidth:200, align:'center'},
            {field: 'passwordExpiredDate', title: '密码有效期', minWidth:200, align:'center'},
            {field: 'sort', title: '排序', align:'center', sort: true},
            {title: '操作', minWidth:150, templet:'#userListBar',fixed:"right",align:"center"}
        ]]
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search").on("click",function(){
    	table.reload("userListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {	//搜索的关键字
                remarks: $("#remarks").val()
            }
        });
    });

    //添加用户
    function addUserCategory(edit){
        var index = layui.layer.open({
            title : (edit)?"编辑用户":"添加用户",
            type : 2,
            content : "form",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                	body.find("#id").val(edit.id);  //用户名称
                    body.find(".loginName").val(edit.loginName);  //登录名
                    body.find(".password").val(edit.password);  //密码
                    body.find(".nick").val(edit.nick);  //昵称
                    body.find(".email").val(edit.email);  //邮件
                    body.find(".phone").val(edit.phone);  //手机号
                    body.find(".loginIp").val(edit.loginIp);  //最后登录IP
                    body.find(".loginDate").val(edit.loginDate);  //最后登录时间
                    body.find(".loginFlag").val(edit.loginFlag);  //是否允许登陆（0：正常；1：不允许；）
                    body.find(".photo").val(edit.photo);  //头像
                    body.find(".loginExceptionCount").val(edit.loginExceptionCount);  //登录异常次数
                    body.find(".passwordExpiredDate").val(edit.passwordExpiredDate);  //密码有效期
                    body.find(".roles").val(edit.roles);  //角色列表
                    body.find(".sort").val(edit.sort);  //用户类型
                    body.find("#isNewCreate").val('false');  //是否为新创建
                    body.find(".remarks").val(edit.remarks);    //备注
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视用户（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }
    $(".add").click(function(){
    	addUserCategory();
    })

    //批量删除
    $(".delAll").click(function(){
        var checkStatus = table.checkStatus('userListTable'),
            data = checkStatus.data,
            ids = [];
        if(data.length > 0) {
            for (var i in data) {
            	ids.push(data[i].id);
            }
            ids = ids.join(",");
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
                $.get(encodeURI("deleteAll?ids="+ids),{
                      //将需要删除的ids作为参数传入
                 },function(data){
                	 if(data.status == 200){
                		 layer.msg("用户删除成功！");
                         tableIns.reload();
                         layer.close(index);
                	 }else{
                		 layer.msg("用户删除失败,错误信息:"+data.msg, {time:3000, icon:5, shift:6}, function(){});
                		 return false;
                	 }
                 })
            })
        }else{
            layer.msg("请选择需要删除的用户");
        }
    })

    //列表操作
    table.on('tool(userList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
        	addUserCategory(data);
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此用户？',{icon:3, title:'提示信息'},function(index){
                 $.get("delete",{
                     id : data.id  //将需要删除的id作为参数传入
                 },function(data){
                	 if(data.status == 200){
                		 layer.msg("用户删除成功！");
                         tableIns.reload();
                         layer.close(index);
                	 }else{
                		 layer.msg("用户删除失败,错误信息:"+data.msg, {time:3000, icon:5, shift:6}, function(){});
                		 return false;
                	 }
                 })
            });
        }
    });

})
