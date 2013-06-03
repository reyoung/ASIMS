define([],function (){
        var retv = new Object();
        retv.parent = false
        function CheckField(field_id, method){
            var field = $("#"+field_id)
            var errStr = method(field.val(), field)
            if(errStr!=true){
                if(!retv.parent){
                    $(field[0].parentNode).attr("class","control-group error")
                    $("#"+field_id+" ~ .help-inline").text(errStr)
                } else {
                    $(field[0].parentNode.parentNode).attr("class","control-group error")
                    $("#"+field_id+" ~ .help-inline").text(errStr)
                }
                return false
            }
            return true
        }

        function ResetCheckField(field_id) {
            function __ResetCheckOneField(f){
                $("#"+f).focus(function (){
                    if(!retv.parent){
                        $($("#"+f)[0].parentNode).attr("class","control-group")
                        $("#"+f+" ~ .help-inline").text("")
                    }else {
                        $($("#"+f)[0].parentNode.parentNode).attr("class","control-group")
                        $("#"+f+" ~ .help-inline").text("")
                    }
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
        retv.check = CheckField
        retv.define = ResetCheckField
        retv.chk = CheckField
        retv.def = ResetCheckField
        return retv
})