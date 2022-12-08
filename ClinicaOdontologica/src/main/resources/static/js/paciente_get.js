window.addEventListener('load', function () {
    const url = '/pacientes';
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {

        for(paciente of data){
            var table = document.getElementById("pacienteTable");
            var pacienteRow = table.insertRow();
            let tr_id = 'tr_' + paciente.id;
            pacienteRow.id = tr_id;

            let updateButton = '<button' +
                              ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                              ' type="button" onclick="findBy('+paciente.id+')" class="btn btn-info btn_id">' +
                              paciente.id +
                              '</button>';

            let deleteButton = '<button' +
                              ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
                              ' type="button" onclick="deleteBy('+paciente.id+')" class="btn btn-danger btn_delete">' +
                              '&times' +
                              '</button>';

            pacienteRow.innerHTML = '<td>' + updateButton + '</td>' +
                    '<td class=\"td_nombre\">' + paciente.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_apellido\">' + paciente.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_domicilio\">' + paciente.domicilio.calle + " N° "+ paciente.domicilio.numero +  " "+ paciente.domicilio.localidad + " " + paciente.domicilio.provincia + '</td>' +
                    '<td class=\"td_dni\">' + paciente.dni + '</td>' +
                    '<td class=\"td_fecha\">' + paciente.fechaIngreso + '</td>' +
                    '<td>' + deleteButton + '</td>';
        };
    })

});