require(["admin/active"],function(active){
    $(function() {
        active("nav_airlinePlan_create")
        $('#datetimepicker').datetimepicker({
            language: 'en'
        });

        function ResetStopovers(){
            $("#StopOver").val(1)
        }

        function StopoversMinusClick(){
            rmId = $(this).attr("stopovers-id")
            //! Remove Hidden Field
            curData = JSON.parse($("#Stopovers").val())
            i = 0
            for (i=0;i<curData.length; ++i){
                if (curData[i]==rmId){
                    break;
                }
            }
            curData.splice(i,1)
            $("#Stopovers").val(JSON.stringify(curData))

            //! Remove Dom
            tr = $(this)[0].parentNode.parentNode
            tbody = tr.parentNode
            tbody.removeChild(tr)
        }

        $("#stopover_cls_btn").click(ResetStopovers)

        $("#Stopover_Add").click(function (){
            function AppendIdToStopoversHidden(id){
                curData = $("#Stopovers").val()
                if(curData=="") {
                    curData = Array(0);
                } else {
                    curData = JSON.parse(curData)
                }
                curData.push(id)
                $("#Stopovers").val(JSON.stringify(curData))
            }
            function AppendIdToStopoversTable(id){
                tbody = $("#StopoversInput table tbody")
                originText = tbody.html()
                name = $("#StopOver option[value="+id+"]").text()
                tbody.html(originText+"<tr><td>"+name+"</td><td><a href=\"#\" stopovers-id=\""+id+"\" class=\"btn\"><i class=\"icon-minus\"></i> </a> </td></tr>")
            }

            add_id = $("#StopOver").val()
            AppendIdToStopoversHidden(add_id)
            AppendIdToStopoversTable(add_id)
            ResetStopovers();
            $('#Stopover_Modal').modal('hide')
            $("tbody a.btn").click(StopoversMinusClick)
        })

        $("tbody a.btn").click(StopoversMinusClick)

        $("form.form-horizontal").submit(function(e){
    	    var number = $("#Number").val();
    	    var leave_time = $("#LeaveTime").val();
    	    var fly_time = $("#FlyTime").val();
    	    var leave_place = $("#LeavePlace").val();
    	    var arrive_place = $("#ArrivePlace").val();
    	    var middle_place = $("#StopoversInput.tbody").val();
    	    var repeat_month = $("#month").val();
    	    var checked_week = new Array();
    	    var j = 0;
    	    //选出要重复的星期
    	    for(var i = 1;i <= 7;i++) {
    	    	if($("#week"+ i).attr("checked") == "checked"){
    	    		checked_week[j++] = i;
    	    	}
    	    }
    	    //检查机场名是否为空
    	    if(number == null || number.length == 0){
    	    	e.preventDefault();
    	    	$("span.help-inline:eq(0)").text("机场名不能为空");
    	    	$("div.control-group:eq(0)").attr("class","control-group error");
    	    }
    	    //检查起飞日期是否为空且格式是否正确
    	    if(leave_time == null || leave_time.length == 0){
    	    	e.preventDefault();
    	    	$("span.help-inline:eq(1)").text("起飞日期不能为空");
    	    	$("div.control-group:eq(1)").attr("class","control-group error");
    	    }
    	    else{
    	    	var str = "^((?:19|20)\\d\\d)\/(0[1-9]|[012])\/(0[1-9]|[12][0-9]|3[01])\\s([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$"
    	    	var date = new RegExp(str);
    	    	var result = date.test(leave_time);
    	    	if(!result){
    	    		e.preventDefault();
    	    		$("span.help-inline:eq(1)").text("起飞日期输入格式不正确");
    	    		$("div.control-group:eq(1)").attr("class","control-group error");
    	    	}
    	    }
    	    //检查起飞时间是否为空且格式是否正确
    	    if(fly_time == null || fly_time.length == 0){
    	    	e.preventDefault();
    	    	$("span.help-inline:eq(2)").text("起飞时间不能为空");
    	    	$("div.control-group:eq(2)").attr("class","control-group error");
    	    }
    	    else{
    	    	if(fly_time < 1 || parseInt(fly_time)!= fly_time){
    	    		e.preventDefault();
        	    	$("span.help-inline:eq(2)").text("飞行时间应该大于1且为整数")
        	    	$("div.control-group:eq(2)").attr("class","control-group error");
    	    	}
    	    }
    	    //检查始发地和目的地是否相同
    	    if(leave_place == arrive_place){
    	    	e.preventDefault();
    	    	$("span.help-inline:eq(3)").text("始发地和目的地不能相同")
    	    	$("div.control-group:eq(3)").attr("class","control-group error");
    	    	$("span.help-inline:eq(4)").text("始发地和目的地不能相同")
    	    	$("div.control-group:eq(4)").attr("class","control-group error");
    	    	
    	    }
    	    $("#Number,#LeaveTime,#FlyTime,#LeavePlace,#ArrivePlace").focus(function(){
    	    	$("span.help-inline").text("");
    	    	var divclass = $("div").hasClass("control-group error");
            	if(divclass){
            		$("div.control-group.error").attr("class","control-group");
            	}
    	    });
    	    //确定重复的形式
    	    if(checked_week.length != 0){
    	    	$("#Repeat").val("W" + checked_week.join(""));
    	    }
    	    else if(repeat_month != null && repeat_month.length != 0){
    	    	$("#Repeat").val("M" + repeat_month);
    	    }
    	    else{
    	    	$("#Repeat").val("N");
    	    }
        });
    });
})