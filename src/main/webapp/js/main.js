var id_usuario = new URL(location.href).searchParams.get("id_usuario");
var user;

$(document).ready(function () {

    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });

    getUsuario().then(function () {
        
        $("#mi-perfil-btn").attr("href","profile.html?id_usuario=" + id_usuario);
        
       // $("#user-saldo").html(user.saldo.toFixed(2) + "$");

        getDestino(false, "ASC");

        $("#ordenar-genero").click(ordenarDestino); //AQUÍ CREAR OrdenarDestino
    });
});


async function getUsuario() {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioPedir",
        data: $.param({
            id_usuario: id_usuario
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                user = parsedResult;
            } else {
                console.log("Error recuperando los datos del usuario");
            }
        }
    });

}
function getDestino(ordenar, orden) {    //getDestino

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletDestinoListar",     //ServletPeliculaListar
        data: $.param({
            ordenar: ordenar,
            orden: orden
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                mostrarDestino(parsedResult);    //mostrarDestino
            } else {
                console.log("Error recuperando los datos de los Destino");
            }
        }
    });
}
function mostrarDestino(destino) { 

    let contenido = "";

    $.each(destino, function (index, destino) {     //destino

        destino = JSON.parse(destino);   //Destino
        //let precio;

        /*if (destino.copias > 0) {

            if (user.premium) {

                if (destino.novedad) {
                    precio = (2 - (2 * 0.1));
                } else {
                    precio = (1 - (1 * 0.1));
                }
            } else {
                if (destino.novedad) {
                    precio = 2;
                } else {
                    precio = 1;
                }
            } 

            contenido += '<tr><th scope="row">' + destino.id_destino + '</th>' +
                    '<td>' + destino.planes + '</td>' +
                    //'<td>' + destino.precio + '</td>' +
                    '<td>' + destino.ciudad + '</td>' +
                    //'<td>' + destino.copias + '</td>' +
                    '<td><input type="checkbox" name="novedad" id_destino="novedad' + destino.id_destino + '" disabled ';
            /*if (destino.novedad) {
                contenido += 'checked';
            }
            contenido += '></td>' +
                    '<td>' + precio + '</td>' +
                    '<td><button onclick="pedirReservacion(' + destino.id_destino + ',' + precio + ');" class="btn btn-success" ';
            if (user.saldo < precio) {
                contenido += ' disabled ';
            

            contenido += '>Reservar</button></td></tr>'

        }*/
    });
    $("#destino-tbody").html(contenido);  //destino-tbody
    
}
function ordenarDestino() { //desde aquí

    if ($("#icono-ordenar").hasClass("fa-sort")) {
        getDestino(true, "ASC");
        $("#icono-ordenar").removeClass("fa-sort");
        $("#icono-ordenar").addClass("fa-sort-down");
    } else if ($("#icono-ordenar").hasClass("fa-sort-down")) {
        getDestino(true, "DESC");
        $("#icono-ordenar").removeClass("fa-sort-down");
        $("#icono-ordenar").addClass("fa-sort-up");
    } else if ($("#icono-ordenar").hasClass("fa-sort-up")) {
        getDestino(false, "ASC");
        $("#icono-ordenar").removeClass("fa-sort-up");
        $("#icono-ordenar").addClass("fa-sort");
    }
}
function pedirReservacion(id_destino, precio) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletReservacionPedir",
        data: $.param({
            id_destino: id_destino,
            id_usuario: id_usuario

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                restarDinero(precio).then(function () {
                    location.reload();
                })
            } else {
                console.log("Error en la reserva de la película");
            }
        }
    });
}


async function restarDinero(precio) {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioRestarDinero",
        data: $.param({
            id_usuario: id_usuario
            //saldo: parseFloat(user.saldo - precio)

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                //console.log("Saldo actualizado");
            } else {
                console.log("Error en el proceso de pago");
            }
        }
    });
}