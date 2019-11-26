layui.use(['form','layer'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    form.on("submit(addUser)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
         $.post("/sys/area/save",{
        	 id : $("#id").val(),  //id
        	 name : $(".name").val(),  //区域名称
        	 code : $(".code").val(),  //区域编码
        	 types : $(".types").val(),  //区域类型
        	 /*createDate : createDate,*/
        	 remarks : $(".remarks").text(),  //备注
         },function(data){
        	 if(data.status == 200){
        		 top.layer.close(index);
                 top.layer.msg("用户添加成功！");
                 layer.closeAll("iframe");
                 //刷新父页面
                 parent.location.reload();
        	 }else{
        		 layer.msg(data.msg, {time:2000, icon:5, shift:6}, function(){});
        		 return false;
        	 }
         })
    })

    //格式化时间
    function filterTime(val){
        if(val < 10){
            return "0" + val;
        }else{
            return val;
        }
    }
    //定时发布
    var time = new Date();
    var createDate = time.getFullYear()+'-'+filterTime(time.getMonth()+1)+'-'+filterTime(time.getDate())+' '+filterTime(time.getHours())+':'+filterTime(time.getMinutes())+':'+filterTime(time.getSeconds());

})