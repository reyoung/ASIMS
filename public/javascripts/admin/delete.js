define(function(){
	function Delete(address){
		$("button.btn.btn-danger").click(function(){
			var idToDelete = $(this).attr("data-id")
	        var host = location.host;
	    	var delete_url = "http://" + host + address + idToDelete;
	    	console.log("Delete URL "+delete_url)
			$.ajaxSetup({
	        	type: "DELETE",
	        	async: false
	        }); 
	        var responseResult = $.ajax({url:delete_url}).responseText;
	        if(responseResult == "false"){
	        	alert("由于外键关系，无法删除。");
	        }
	        else{
	        	window.location.reload();
	        }
		});
	}
	return Delete;
})