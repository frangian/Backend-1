function deleteBy(id){
    const url = '/pacientes/'+ id;
    const settings = {
        method: 'DELETE'
    }
    fetch(url,settings)
    .then(response => {
        if (response.status == 404){
            alert("Error al eliminar");
        }
        else if (response.status == 200){
            let row_id = "#tr_" + id;
            document.querySelector(row_id).remove();
        }
    })
}