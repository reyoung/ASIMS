require(["admin/active","field_checker"],function (act,fc){
    $(function (){
        act("nav_airportResource_create")
        fc.def(["Name","Position","Amount","Comment"])
        $("form").submit(function (){
            var ok = true
            ok = fc.chk("Name",function (text){
                if (text.length!=0){
                    return true
                } else {
                    return "资源名称不能为空"
                }
            })
            ok = fc.chk("Position",function (text) {
                if (text.length!=0){
                    return true
                } else {
                    return "位置不能为空"
                }
            }) && ok
            ok = fc.chk("Amount",function (num) {
                num = parseInt(num)
                if (num >= 1){
                    return true
                } else {
                    return "数量必须为数字，且大于0"
                }
            }) && ok
            return ok
        })
    })
})