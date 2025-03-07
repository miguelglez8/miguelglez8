$(document).ready(function() {

    /*  Sidebar */
    $('#adeplus .collapse-icon').addClass('adeplus-sidebar-icon-expand');
    $('[data-toggle=sidebar-colapse]').click(function (e) {
        e.preventDefault();
        SidebarCollapse();
    });

    function SidebarCollapse() {
        $('#adeplus .menu-collapsed').toggleClass('d-none');
        $('#adeplus .sidebar-submenu').toggleClass('d-none');
        $('#adeplus .submenu-icon').toggleClass('d-none');

        $('#adeplus #sidebar-container').toggleClass('sidebar-expanded sidebar-collapsed');
        $('#adeplus #brand-logo').toggleClass('brand-logo brand-logo-collapsed');

        // Collapse/Expand icon
        $('#adeplus .collapse-icon').toggleClass('adeplus-sidebar-icon-expand adeplus-sidebar-icon-collapse');
        ;

    }

    // Opción seleccionada del menu en el sidebar
    $("#adeplus .list-group-item").click(function() {
        $("#adeplus .list-group-item").removeClass("active");
        $(this).addClass("active");
    });

    // Dropdwon para las opciones de usuario
    $('#dropdownMenuUsuario').click(function (){
        $(".dropdown-menu").toggleClass('show');
    });


    /*        $(".collapse-icon").click(function(evento){
          if ( $("#sidebar-container").hasClass("sidebar-collapsed")){
             $("#dark-background").css("display", "block");
          }else{
             $("#dark-background").css("display", "none");
          }
       });
             */


    $('.version-content-open').click(function(event){
    event.preventDefault();
        if ($('.divModal').is(':visible')) {
    } else {
        $('.divModal').show();
        $('.contentModal').show();
    }
    });

    $('.closeMyModal').click(function(){
        $('.divModal').hide();
        $('.contentModal').hide();
    });
});

