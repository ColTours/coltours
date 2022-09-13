$(document).ready(function () {

    $("#form-login").submit(function (event) {

        event.preventDefault();
        autenticarUsuario();
    });



});

function autenticarUsuario() {

    let id_usuario = $("#id_usuario").val();
    let contrasena = $("#contrasena").val();

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioLogin",
        data: $.param({
            username: id_usuario,
            contrasena: contrasena
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult != false) {
                $("#login-error").addClass("d-none");
                let id_usuario = parsedResult['id_usuario'];
                document.location.href = "home.html?id_usuario=" + id_usuario;
            } else {
                $("#login-error").removeClass("d-none");
            }
        }
    });
}



