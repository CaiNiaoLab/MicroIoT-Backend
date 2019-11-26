layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //数据权限列表
    var tableIns = table.render({
        elem: '#dataPermissionList',
        url : 'data',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,50],
        limit : 10,
        id : "dataPermissionListTable",
        toolbar : '#dataPermissionListLeftBar',
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'name', title: '数据权限名称', minWidth:100, align:"center",templet:function(data){
                return '<a href="#" lay-event="edit">'+data.name+'</a>';
            }},
            {field: 'dataCode', title: '数据权限编码', minWidth:200, align:'center'},
            {field: 'sort', title: '排序', align:'center', sort: true},
            {field: 'remarks', title: '备注', align:'center', sort: true},
            {title: '操作', minWidth:75, templet:'#dataPermissionListBar',fixed:"right",align:"center"}
        ]]
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search").on("click",function(){
    	table.reload("dataPermissionListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {	//搜索的关键字
                remarks: $("#remarks").val()
            }
        });
    });

    //添加数据权限
    function addRoleCategory(edit){
        var index = layui.layer.open({
            title : (edit)?"编辑数据权限":"添加数据权限",
            type : 2,
            content : "form",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                	body.find("#id").val(edit.id);  //数据权限名称
                    body.find(".name").val(edit.name);  //数据权限名称
                    body.find(".dataCode").val(edit.dataCode);  //数据权限编码
                    body.find(".sort").val(edit.sort);  //数据权限类型
                    body.find("#isNewCreate").val('false');  //是否为新创建
                    body.find(".remarks").val(edit.remarks);    //备注
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回数据权限列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视数据权限（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }
    $(".add").click(function(){
    	addRoleCategory();
    })

    //批量删除
    $(".delAll").click(function(){
        var checkStatus = table.checkStatus('dataPermissionListTable'),
            data = checkStatus.data,
            ids = [];
        if(data.length > 0) {
            for (var i in data) {
            	ids.push(data[i].id);
            }
            ids = ids.join(",");
            layer.confirm('确定删除选中的数据权限？', {icon: 3, title: '提示信息'}, function (index) {
                $.get(encodeURI("deleteAll?ids="+ids),{
                      //将需要删除的ids作为参数传入
                 },function(data){
                	 if(data.status == 200){
                		 layer.msg("数据权限删除成功！");
                         tableIns.reload();
                         layer.close(index);
                	 }else{
                		 layer.msg("数据权限删除失败,错误信息:"+data.msg, {time:3000, icon:5, shift:6}, function(){});
                		 return false;
                	 }
                 })
            })
        }else{
            layer.msg("请选择需要删除的数据权限");
        }
    })

    //列表操作
    table.on('tool(dataPermissionList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
        	addRoleCategory(data);
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此数据权限？',{icon:3, title:'提示信息'},function(index){
                 $.get("delete",{
                     id : data.id  //将需要删除的id作为参数传入
                 },function(data){
                	 if(data.status == 200){
                		 layer.msg("数据权限删除成功！");
                         tableIns.reload();
                         layer.close(index);
                	 }else{
                		 layer.msg("数据权限删除失败,错误信息:"+data.msg, {time:3000, icon:5, shift:6}, function(){});
                		 return false;
                	 }
                 })
            });
        }
    });

})
