window.addEventListener('load', function () {
    const formulario = document.querySelector('#add_new_paciente');
    formulario.addEventListener('submit', (e) => {
        e.preventDefault();
        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            domicilio: {calle: document.querySelector('#calle').value,
                        numero: document.querySelector('#numero').value,
                        localidad: document.querySelector('#localidad').value,
                        provincia: document.querySelector('#provincia').value},
            dni: document.querySelector('#dni').value,
            fechaIngreso: document.querySelector('#fechaAlta').value,

        };

        const url = '/pacientes';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {

                if(data.status == 400){
                    let errorAlert = '<div class="alert alert-warning alert-dismissible fade show" role="alert">' +
                    '<div> <p>No se pudo guardar el paciente. </p></div>' +
                    '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>';
                    document.querySelector('#response').innerHTML = errorAlert;
                    document.querySelector('#response').style.display = "block";
                }
                else {
                    let successAlert = '<div class="alert alert-success alert-dismissible fade show" role="alert">' +
                    '<div> <p>Se agregó correctamente el paciente</p> </div>' +
                    '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>';
                    document.querySelector('#response').innerHTML = successAlert;
                    document.querySelector('#response').style.display = "block";
                    resetUploadForm();
                }
            })
            .catch(error => {

                let errorAlert = '<div class="alert alert-warning alert-dismissible fade show" role="alert">' +
                '<div> <p>No se pudo guardar el paciente. </p></div>' +
                '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>';
                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            })
    });


    const resetUploadForm = () => {
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#calle').value = "";
        document.querySelector('#numero').value = "";
        document.querySelector('#localidad').value = "";
        document.querySelector('#provincia').value = "";
        document.querySelector('#dni').value = "";
        document.querySelector('#fechaAlta').value = "";
    }

});