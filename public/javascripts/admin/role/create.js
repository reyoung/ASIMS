require(["admin/active"],function(act){
    $(function (){
        act("nav_role_create")

        //! Handle Edit Create
        if ($("#Privilege").val().length!=0){
            curPrivilege = $("#Privilege").val()
            $("input[type='checkbox']").each(function (){
                pid = $(this).attr("p-id")
                prw = $(this).attr("p-rw")
                flag = 0
                if (prw == 'r') {
                    flag = 1
                } else {
                    flag = 2
                }
                flag <<= pid *2
                if((curPrivilege&flag)!=0){
                    $(this).attr("checked","checked")
                }
            })
        }

    })
})