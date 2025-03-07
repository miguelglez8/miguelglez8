<script>
function addLogoSelect(){
    var result = new Array();
    var inputs = document.getElementsByTagName("input");
    for(var i=0; i < inputs.length; i++) {
        if(inputs[i].id.endsWith('selectedLogo'))
        	document.getElementById(inputs[i].id).value = "true";        
    }
}

function deleteLogoSelect(){
	 var result = new Array();
	 var inputs = document.getElementsByTagName("input");
	 for(var i=0; i < inputs.length; i++) {
	     if(inputs[i].id.endsWith('selectedLogo'))
	        document.getElementById(inputs[i].id).value = "false";
	     if(inputs[i].id.endsWith('logo'))
	        	document.getElementById(inputs[i].id).value = "";
	 }
}
</script>