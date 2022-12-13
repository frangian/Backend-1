window.addEventListener('load', function () {
    const formulario = document.querySelector('#update_turno_form');
    formulario.addEventListener('submit', (e) => {
        e.preventDefault();
        const formData = {
            id: document.querySelector('#turno_id').value,
            fecha: document.querySelector('#fecha').value,
            pacienteId: document.querySelector('#pacienteId').value,
            odontologoId: document.querySelector('#odontologoId').value,
        };
        const url = '/turnos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        fetch(url,settings)
        .then(response => {
            if (response.status == 404){
                alert("Las modificaciones no fueron ejecutadas");
            }
            window.location.replace("/turno_list.html");
        })
    })
 });

function findBy(id) {
    const url = '/turnos'+"/"+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        let turno = data;
        document.querySelector('#turno_id').value = turno.id;
        document.querySelector('#fecha').value = turno.fecha;
        document.querySelector('#pacienteId').value = turno.pacienteId;
        document.querySelector('#odontologoId').value = turno.odontologoId;
        document.querySelector('#div_turno_updating').style.display = "block";
        })
    .catch(error => {
        alert("Error: " + error);
    })
}