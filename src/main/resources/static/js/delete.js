windows.addEventListener('load', function() {

}
document.addEventListener("DOMContentLoaded", function() {
    // Hacer una solicitud AJAX para obtener la lista de pacientes y actualizar la tabla
    fetch('/api/pacientes')
        .then(response => response.json())
        .then(data => actualizarTabla(data));
});

function actualizarTabla

});