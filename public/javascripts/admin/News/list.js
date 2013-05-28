require(["admin/active","admin/delete"],function(act,opt){
    $(function (){
    	act("nav_news_list") //! Active The Navigation List.
    	opt("/admin/News/");
    });
})