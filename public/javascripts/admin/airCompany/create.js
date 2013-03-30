require(["admin/active","field_checker"],function (act,fc){
    $(function (){
        act("nav_airCompany_create")
        fc.def("Name")
        //提交时检查表单里的内容。
        $("form.form-horizontal").submit(function(e){
            return fc.chk("Name",function (text){
                if(text.length==0){
                    return "航空公司名称不能为空"
                } else if(!checkName(text)) {
                    return "该公司名已存在请重新输入"
                } else {
                    return true
                }
            })
        });
        
        //获得输入名称的url
        function checkName(companyName){
        	var url = "http://"+location.host+"/admin/AirCompany/byName/" + companyName + ".json";
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
	        				return_val = false;
        				}
        			}
        		}
        	};
        	xmlhttp.open("GET",url,false);
        	xmlhttp.send();
        	return  return_val
        }
    })
})