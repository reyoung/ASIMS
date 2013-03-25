require(["admin/active"],function (act){
    $(function (){
        act("nav_airCompany_list")
        $("button.btn.btn-danger").click(function(){
        	var data_id = $(this).attr("data-id");
        	var host = location.host;
        	var delete_url = "http://" + host + "/admin/AirCompany/" + data_id;
        	$.ajaxSetup({
        		type: "DELETE",
        		async: false
        	}); 
        	var responseResult = $.ajax({url:delete_url}).responseText;
        	if(responseResult){
        		 window.location.reload();
        	}
        	else{
        		
        	}
        });
    })
})