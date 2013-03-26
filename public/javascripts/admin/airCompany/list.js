require(["admin/active","admin/delete"],function (act,opt){
    $(function (){
        act("nav_airCompany_list")
        opt("/admin/AirCompany/");
        
    });
})