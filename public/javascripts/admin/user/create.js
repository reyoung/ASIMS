require(["admin/active","field_checker","isEmail"],function(act,fc,isEmail){
    act("nav_user_list")
    $(function(){
        fc.def(["LoginName","Password","PasswordRepeat","Email","UserNumber","Telephone"])
        $("form").submit(function(){
            var ok = fc.chk("LoginName",function(text){
                if(text.length==0){
                    return "登录名称不能为空"
                } else if(text.length>255){
                    return "登录名称不能长于255字符"
                } else {
                    return true
                }
            })
            ok = ok && fc.chk("Password",function(pwd){
                if(pwd.length==0){
                    return "密码不能为空"
                } else if(pwd.length>255){
                    return "密码不能超过255字符"
                } else {
                    return true
                }
            })
            ok = ok && fc.chk("PasswordRepeat",function(pwd){
                if(pwd!==$("#Password").val()){
                    return "两次输入的密码不一致"
                } else {
                    return true
                }
            })

            ok = ok && fc.chk("Email",function (email){
                if(!isEmail(email)){
                    return "请输入正确格式的Email地址"
                } else {
                    return true
                }
            })
            ok = ok && fc.chk("UserNumber",function(number){
                if(number.length===0){
                    return "工号不能为空"
                } else {
                    return true
                }
            })
            function IsAllDigital(str){
                for(var i=0;i<str.length;++i){
                    if(isNaN(parseInt(str[i],10))
                        return false
                }
                return true
            }
            function CheckTel(tel){
                if(!IsAllDigital(tel)){
                    return "请输入正确的电话号码"
                } else {
                    return true
                }
            }
            ok = ok && fc.chk("Telephone",CheckTel)
            ok = ok && fc.chk("Mobile",CheckTel)
            return ok
        })
    })
})