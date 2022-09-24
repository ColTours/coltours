var id_usuario = new URL(location.href).searchParams.get("id_usuario");
var user;

$(document).ready(function () {


    fillUsuario().then(function () {

        $("#user-saldo").html("$" + user.saldo.toFixed());

        getAlquiladas(user.id_usuario);
    });

    $("#reservar-btn").attr("href", `home.html?id_usuario=${id_usuario}`);

    $("#form-modificar").on("submit", function (event) {

        event.preventDefault();
        modificarUsuario();
    });

    $("#aceptar-eliminar-cuenta-btn").click(function () {

        eliminarCuenta().then(function () {
            location.href = "index.html";
        })
    })

});

async function fillUsuario() {
    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioPedir",
        data: $.param({
            id_usuario: id_usuario,
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                user = parsedResult;

                $("#input-username").val(parsedResult.id_usuario);
                $("#input-contrasena").val(parsedResult.contrasena);
                $("#input-nombre").val(parsedResult.nombre);
                $("#input-apellidos").val(parsedResult.apellidos);
                $("#input-email").val(parsedResult.email);
                $("#input-direccion").val(parsedResult.direccion);
                $("#input-ciudad").val(parsedResult.ciudad);
                $("#input-telefono").val(parsedResult.telefono);
                //$("#input-saldo").val(parsedResult.saldo.toFixed(2));
                //$("#input-premium").prop("checked", parsedResult.premium);

            } else {
                console.log("Error recuperando los datos del usuario");
            }
        }
    });
}

function getAlquiladas(id_usuario) {    ///método que averiguar


    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletDestinoListar",
        data: $.param({
            id_usuario: id_usuario,
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {

                mostrarHistorial(parsedResult)

            } else {
                console.log("Error recuperando los datos de las reservas");
            }
        }
    });
}

function mostrarHistorial(destino) {
    let contenido = "";
    if (destino.length >= 1) {
        $.each(destino, function (index, destino) {
            destino = JSON.parse(destino);

            contenido += '<tr><th scope="row">' + destino.id_destino + '</th>' +
                    '<td>' + destino.planes + '</td>' +
                    '<td>' + destino.precio + '</td>' +
                    '<td>' + destino.ciudad + '</td>'
                    //'<td><input type="checkbox" name="novedad" id="novedad' + pelicula.id 
                    //+ '" disabled ';
            //if (pelicula.novedad) {
             //   contenido += 'checked'
            //}
            //contenido += '></td><td>' + pelicula.fechaAlquiler + '</td>' +
                 //   '<td><button id="devolver-btn" onclick= "devolverpelicula(' + pelicula.id 
                   // + ');" class="btn btn-danger">Devolver pelicula</button></td></tr>';

        });
        $("#historial-tbody").html(contenido);
        $("#historial-table").removeClass("d-none");
        $("#historial-vacio").addClass("d-none");

    } else {
        $("#historial-vacio").removeClass("d-none");
        $("#historial-table").addClass("d-none");
    }
}


function devolverDestino(id_destino) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletDestinoDevolver",  //ServletPeliculaDevolver
        data: $.param({
            id_usuario: id_usuario,
            id_destino: id_destino,
        }),
        success: function (result) {

            if (result != false) {

                location.reload();

            } else {
                console.log("Error al devolver el plan/destino");
            }
        }
    });

}

function modificarUsuario() {

    let id_usuario = $("#input-username").val();
    let contrasena = $("#input-contrasena").val();
    let nombre = $("#input-nombre").val();
    let apellidos = $("#input-apellidos").val();
    let email = $("#input-email").val();
    let direccion = $("#input-direccion").val();
    let ciudad = $("#input-ciudad").val();
    let telefono = $("#input-telefono").val();
    //let saldo = $("#input-saldo").val();
    //let premium = $("#input-premium").prop('checked');
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioModificar",
        data: $.param({
            id_usuario: id_usuario,
            contrasena: contrasena,
            nombre: nombre,
            apellidos: apellidos,
            email: email,
            direccion: direccion,
            ciudad: ciudad,
            telefono: telefono,
            //saldo: saldo,
            //premium: premium,
        }),
        success: function (result) {

            if (result != false) {
                $("#modificar-error").addClass("d-none");
                $("#modificar-exito").removeClass("d-none");
            } else {
                $("#modificar-error").removeClass("d-none");
                $("#modificar-exito").addClass("d-none");
            }

            setTimeout(function () {
                location.reload();
            }, 3000);

        }
    });

}

async function eliminarCuenta() {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioEliminar",
        data: $.param({
            id_usuario: id_usuario
        }),
        success: function (result) {

            if (result != false) {

                console.log("Usuario eliminado")

            } else {
                console.log("Error eliminando el usuario");
            }
        }
    });
}
