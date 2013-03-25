require(["admin/active"],function(act){
    $(function (){
      act("nav_airlinePlan_list") //! Active The Navigation List.
      $(".btn-group .btn.btn-danger").click(function(){
        //console.debug("On Delete Btn Click")
       var idToDelete = $(this).attr("data-id")
        //console.debug("The Data need to delete is "+idToDelete)
        var host = location.host;
    	var delete_url = "http://" + host + "/admin/AirlinePlan/" + idToDelete;
    	$.ajaxSetup({
    		type: "DELETE",
    		async: false,
    	}); 
    	var responseResult = $.ajax({url:delete_url}).responseText;
    	if(responseResult){
    		 window.location.reload();
    	}
    	else{
    		
    	}
      })
    })

})