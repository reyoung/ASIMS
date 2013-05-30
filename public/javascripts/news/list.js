require(["admin/active"],function (act){
    $(function(){
       $('#fromPicker').datetimepicker({
           language: 'en',
           pickTime: false
       });
       act(ACT_ID)
    })
})