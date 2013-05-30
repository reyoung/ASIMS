require(["admin/active","admin/delete"],function(act,opt){
    $(function (){
    	act("nav_airlineStatus_list")
    	$(function(){
            function updateByID(id, actCode){
                var uurl = "http://"+ location.host + "/admin/AirlineStatus/" + id
                $.ajax({
                    url: uurl,
                    data: {action:actCode},
                    type: "PUT",
                    datatype: "jsonp",
                    success: function (retv){
                        if(retv === true){
                            window.location.reload();
                        } else {
                            console.log("Cannot Do This!")
                        }
                    }}
                )
            }

    	    $(".btn-info").click(function(){
    	        var id = parseInt($(this).attr("data-id"),10)
    	        updateByID(id,0)
    	    })
    	    $(".btn-success").click(function(){
    	        var id = parseInt($(this).attr("data-id"),10)
    	        updateByID(id,1)
    	    })
    	    $(".btn-danger").click(function(){
    	        var id = parseInt($(this).attr("data-id"),10)
    	        updateByID(id,2)
    	    })
    	})
    });
})