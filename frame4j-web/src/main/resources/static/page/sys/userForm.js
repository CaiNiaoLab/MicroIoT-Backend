layui.use(['form','layer'],function(){
	var form = layui.form
	layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery;

	form.on("submit(addUser)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
         $.post("/sys/user/save",{
        	 'id' : $("#id").val(),  //id
        	 'loginName' : $(".loginName").val(),  //登录名
			 'password' : $(".password").val(),  //密码
			 'nick' : $(".nick").val(),  //昵称
			 'email' : $(".email").val(),  //邮件
			 'phone' : $(".phone").val(),  //手机号
			 'loginIp' : $(".loginIp").val(),  //最后登录IP
			 'loginDate' : $(".loginDate").val(),  //最后登录时间
			 'loginFlag' : $(".loginFlag").val(),  //是否允许登陆（0：正常；1：不允许；）
			 'photo' : $(".photo").val(),  //头像
			 'loginExceptionCount' : $(".loginExceptionCount").val(),  //登录异常次数
			 'passwordExpiredDate' : $(".passwordExpiredDate").val(),  //密码有效期
			 //'roles' : $(".roles").val(),  //角色列表
        	 'sort' : $(".sort").val(),  //排序号
        	 'isNewCreate' : $("#isNewCreate").val(),  //是否为新创建
        	 'remarks' : $(".remarks").val()  //备注
         },function(data){
        	 if(data.status == 200){
        		 top.layer.close(index);
        		 if($("#isNewCreate").val() == 'true'){
        			 top.layer.msg("用户添加成功！");
        		 }else{
        			 top.layer.msg("用户修改成功！");
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