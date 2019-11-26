layui.use(['form','layer'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    form.on("submit(addRole)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
         $.post("/sys/role/save",{
        	 'id' : $("#id").val(),  //id
        	 'name' : $(".name").val(),  //角色名称
        	 'enname' : $(".enname").val(),  //角色编码
        	 'dataPermission.id' : $(".dataPermissionId").val(),  //数据权限
        	 'sort' : $(".sort").val(),  //排序号
        	 'isNewCreate' : $("#isNewCreate").val(),  //是否为新创建
        	 'remarks' : $(".remarks").val(),  //备注
         },function(data){
        	 if(data.status == 200){
        		 top.layer.close(index);
        		 if($("#isNewCreate").val() == 'true'){
        			 top.layer.msg("角色添加成功！");
        		 }else{
        			 top.layer.msg("角色修改成功！");
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