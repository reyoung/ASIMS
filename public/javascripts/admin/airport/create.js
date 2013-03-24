require(["admin/active","libs/functools"],function(active,f){
    active("nav_airport_create")
    function updateCity(){
        countryId = $("#CountryId").val()
        tables = JSON.parse( $("#table_json").text())
        cities = f.filter(function (el,idx,lst){
            return idx!=0;
        },tables[countryId])
        city = $("#CityId")
        city.empty()
//        cid = $("#city_id_json").text()
        for (i=0;i<cities.length;++i){
            city.append("<option value='"+i+"'>"+cities[i]+"</option>")
        }
    }

    $("#CountryId").change(updateCity)
    updateCity()
    $("#CityId")[0].selectedIndex = $("#city_id_json").text()


    //* TODO Check The Airport Name Not Empty
     $("form.form-horizontal").submit(function(e){
	    var airport_name = $("#Name").val();
	    if(airport_name == null || airport_name.length == 0){
	    	e.preventDefault();
	    	$("span.help-inline").text("机场名不能为空");
	    	$("div.control-group:eq(0)").attr("class","control-group error");
	    }
    });
    $("#Name").focus(function(){
    	$("span.help-inline").text(" ");
    	var span_class = $("span").hasClass("help-inline error");
    	if(divclass){
    		var divclass = $("div").hasClass("control-group error");
        	if(divclass){
        		$("div.control-group.error").attr("class","control-group");
        	}
    	}
    });
})