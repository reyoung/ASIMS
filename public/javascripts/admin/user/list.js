require(["admin/active","admin/delete"],function(act,opt){
    $(function (){
            act("nav_user_list")
        	opt("/admin/User/");
    })
})