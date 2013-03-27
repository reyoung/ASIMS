define([],function (){
        function CheckField(field_id, method){
            var field = $("#"+field_id)
            var errStr = method(field.val(), field)
            if(errStr!=true){
                $(field[0].parentNode).attr("class","control-group error")
                $("#"+field_id+" ~ .help-inline").text(errStr)
                return false
            }
            return true
        }

        function ResetCheckField(field_id) {
            function __ResetCheckOneField(f){
                $("#"+f).focus(function (){
                    $($("#"+f)[0].parentNode).attr("class","control-group")
                    $("#"+f+" ~ .help-inline").text("")
                })
            }
            function isArray(obj){
                return Object.prototype.toString.call(obj) == '[object Array]';
            }
            if (isArray(field_id)){
                for (var i=0;i<field_id.length;++i){
                    __ResetCheckOneField(field_id[i])
                }
            } else {
                __ResetCheckOneField(field_id)
            }
        }
        retv = new Object();
        retv.check = CheckField
        retv.define = ResetCheckField
        retv.chk = CheckField
        retv.def = ResetCheckField
        return retv
})