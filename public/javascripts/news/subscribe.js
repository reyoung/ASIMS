require(["admin/active"],function (act){
    $(function(){
       act("nav_subscribe")
       $("form").submit(function(){
            var arr = []
            $("input[type=checkbox]").each(function(){
                var id = parseInt($(this).attr("data-id"),10)
                if($(this).attr("checked")=="checked"){
                    arr.push(id)
                }
            })
            $("input[type=hidden]").val(JSON.stringify(arr))
            return true
       })
    })
})