<%@page import="com.adeplus.liferay.portlet.menu.web.constants.MenuPortletKeys"%>
<%@ page import="com.liferay.portal.kernel.util.PortalUtil" %>
<%@ page import="com.liferay.portal.kernel.model.Layout" %>
<%@ page import="com.liferay.portal.kernel.model.Group" %>
<%@ page import="com.liferay.portal.kernel.service.GroupLocalServiceUtil" %>

<%@ include file="/init.jsp" %>

<%
	String companyName = "";
	if(request.getAttribute(MenuPortletKeys.COMP_NAME_PARAM)!=null){
		companyName = (String) request.getAttribute(MenuPortletKeys.COMP_NAME_PARAM);
	}
	
	String layoutDefect = (String) request.getAttribute(MenuPortletKeys.LAYOUT_DEFECT);
    String estadoDefect = (String) request.getAttribute(MenuPortletKeys.ESTADO_DEFECT);

	int paginaCount = 0;
	Boolean dontActive = Boolean.FALSE;
	int layoutDefectInteger = 0;
	if(!layoutDefect.isEmpty()){
		layoutDefectInteger = Integer.parseInt(layoutDefect);
	}
	
	String stateCurrent = "";
	
	if(request.getAttribute(MenuPortletKeys.ESTADO_CURRENT_PARAM)!=null){
		stateCurrent = (String) request.getAttribute(MenuPortletKeys.ESTADO_CURRENT_PARAM);
	}
	
	boolean haveEstados = false;
	if(request.getAttribute(MenuPortletKeys.ESTADOS_LIST_PARAM)!=null){
		haveEstados=true;
	}
%>

<portlet:resourceURL id="saveEstado" var="saveEstadoURL">
    <portlet:param name="compId" value="${compId}" />
</portlet:resourceURL>

<% if(!companyName.isEmpty()){ %>
<h2><%=companyName %></h2>
<%} %>

<%
	if(haveEstados && !estadoDefect.equals("0")){
%>
	<div class="div-estado"> 
		<c:set var="stateCurrent" value="<%=stateCurrent %>" />
		<fieldset class="custom-fieldset">
			<aui:select name="<%=MenuPortletKeys.ESTADO%>" label="estado.titulo"> 
			    <aui:option value="" disabled="true">
			    </aui:option>
				<c:forEach items="${estadosList}" var="estado">
					<c:choose>
						<c:when test="${stateCurrent eq estado.id}">
							<aui:option value="${estado.id}" selected="true" disabled="true" style="background-color: #C8C8C8;">
					        	${estado.nombre} 
					        </aui:option>
						</c:when>
						<c:otherwise>
							<aui:option value="${estado.id}" >
					        	${estado.nombre}
					        </aui:option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</aui:select>
		</fieldset>
	</div>
<%
	}
%>

<nav class="navbar navbar-expand-lg navbar-light prv-tabs">
    <div class="collapse navbar-collapse">
        <ul class="navbar-nav mr-auto">

            <c:forEach items="${paginas}" var="pagina">
                <% Layout pagina = (Layout) pageContext.getAttribute("pagina");
                String active = themeDisplay.getLayout().getPlid() == pagina.getPlid() ? "active" : "";
                String url = PortalUtil.getLayoutFriendlyURL(pagina, themeDisplay); 
                
                if(active==null || active.isEmpty()){
                	paginaCount ++;
                	dontActive = Boolean.TRUE;
                }
                
                if(dontActive && layoutDefectInteger == paginaCount){
                	active = "active";
                }
            	
                %>

                <c:choose>
                    <c:when test="<%= pagina.hasChildren() %>">

                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                ${pagina.getName(locale)}
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">

                                <c:forEach items="<%= pagina.getChildren() %>" var="subPagina">

                                    <% Layout subPagina = (Layout) pageContext.getAttribute("subPagina"); %>
                                    <% String subPaginaUrl = PortalUtil.getLayoutFriendlyURL(subPagina, themeDisplay); %>
                                    <% String subActive = themeDisplay.getLayout().getPlid() == subPagina.getPlid() ? "active" : ""; %>
                                    <li class="nav-item <%= subActive %>">
                                        <a class="nav-link" href="<%= subPaginaUrl %>?query=${compId}">${subPagina.getName(locale)}</a>
                                    </li>

                                </c:forEach>

                            </ul>
                        </li>

                    </c:when>
                    <c:otherwise>
                        <li class="nav-item <%= active %>">
                            <a class="nav-link" href="<%= url %>?query=${compId}">${pagina.getName(locale)}</a>
                        </li>
                    </c:otherwise>
                </c:choose>

            </c:forEach>

        </ul>
    </div>
</nav>

<span id="capaDialog">
    <div class="contentModal" id="modalContainer"  style="border-radius: 10px; width: 650px;">
       <div class="headerModal">
       		<p>
       			<span id="spnTitleNueva">
                    <liferay-ui:message key="modal.estado.cambiar.titulo"/>
                </span>
       		</p>
       </div>
       <div class="container">
       		<div class="image-warning">
       		
       		</div>
          <p class="text-center">
              <liferay-ui:message key="modal.estado.cambiar.contenido"/>
          </p>
       </div>
       <div class="footerModal">
             <button type="button" class="btn-primary buttonModal " id="btnSaveSt" onclick="saveState()" data-dismiss="modal" aria-label="Close">
                <liferay-ui:message key="modal.estado.cambiar.continuar"/>
             </button>

            <button type="button" class="buttonModal " id="btnCancelSt" onclick="cancelState()"  data-dismiss="modal" aria-label="Close">
               <liferay-ui:message key="modal.estado.cambiar.cancelar"/>
            </button>
       </div>
  	</div>
  	<div class="divModal" id="modalPortlet">
    </div>
</span>

<script>

	function saveState(){
		var idEstado = $("#<portlet:namespace/><%=MenuPortletKeys.ESTADO%>").val();
		
		if(idEstado!=""){
			$.ajax({
	            url: '${saveEstadoURL}',
	            dataType: 'json',
	            data: {
	                'idEstado' : idEstado
	            },
	            type: 'post'
	        }).done(function(data){
	        	location.reload();
	        });
		}
	}
	
	function cancelState(){
		$("#<portlet:namespace/><%=MenuPortletKeys.ESTADO%>").val('<%=stateCurrent%>');
		
		$('.divModal').css('display','none');
		$('.contentModal').css('display','none');
	}
	$(document).ready( function () {
		$('.dropdown-menu li').each(function(){
			if($(this).hasClass('active')){
				$(this).closest('.dropdown').css('border-top', '4px solid #004d71');
				$(this).closest('.dropdown').css('font-weight', '600');
				$(this).closest('.dropdown').css('background-color', 'white');
			}
		});
	
		$("#<portlet:namespace/><%=MenuPortletKeys.ESTADO%>").change(function(){
			$('#modalPortlet').css('display','block');
		       $('#modalContainer').css('display','block');
		});
	});
</script>