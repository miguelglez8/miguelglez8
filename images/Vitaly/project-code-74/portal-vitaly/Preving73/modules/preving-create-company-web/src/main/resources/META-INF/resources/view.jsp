<%@ include file="/init.jsp" %>

<liferay-ui:success key="import-process-success"  message="createcompany.import.success"/>
<liferay-ui:error key="import-process-error"  message="createcompany.import.error"/>


<liferay-ui:tabs names="createcompany.view.search.title.tab,createcompany.view.create.title.tab,createcompany.view.import.title.tab" refresh="false" >

	<liferay-ui:section>
		<%@include file="companies.jspf" %>
	</liferay-ui:section>

	 <liferay-ui:section>
		<%@include file="create.jspf" %>
	</liferay-ui:section>

	<liferay-ui:section>
		<%@include file="import.jspf" %>
	</liferay-ui:section>



<script>
$(document).ready(function(){
     $.fn.dataTable.ext.search.push(
        function( settings, data, dataIndex){
            console.log("settings.sTableId: " + settings.sTableId);
            if(settings.sTableId == "table_companies"){
                console.log("de table_companies");
                return _seachCompanies(settings, data, dataIndex);
            }else if(settings.sTableId == "table_id"){
                console.log("de table_id");
                return _searchUsers(settings, data, dataIndex);
            }
        }
    );


});

function _seachCompanies(settings, data, dataIndex){
    try{
        var numCond = 0;
        var arrFields = ["inpSearchName","inpSearchCif","inpSearchEmail"];
        var arrColumnas = [0,1,3];
        for(var i = 0; i < arrFields.length; i++){
            if(document.getElementById(arrFields[i]).value.trim() != "") numCond++;
            if(document.getElementById(arrFields[i]).value.trim() != ""
                    && data[arrColumnas[i]].toUpperCase().indexOf(document.getElementById(arrFields[i]).value.trim().toUpperCase().trim()) != -1 ) numCond--;

        }
        if(numCond == 0
                &&  (document.querySelector("input[name='inpSearchActive']:checked").value  == "Todos"
                        || data[5].indexOf(document.querySelector("input[name='inpSearchActive']:checked").value.substring(0,1)) == 0 ) ) return true;

        /*if($("#inpSearch").val() == "" || data[1].toUpperCase().indexOf($("#inpSearch").val().toUpperCase().trim()) != -1 )  return true;*/

        return false;

    }catch(e){
        console.error("_seachCompanies(): " + e);
    }
}


function _searchUsers(settings, data, dataIndex){
    try{
        var numCond = 0;
        var arrFields = ["inpSearchWorkcenter", "inpSearchJobtitle"];
        var arrColumnas = [4, 3];
        for(var i = 0; i < arrFields.length; i++){
            if(document.getElementById(arrFields[i]).value.trim() != "") numCond++;
            if(document.getElementById(arrFields[i]).value.trim() != ""
                    && data[arrColumnas[i]].toUpperCase().indexOf(document.getElementById(arrFields[i]).value.trim().toUpperCase().trim()) != -1 ) numCond--;
        }

        if(numCond == 0
            && (document.querySelector("input[name='inpSearchActive']:checked").value  == "Todos"
                || data[8].indexOf(document.querySelector("input[name='inpSearchActive']:checked").value.substring(0,1)) == 0)
            && (document.querySelector("input[name='inpSearchAdmin']:checked").value  == "Todos"
                || data[5].indexOf(document.querySelector("input[name='inpSearchAdmin']:checked").value.substring(0,1)) == 0)) return true;

        return false;
    }catch(e){
        console.error("_searchUsers(): " + e);
    }
}
</script>

</liferay-ui:tabs>