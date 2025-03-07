<script>
function addFileSelectEtico(){
    var result = new Array();
    var inputs = document.getElementsByTagName("input");
    for(var i=0; i < inputs.length; i++) {
        if(inputs[i].id.endsWith('selectedFileEtico'))
        	document.getElementById(inputs[i].id).value = "true";        
    }
}

function deleteFileSelectEtico(){
	 var result = new Array();
	 var inputs = document.getElementsByTagName("input");
	 for(var i=0; i < inputs.length; i++) {
	     if(inputs[i].id.endsWith('selectedFileEtico'))
	        document.getElementById(inputs[i].id).value = "false";
	     if(inputs[i].id.endsWith('file')){
	        	document.getElementById(inputs[i].id).value = "";
	        	console.log("delete file");
	     }
	 }
}
</script>