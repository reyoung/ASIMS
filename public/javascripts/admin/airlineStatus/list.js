require(["admin/active","admin/delete"],function(act,opt){
    $(function (){
    	act("nav_airlineStatus_list")
    	$(function(){
    	    $(".btn-info").click(function(){
    	        var id = parseInt($(this).attr("data-id"),10)
    	        console.log("The Airline Status "+id+" will fly")
    	    })
    	})
    });
})