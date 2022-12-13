window.addEventListener('load', function () {
    const formulario = document.querySelector('#add_new_turno');
    formulario.addEventListener('submit', (e) => {
        e.preventDefault();
        const formData = {
            pacienteId: document.querySelector('#pacienteId').value,
            odontologoId: document.querySelector('#odontologoId').value,
            fecha: document.querySelector('#fecha').value
        };

        const url = '/turnos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        let successAlert = '<div class="alert alert-success alert-dismissible fade show" role="alert">' +
        '<div> <p>Se agregó correctamente el turno</p> </div>' +
        '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>';

        let errorAlert = '<div class="alert alert-warning alert-dismissible fade show" role="alert">' +
        '<div> <p>No se pudo guardar el turno. </p></div>' +
        '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>';

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {

                if(data.status == 400){
                    document.querySelector('#response').innerHTML = errorAlert;
                    document.querySelector('#response').style.display = "block";
                }
                else {
                    document.querySelector('#response').innerHTML = successAlert;
                    document.querySelector('#response').style.display = "block";
                    resetUploadForm();
                }

            })
            .catch(error => {

                console.log(data)

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            })
    });


    const resetUploadForm = () => {
        document.querySelector('#turnoId').value = "";
        document.querySelector('#pacienteId').value = "";
        document.querySelector('#odontologoId').value = "";
        document.querySelector('#fecha').value = "";
    }

});