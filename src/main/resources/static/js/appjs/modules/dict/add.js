$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});


function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/modules/dict/save",
		data : $('#signupForm').serialize(), // 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("网络超时");
		},
		success : function(data) {
			console.log(data);
			if (data.state === 0) {
				parent.layer.msg(data.msg);
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}, parentId: {
				required: true
			}, value: {
				required: true
			}, type: {
				required: true
			}, description: {
				required: true
			}
		},
		messages : {
			name : {
				required: icon + "请输入字典标签名"
			}, type: {
				required: icon + "请输入字典类型"
			}, value: {
				required: icon + "请输入数据值"
			}, description: {
				required: icon + "请输入字段描述"
			}, parentId: {
				required: icon + "父级字典值缺失"
			}
		}
	})
}