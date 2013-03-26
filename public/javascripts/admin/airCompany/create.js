require(["admin/active"],function (act){
    $(function (){
        act("nav_airCompany_create")
        
        //提交时检查表单里的内容。
        $("form.form-horizontal").submit(function(e){
        	var name = $("input#Name").val();
        	if( name == null || name.length == 0){
        		e.preventDefault();
        		$("span").text("航空公司名称不能为空");
        		$("span").attr("style","visibility:visible");
        		$("div.control-group").attr("class","control-group error");
        		
        	}
        	else{
        	   ok =  checkName();
        	     if(!ok){
        	    	 e.preventDefault();
        	     }
        	}
        });
        
        //获得输入名称的url
        function checkName(){
        	var companyname = $("input#Name").val();
        	var url = "http://"+location.host+"/admin/AirCompany/byName/" + companyname + ".json";
            return loadJson(url);
        }
       
        //检验新增的公司是否在数据库中
        function loadJson(url){ 
        	var xmlhttp;
        	if(window.XMLHttpRequest){
            	xmlhttp = new XMLHttpRequest();
            }
            else{
            	xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
        	return_val = true
        	
        	xmlhttp.onreadystatechange = function(){
        		if(xmlhttp.readyState == 4){
        			if((xmlhttp.status >= 200 && xmlhttp.status < 300) || xmlhttp.status == 304){
        				var json = xmlhttp.responseText
        				if(json != "false"){
        					$("span").text("该公司名已存在请重新输入");
	        				$("span").attr("style","visibility:visible");
	        				$("div.control-group").attr("class","control-group error");
	        				return_val = false;
        				}
        			}
        		}
        	};
        	xmlhttp.open("GET",url,false);
        	xmlhttp.send();
        	return  return_val
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