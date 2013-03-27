require(["admin/active","admin/delete"],function(act,opt){
    $(function (){
    	act("nav_airportResource_list") //! Active The Navigation List.
    	opt("/admin/AirlinePlan/");
    });

})