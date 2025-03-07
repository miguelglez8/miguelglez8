<script>
    // ------------step-wizard-------------
    $(document).ready(function () {

        var portletNamespace = "_com_canal_etico_liferay_portlet_denuncia_web_DenunciaPortlet_";

        
        $("#"+portletNamespace+"button_confirm").click(function(){
        	confirm()
        });
        $("#link_confirm").click(function(){
            confirm();
        });

        function confirm(){

            $("#"+portletNamespace+"empresa_cif").val($("#"+portletNamespace+"cif").val());
            $("#"+portletNamespace+"empresa_nombre").val($("#"+portletNamespace+"compName").val());
            $("#"+portletNamespace+"empresa_vinculacion").val($.trim($("#"+portletNamespace+"vinculacion option:selected").text()));

            $("#"+portletNamespace+"empleado_email").val($.trim($("#"+portletNamespace+"email-empleado").val()));
            $("#"+portletNamespace+"empleado_nombre").val($.trim($("#"+portletNamespace+"name").val()));
            $("#"+portletNamespace+"empleado_apellidos").val($.trim($("#"+portletNamespace+"lastName").val()));
            $("#"+portletNamespace+"empleado_nif").val($.trim($("#"+portletNamespace+"nif").val()));
            $("#"+portletNamespace+"empleado_telefono").val($.trim($("#"+portletNamespace+"phone").val()));

            $("#"+portletNamespace+"denuncia_codigo").val($("#"+portletNamespace+"codigo").val());
            $("#"+portletNamespace+"denuncia_tipo").val($.trim($("#"+portletNamespace+"tipo option:selected").text()));
            $("#"+portletNamespace+"denuncia_categoria").val($.trim($("#" + portletNamespace + "consulta option:selected").text()));
            
            $("#"+portletNamespace+"detalle_asunto").val($("#"+portletNamespace+"asunto").val());
            $("#"+portletNamespace+"detalle_descripcion").val($("#"+portletNamespace+"descripcion").val());

            var fileName = document.getElementById("detalle_adjunto_pass").value;
            $("#"+portletNamespace+"detalle_adjunto").val(fileName);
        }



        $('.nav-tabs > li a[title]').tooltip();

        //Wizard
        $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {

            var target = $(e.target);

            if (target.parent().hasClass('disabled')) {
                return false;
            }
        });

        $(".next-step").click(function (e) {

            var active = $('.wizard .nav-tabs li.active');
            active.next().removeClass('disabled');
            nextTab(active);

        });
        $(".prev-step").click(function (e) {

            var active = $('.wizard .nav-tabs li.active');
            prevTab(active);

        });
    });

    function nextTab(elem) {
        $(elem).next().find('a[data-toggle="tab"]').click();
    }
    function prevTab(elem) {
        $(elem).prev().find('a[data-toggle="tab"]').click();
    }

    $('.nav-tabs').on('click', 'li', function() {
        $('.nav-tabs li.active').removeClass('active');
        $(this).addClass('active');
    });

	function sendValuesDenuncias(){
		var fileName = "-";
		if(undefined!=document.getElementById("_com_canal_etico_liferay_portlet_denuncia_web_DenunciaPortlet_adjunto").files[0]){
			fileName = document.getElementById("_com_canal_etico_liferay_portlet_denuncia_web_DenunciaPortlet_adjunto").files[0].name;
		}
		$("#detalle_adjunto_pass").val(fileName);
	}

	$("[id*='checkConditions']").click(function(){

		if ($(this).is(':checked') && $("[id*='checkProteccion']").is(':checked')) {
			$("[id*='button_confirm']").prop("disabled",false); 
			$("[id*='button_confirm']").removeClass("disabled");
    	} else {
    		$("[id*='button_confirm']").attr("disabled","disabled"); 
    		$("[id*='button_confirm']").addClass("disabled");
    	}
	});

	$("[id*='checkProteccion']").click(function(){

		if ($(this).is(':checked') && $("[id*='checkConditions']").is(':checked')) {
			$("[id*='button_confirm']").prop("disabled",false);
			$("[id*='button_confirm']").removeClass("disabled");
    	} else {
    		$("[id*='button_confirm']").attr("disabled","disabled");
    		$("[id*='button_confirm']").addClass("disabled");
    	}
	});
	
	$("[id*='email-empleado']").on('change keyup paste', function () {
		if ($("[id*='email-empleado']").val()=="") { 
			$("[id*='button-next-empleado']").attr("disabled","disabled"); 
			$("[id*='button-next-empleado']").addClass("disabled");
    	} else {
    		$("[id*='button-next-empleado']").prop("disabled",false);  
    		$("[id*='button-next-empleado']").removeClass("disabled");	
    	}
	});
	
	$("[id*='asunto']").on('change keyup paste', function () {
		if ($("[id*='asunto']").val()=="") { 
			$("[id*='send_acept']").attr("disabled","disabled"); 
			$("[id*='send_acept']").addClass("disabled");
    	} else if($("[id*='asunto']").val()!="" && $("[id*='descripcion']").val()!="" ){
    		if($("[id*='adjunto']").val()==""){
    			$("[id*='send_acept']").prop("disabled",false); 
        		$("[id*='send_acept']").removeClass("disabled");
    		}else if(undefined!=document.getElementById("_com_canal_etico_liferay_portlet_denuncia_web_DenunciaPortlet_adjunto").files[0]){
    			var ext=document.getElementById("_com_canal_etico_liferay_portlet_denuncia_web_DenunciaPortlet_adjunto").files[0].name;
    			if(ext.includes(".jpg") || ext.includes(".png") || ext.includes(".pdf") || ext.includes(".txt") || ext.includes(".xlsx") || ext.includes(".docs")){
    				$("[id*='send_acept']").prop("disabled",false); 
            		$("[id*='send_acept']").removeClass("disabled");
    			}
    		}
    			
    	}
	});
	
	$("[id*='descripcion']").on('change keyup paste', function () {
		if ($("[id*='descripcion']").val()=="") { 
			$("[id*='send_acept']").attr("disabled","disabled"); 
			$("[id*='send_acept']").addClass("disabled");
    	} else if($("[id*='asunto']").val()!="" && $("[id*='descripcion']").val()!=""){
    		if($("[id*='adjunto']").val()==""){
    			$("[id*='send_acept']").prop("disabled",false); 
        		$("[id*='send_acept']").removeClass("disabled");
    		}else if(undefined!=document.getElementById("_com_canal_etico_liferay_portlet_denuncia_web_DenunciaPortlet_adjunto").files[0]){
    			var ext=document.getElementById("_com_canal_etico_liferay_portlet_denuncia_web_DenunciaPortlet_adjunto").files[0].name;
    			if(ext.includes(".jpg") || ext.includes(".png") || ext.includes(".pdf") || ext.includes(".txt") || ext.includes(".xlsx") || ext.includes(".docs")){
    				$("[id*='send_acept']").prop("disabled",false); 
            		$("[id*='send_acept']").removeClass("disabled");
    			}
    		}	
    	}
	});
	
	$("[id*='adjunto']").on('change keyup paste', function () {
		if (undefined!=document.getElementById("_com_canal_etico_liferay_portlet_denuncia_web_DenunciaPortlet_adjunto").files[0]) { 
			var ext=document.getElementById("_com_canal_etico_liferay_portlet_denuncia_web_DenunciaPortlet_adjunto").files[0].name;
			if(ext=="" || ext.includes(".jpg") || ext.includes(".png") || ext.includes(".pdf") || ext.includes(".txt") || ext.includes(".xlsx") || ext.includes(".docs")){
				if($("[id*='asunto']").val()!="" && $("[id*='descripcion']").val()!=""){
					$("[id*='send_acept']").prop("disabled",false); 
		    		$("[id*='send_acept']").removeClass("disabled");
				}				
			}else{
				$("[id*='send_acept']").attr("disabled","disabled"); 
				$("[id*='send_acept']").addClass("disabled");
			}		
    	} else if($("[id*='asunto']").val()!="" && $("[id*='descripcion']").val()!=""){
    		$("[id*='send_acept']").prop("disabled",false); 
    		$("[id*='send_acept']").removeClass("disabled");	
    	}
	});
</script>