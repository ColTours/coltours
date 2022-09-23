var id_usuario = new URL(location.href).searchParams.get("id_usuario");
var user;

$(document).ready(function () {

    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });

    getUsuario().then(function () {
        
        $("#mi-perfil-btn").attr("href","profile.html?id_usuario=" + id_usuario);
        
        $("#user-saldo").html(user.saldo.toFixed(2) + "$");

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
        let precio;

        if (destino.copias > 0) {

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
            if (destino.novedad) {
                contenido += 'checked';
            }
            contenido += '></td>' +
                    '<td>' + precio + '</td>' +
                    '<td><button onclick="alquilarPelicula(' + destino.id_destino + ',' + precio + ');" class="btn btn-success" ';
            if (user.saldo < precio) {
                contenido += ' disabled ';
            }

            contenido += '>Reservar</button></td></tr>'

        }
    });
    $("#destino-tbody").html(contenido);
}

