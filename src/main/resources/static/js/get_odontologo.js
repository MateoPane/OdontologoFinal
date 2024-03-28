window.addEventListener("load", function () {
  loadData();
});

function loadData() {
  const url = "/odontologos/listar";
  const settings = {
    method: "GET",
  };

  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      for (dentist of data) {
        var table = document.getElementById("dentistTableBody");
        var dentistRow = table.insertRow();
        let tr_id = "tr_" + dentist.id;
        dentistRow.id = tr_id;
        let btn_id = "delete-btn-" + dentist.id;
        dentistRow.innerHTML =
          '<td class="td_id">' +
          dentist.id +
          "</td>" +
          '<td class="td_nombre">' +
          dentist.nombre.toUpperCase() +
          "</td>" +
          '<td class="td_apellido">' +
          dentist.apellido.toUpperCase() +
          "</td>" +
          '<td class="td_matricula">' +
          dentist.matricula +
          "</td>" +
          '<td class="td_"><button id="' +
          btn_id +
          '">borrar</button></td>';

        let btn = document.querySelector(`#${btn_id}`);
        btn.addEventListener("click", () => deleteAction(dentist.id));
      }
    });
}

function deleteAction(id) {
  const url = "/odontologos/" + id;
  const settings = {
    method: "DELETE",
  };

  fetch(url, settings)
    .then((response) => response.json())
    .then(() => {
      clearTable();
      loadData();
    });
}

function clearTable() {
  var table = document.getElementById("dentistTableBody");
  while (table.firstChild) {
    table.removeChild(table.firstChild);
  }
}
