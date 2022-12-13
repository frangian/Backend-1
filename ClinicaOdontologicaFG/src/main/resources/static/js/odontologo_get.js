window.addEventListener('load', function () {
    const url = '/odontologos';
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {

        for(odontologo of data){
            var table = document.getElementById("odontologoTable");
            var odontologoRow = table.insertRow();
            let tr_id = 'tr_' + odontologo.id;
            odontologoRow.id = tr_id;

            let updateButton = '<button' +
                              ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                              ' type="button" onclick="findBy('+odontologo.id+')" class="btn btn-info btn_id">' +
                              odontologo.id +
                              '</button>';

            let deleteButton = '<button' +
                              ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                              ' type="button" onclick="deleteBy('+odontologo.id+')" class="btn btn-danger btn_delete">' +
                              '&times' +
                              '</button>';

            odontologoRow.innerHTML = '<td>' + updateButton + '</td>' +
                    '<td class=\"td_nombre\">' + odontologo.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_apellido\">' + odontologo.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_matricula\">' + odontologo.matricula.toUpperCase() + '</td>' +
                    '<td>' + deleteButton + '</td>';
        };
    })

});