require(["admin/active"],function(act){
    $(function (){
      act("nav_airlinePlan_list") //! Active The Navigation List.
      $(".btn-group .btn.btn-danger").click(function(){
        console.debug("On Delete Btn Click")
        idToDelete = $(this).attr("data-id")
        console.debug("The Data need to delete is "+idToDelete)
      })
    })

})