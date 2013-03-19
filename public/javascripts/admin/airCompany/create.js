require(["admin/active"],function (act){
    $(function (){
        act("nav_airCompany_create")
        $("form.form-horizontal").submit(function(e){
        	var name = $("input#Name").val();
        	if( name == null || name.length == 0){
        		e.preventDefault();
        		$("span").attr("style","visibility:visible");
        		
        	}
        });
        $("#Name").focus(function(){
        	$("span").attr("style","visibility:hidden");
        });
    })
})