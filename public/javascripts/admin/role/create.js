require(["admin/active","field_checker"],function(act,field){
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
        field.def(["Name"])
        $("form").submit(function (){
            function makePrivilege(){
                privilege = 0
                $("input[type='checkbox']").each(function (){
                    pid = $(this).attr("p-id")
                    prw = $(this).attr("p-rw")
                    if ($(this).attr("checked")=="checked"){
                        flag = 0
                        if (prw == 'r') {
                            flag = 1
                        } else {
                            flag = 2
                        }
                        flag <<= pid *2
                        privilege |= flag
                    }
                })
                $("#Privilege").val(privilege)
            }
            if (!field.chk("Name",function (str){
                if(str.length!=0){
                    return true
                } else {
                    return "角色名称不能为空"
                }
            })) {
                return false
            }
            makePrivilege();
            return true
        })
    })
})