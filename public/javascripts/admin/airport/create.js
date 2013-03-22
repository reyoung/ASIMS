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

})