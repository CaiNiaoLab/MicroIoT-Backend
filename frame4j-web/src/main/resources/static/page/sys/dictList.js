layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //字典数据列表
    var tableIns = table.render({
        elem: '#dictList',
        url : 'data'+"?dictCategory.id="+$("#dictCategoryId").val(),
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,50],
        limit : 10,
        id : "dictListTable",
        toolbar : '#dictListLeftBar',
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'skey', title: '字典key', minWidth:100, align:"center",templet:function(data){
                return '<a href="#" lay-event="edit">'+data.skey+'</a>';
            }},
            {field: 'svalue', title: '字典value', minWidth:200, align:'center'},
            {field: 'dictCategory.type', title: '字典类型', minWidth:200, align:'center',templet:function(data){
                return data.dictCategory.type;
            }},
            {field: 'sort', title: '排序', align:'center', sort: true},
            {field: 'remarks', title: '描述', align:'center'},
            {title: '操作', minWidth:75, templet:'#dictListBar',fixed:"right",align:"center"}
        ]]
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search").on("click",function(){
    	table.reload("dictListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {	//搜索的关键字
                remarks: $("#remarks").val()
            }
        });
    });

    //添加区域
    function addDictCategory(edit){
        var index = layui.layer.open({
            title : (edit)?"编辑字典数据":"添加字典数据",
            type : 2,
            content : "form",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                	body.find("#id").val(edit.id);  //区域名称
                    body.find(".skey").val(edit.skey);  //区域名称
                    body.find(".svalue").val(edit.svalue);  //区域编码
                    body.find(".dictCategoryType").val(edit.dictCategory.type);  //区域类型
                    body.find(".sort").val(edit.sort);  //区域类型
                    body.find("#isNewCreate").val('false');  //是否为新创建
                    body.find(".remarks").val(edit.remarks);    //备注
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回字典数据列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }
    $(".add").click(function(){
    	addDictCategory();
    })

    //批量删除
    $(".delAll").click(function(){
        var checkStatus = table.checkStatus('dictListTable'),
            data = checkStatus.data,
            ids = [];
        if(data.length > 0) {
            for (var i in data) {
            	ids.push(data[i].id);
            }
            ids = ids.join(",");
            layer.confirm('确定删除选中的字典数据？', {icon: 3, title: '提示信息'}, function (index) {
                $.get(encodeURI("deleteAll?ids="+ids),{
                      //将需要删除的ids作为参数传入
                 },function(data){
                	 if(data.status == 200){
                		 layer.msg("字典数据删除成功！");
                         tableIns.reload();
                         layer.close(index);
                	 }else{
                		 layer.msg("字典数据删除失败,错误信息:"+data.msg, {time:3000, icon:5, shift:6}, function(){});
                		 return false;
                	 }
                 })
            })
        }else{
            layer.msg("请选择需要删除的字典数据");
        }
    })

    //列表操作
    table.on('tool(dictList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
        	addDictCategory(data);
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此字典数据？',{icon:3, title:'提示信息'},function(index){
                 $.get("delete",{
                     id : data.id  //将需要删除的id作为参数传入
                 },function(data){
                	 if(data.status == 200){
                		 layer.msg("字典数据删除成功！");
                         tableIns.reload();
                         layer.close(index);
                	 }else{
                		 layer.msg("字典数据删除失败,错误信息:"+data.msg, {time:3000, icon:5, shift:6}, function(){});
                		 return false;
                	 }
                 })
            });
        }
    });

})
