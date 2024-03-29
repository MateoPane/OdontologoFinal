window.addEventListener("load", () => {
  loadData();
  const formulario = document.querySelector("#add_new_dentist");
  formulario.addEventListener("submit", (e) => {
    e.preventDefault()
    submit()
  });

});

function loadData() {
  let id = localStorage.getItem("dentist-id");
  const url = "/odontologos/" + id;
  const settings = {
    method: "GET",
  };

  fetch(url, settings)
    .then((response) => response.json())
    .then((dentist) => {
      let nameField = document.querySelector("#nombre");
      let lastnameField = document.querySelector("#apellido");
      let licenseField = document.querySelector("#matricula");

      nameField.value = dentist.nombre;
      lastnameField.value = dentist.apellido;
      licenseField.value = dentist.matricula;
    });
}

function submit() {
  const formData = {
    nombre: document.querySelector("#nombre").value,
    apellido: document.querySelector("#apellido").value,
    matricula: document.querySelector("#matricula").value,
  };
  let id = localStorage.getItem("dentist-id");

  const url = "/odontologos/" + id;
  const settings = {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(formData),
  };

  fetch(url, settings)
    .then((response) => {
        if (response.ok) {
            window.location.href = "/listado_odontologo.html";
        }
    })
}
