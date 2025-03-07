<script>
function addFileDenuncia(){
    var result = new Array();
    var inputs = document.getElementsByTagName("input");
    for(var i=0; i < inputs.length; i++) {
        if(inputs[i].id.endsWith('selectedFileDenuncia'))
        	document.getElementById(inputs[i].id).value = "true";        
    }
}

function deleteFileDenuncia(){
	 var result = new Array();
	 var inputs = document.getElementsByTagName("input");
	 for(var i=0; i < inputs.length; i++) {
	     if(inputs[i].id.endsWith('selectedFileDenuncia'))
	        document.getElementById(inputs[i].id).value = "false";
	     if(inputs[i].id.endsWith('adjunto'))
	        	document.getElementById(inputs[i].id).value = "";
	 }
	 
	 if($("[id*='asunto']").val()!="" && $("[id*='descripcion']").val()!=""){
 		$("[id*='send_acept']").prop("disabled",false); 
 		$("[id*='send_acept']").removeClass("disabled");	
 	}
}
</script>