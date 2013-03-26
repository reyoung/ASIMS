define(["libs/functools"],function (functools){
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

        function ResetCheckField(field_id) {
            function __ResetCheckOneField(f){
                function Method(fid){
                    $($("#"+fid)[0].parentNode).attr("class","control-group")
                    $("#"+fid+" ~ .help-inline").text("")
                }
                $("#"+f).focus(functools.curry(Method,f))
            }
            function isArray(obj){
                return Object.prototype.toString.call(obj) == '[object Array]';
            }
            if (isArray(field_id)){
                for (i=0;i<field_id.length;++i){
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