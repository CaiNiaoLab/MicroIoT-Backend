layui.use(['form','layer','table','laytpl','element','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table,
        element = layui.element;

    //字典类型列表
    var tableIns = table.render({
        elem: '#dictCategoryList',
        url : 'data',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,50],
        limit : 10,
        id : "dictCategoryListTable",
        toolbar : '#dictCategoryListLeftBar',
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'name', title: '字典名称', minWidth:100, align:"center",templet:function(data){
                return '<a href="#" lay-event="edit">'+data.name+'</a>';
            }},
            {field: 'type', title: '字典类型', minWidth:200, align:'center',templet:function(data){
                return '<a href="#" lay-event="dict">'+data.type+'</a>';
            }},
            {field: 'sort', title: '排序', align:'center', sort: true},
            {title: '操作', minWidth:75, templet:'#dictCategoryListBar',fixed:"right",align:"center"}
        ]]
    });

    //搜索
    $(".search").on("click",function(){
    	table.reload("dictCategoryListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {	//搜索的关键字
                name: $("#name").val(),  
                type: $(".type").val()
            }
        });
    });

    //添加，编辑
    function addDictCategory(edit){
        var index = layui.layer.open({
            title : (edit)?"编辑字典类型":"添加字典类型",
            type : 2,
            content : "form",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                	body.find("#id").val(edit.id);  //区域名称
                    body.find(".name").val(edit.name);  //区域名称
                    body.find(".code").val(edit.code);  //区域编码
                    body.find(".type").val(edit.type);  //区域类型
                    body.find(".sort").val(edit.sort);  //区域类型
                    body.find("#isNewCreate").val('false');  //是否为新创建
                    body.find(".remarks").val(edit.remarks);    //备注
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回字典类别列表', '.layui-layer-setwin .layui-layer-close', {
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
    
    //添加，编辑
    function dictList(icon,title,src){
    	//如果不存在子级
		if($(this).siblings().length == 0){
			top.element.tabAdd("bodyTab", {
			  title: "<i class='layui-icon'></i><cite>"+title+"</cite><i class='layui-icon layui-unselect layui-tab-close' data-id='1'>&#x1006;</i>"
			  ,content: "<iframe src='"+src+"' data-id='1'></frame>"
			  ,id: 'dictList'
			});  
		}
    }
    
    //添加
    $(".add").click(function(){
    	addDictCategory();
    })

    //批量删除
    $(".delAll").click(function(){
        var checkStatus = table.checkStatus('dictCategoryListTable'),
            data = checkStatus.data,
            ids = [];
        if(data.length > 0) {
            for (var i in data) {
            	ids.push(data[i].id);
            }
            ids = ids.join(",");
            layer.confirm('确定删除选中的字典类别？', {icon: 3, title: '提示信息'}, function (index) {
                $.get(encodeURI("deleteAll?ids="+ids),{
                      //将需要删除的ids作为参数传入
                 },function(data){
                	 if(data.status == 200){
                		 layer.msg("字典类型删除成功！");
                         tableIns.reload();
                         layer.close(index);
                	 }else{
                		 layer.msg("字典类型删除失败,错误信息:"+data.msg, {time:3000, icon:5, shift:6}, function(){});
                		 return false;
                	 }
                 })
            })
        }else{
            layer.msg("请选择需要删除的字典类别");
        }
    })

    //列表操作
    table.on('tool(dictCategoryList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
        	addDictCategory(data);
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此字典类别？',{icon:3, title:'提示信息'},function(index){
                 $.get("delete",{
                     id : data.id  //将需要删除的id作为参数传入
                 },function(data){
                	 if(data.status == 200){
                		 layer.msg("字典类型删除成功！");
                         tableIns.reload();
                         layer.close(index);
                	 }else{
                		 layer.msg("字典类型删除失败,错误信息:"+data.msg, {time:3000, icon:5, shift:6}, function(){});
                		 return false;
                	 }
                 })
            });
        }else if(layEvent === 'dict'){ //删除
        	var src = $(this).attr("data-url")+"?dictCategoryId="+data.id;
        	var title = $(this).attr("title");
        	var icon = $(this).attr("data-icon");
        	dictList(icon,title,src);
        }
    });

})
