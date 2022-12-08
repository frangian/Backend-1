function deleteBy(id){
    const url = '/odontologos';
    const formData = {
        id: id,
    };

    const settings = {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData)
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