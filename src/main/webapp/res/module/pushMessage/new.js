$(function(){
	//表单订交
	$('#pushMessage_new').validator({
		submit:function(e){
			var _form = $('#pushMessage_new');
			if(this.isFormValid()){
				_form.find(".submit").attr("disabled",true);
				$.post(_form.attr("url"),_form.serialize(),function(data){
					_form.find(".submit").attr("disabled",false);
					console.log(data);
				},"json")
			}
			return false;
		}
	})
	//后续操作切换
})