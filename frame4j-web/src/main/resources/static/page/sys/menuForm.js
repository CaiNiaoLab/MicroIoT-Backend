layui.use(['form','layer'],function(){
	var form = layui.form
	layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery;

	form.on("submit(addMenu)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
         $.post("/sys/menu/save",{
        	 'id' : $("#id").val(),  //id
			 'name' : $(".name").val(),  //菜单名称
			 'href' : $(".href").val(),  //菜单链接
			 'icon' : $(".icon").val(),  //菜单图标
			 'isShow' : $(".isShow").val(),  //菜单显示
			 'permission' : $(".permission").val(),  //权限标识
			 'type' : $(".type").val(),  //菜单类型
        	 'sort' : $(".sort").val(),  //排序号
        	 'isNewCreate' : $("#isNewCreate").val(),  //是否为新创建
        	 'remarks' : $(".remarks").val()  //备注
         },function(data){
        	 if(data.status == 200){
        		 top.layer.close(index);
        		 if($("#isNewCreate").val() == 'true'){
        			 top.layer.msg("菜单添加成功！");
        		 }else{
        			 top.layer.msg("菜单修改成功！");
        		 }
                 layer.closeAll("iframe");
                 //刷新父页面
                 parent.location.reload();
        	 }else{
        		 top.layer.msg("错误信息:"+data.msg, {time:1000, icon:5, shift:6}, function(){});
        		 return false;
        	 }
         })
    })
})