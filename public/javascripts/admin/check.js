define([],function(){
	function Check(str){
		var content = $(str).val();
		if (content == null || content.length == 0){
			return false;
		}
		else{
			return true;
		}
	}
	return Check;
})