require(["admin/active"],function (act){
    $(function (){
        act("nav_airCompany_create")
        var isAdd = true;
        //提交时检查表单里的内容。
        $("form.form-horizontal").submit(function(e){
        	var name = $("input#Name").val();
        	if( name == null || name.length == 0){
        		e.preventDefault();
        		$("span").attr("style","visibility:visible");
        		$("div.control-group").attr("class","control-group error");
        		
        	}
        	else{
        	     checkName();
        	     if(isAdd){
        	    	 e.preventDefault();
        	     }
        	}
        });
        
        //获得输入名称的url
        function checkName(){
        	var companyname = $("input#Name").val();
        	var url = "http://" +window.location.host+"/admin/AirCompany/byName/" + companyname + ".json";
            loadJason(url);
        }
       
        //检验新增的公司是否在数据库中
        function loadJason(url){ 
        	var xmlhttp;
        	if(window.XMLHttpRequest){
            	xmlhttp = new XMLHttpRequest();
            }
            else{
            	xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
        	
        	xmlhttp.onreadystatechange = function(){
        		if(xmlhttp.readyState == 4){
        			if((xmlhttp.status >= 200 && xmlhttp.status < 300) || xmlhttp.status == 304){
        				var json = xmlhttp.responseText
        				if(json != "false"){
        					$("span").text("该公司名已存在请重新输入");
	        				$("span").attr("style","visibility:visible");
	        				$("div.control-group").attr("class","control-group error");
	        				isAdd = false;
        				}
        			}
        		}
        	};
        	xmlhttp.open("GET",url,true);
        	xmlhttp.send();
        }
       
        //重新输入公司名时取消提示
        $("#Name").focus(function(){
        	$("span").attr("style","visibility:hidden");
        	var divclass = $("div").hasClass("control-group error");
        	if(divclass){
        		$("div.control-group.error").attr("class","control-group");
        	}
        });
       
        
    })
})