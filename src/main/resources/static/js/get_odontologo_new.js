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
        var list = document.getElementById("dentist-list");
        list.innerHTML += buildListItem(dentist);
        let btn_id = "delete-btn-" + dentist.id;
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

function buildListItem(dentist) {
  return `<div class="flex border-b-2 py-3 last:border-0">
  <div class="w-1/6">
    <div
      class="bg-indigo-300 text-white rounded-full w-14 aspect-square flex justify-center items-center"
    >
      <span class="heroicon heroicon-user text-3xl m-0"></span>
    </div>
  </div>
  <div class="w-4/6">
    <p class="text-lg font-extralight">
      <span>${dentist.nombre}</span>
      <span>${dentist.apellido}</span>
    </p>
    <small>
      <span>Matricula: </span>
      <span>${dentist.matricula}</span>
    </small>
  </div>
  <div class="w-1/6 flex justify-center items-center gap-x-2">
    <button
      id="edit-btn-${dentist.id}"
      class="bg-cyan-400 text-white rounded-md w-10 aspect-square flex justify-center items-center"
    >
      <span class="heroicon heroicon-edit text-2xl m-0"></span>
    </button>
    <button
      id="delete-btn-${dentist.id}"
      class="bg-red-500 text-white rounded-md w-10 aspect-square flex justify-center items-center"
    >
      <span class="heroicon heroicon-trash text-2xl m-0"></span>
    </button>
  </div>
</div>`;
}
