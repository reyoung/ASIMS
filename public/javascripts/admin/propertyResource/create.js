require(["admin/active","field_checker"],function (act,fc){
    $(function (){
        act("nav_propertyResource_create")
        fc.def(["Name","Position","Amount","Telephone","Comment"])
        $("form").submit(function (){
            var ok = fc.chk("Name",function (text){
                if(text.length!=0){
                    return true
                } else {
                    return "物业设施不为空"
                }
            })
            ok = fc.chk("Position",function (text){
                if(text.length!=0){
                    return true
                } else {
                    return "位置不为空"
                }
            }) && ok
            ok = fc.chk("Amount",function (num){
                var num = parseInt(num)
                if (num >= 1){
                    return true
                } else {
                    return "数量必须大于0"
                }
            }) && ok
            ok = fc.chk("Telephone",function (tel){
                var flag = true
                for (var i=0;i<tel.length;++i){
                    if (isNaN(parseInt(tel[i]))) {
                        flag = false
                        break
                    }
                }
                if (flag && tel.length!=0) return true
                else {
                    return "请输入正确的电话号码"
                }
            }) && ok
            return ok
        })
    })
})