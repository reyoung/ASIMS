require(["admin/active"],function(act){
    $(function (){
        act("nav_role_create")

        function CheckField(field_id, method){
            field = $("#"+field_id)
            errStr = method(field.val(), field)
            if(errStr!=true){
                $(field[0].parentNode).attr("class","control-group error")
                $("#"+field_id+" ~ .help-inline").text(errStr)
                return false
            }
            return true
        }

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
                console.log(privilege)
                $("#Privilege").val(privilege)
            }
            if (!CheckField("Name",function (str){
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