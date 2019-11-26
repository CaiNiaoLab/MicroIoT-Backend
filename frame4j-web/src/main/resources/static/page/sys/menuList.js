layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //菜单列表
    var tableIns = table.render({
        elem: '#menuList',
        url : 'data',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,50],
        limit : 10,
        id : "menuListTable",
        toolbar : '#menuListLeftBar',
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'name', title: '菜单名称', minWidth:200, align:'center'},
            {field: 'href', title: '菜单链接', minWidth:200, align:'center'},
            {field: 'icon', title: '菜单图标', minWidth:200, align:'center'},
            {field: 'isShow', title: '菜单显示', minWidth:200, align:'center'},
            {field: 'permission', title: '权限标识', minWidth:200, align:'center'},
            {field: 'type', title: '菜单类型', minWidth:200, align:'center'},
            {field: 'sort', title: '排序', align:'center', sort: true},
            {title: '操作', minWidth:150, templet:'#menuListBar',fixed:"right",align:"center"}
        ]]
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search").on("click",function(){
    	table.reload("menuListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {	//搜索的关键字
                remarks: $("#remarks").val()
            }
        });
    });

    //添加菜单
    function addMenuCategory(edit){
        var index = layui.layer.open({
            title : (edit)?"编辑菜单":"添加菜单",
            type : 2,
            content : "form",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                	body.find("#id").val(edit.id);  //菜单名称
                    body.find(".loginName").val(edit.loginName);  //登录名

                    body.find(".sort").val(edit.sort);  //菜单类型
                    body.find("#isNewCreate").val('false');  //是否为新创建
                    body.find(".remarks").val(edit.remarks);    //备注
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回菜单列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视菜单（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }
    $(".add").click(function(){
    	addMenuCategory();
    })

    //批量删除
    $(".delAll").click(function(){
        var checkStatus = table.checkStatus('menuListTable'),
            data = checkStatus.data,
            ids = [];
        if(data.length > 0) {
            for (var i in data) {
            	ids.push(data[i].id);
            }
            ids = ids.join(",");
            layer.confirm('确定删除选中的菜单？', {icon: 3, title: '提示信息'}, function (index) {
                $.get(encodeURI("deleteAll?ids="+ids),{
                      //将需要删除的ids作为参数传入
                 },function(data){
                	 if(data.status == 200){
                		 layer.msg("菜单删除成功！");
                         tableIns.reload();
                         layer.close(index);
                	 }else{
                		 layer.msg("菜单删除失败,错误信息:"+data.msg, {time:3000, icon:5, shift:6}, function(){});
                		 return false;
                	 }
                 })
            })
        }else{
            layer.msg("请选择需要删除的菜单");
        }
    })

    //列表操作
    table.on('tool(menuList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
        	addMenuCategory(data);
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此菜单？',{icon:3, title:'提示信息'},function(index){
                 $.get("delete",{
                     id : data.id  //将需要删除的id作为参数传入
                 },function(data){
                	 if(data.status == 200){
                		 layer.msg("菜单删除成功！");
                         tableIns.reload();
                         layer.close(index);
                	 }else{
                		 layer.msg("菜单删除失败,错误信息:"+data.msg, {time:3000, icon:5, shift:6}, function(){});
                		 return false;
                	 }
                 })
            });
        }
    });

})
