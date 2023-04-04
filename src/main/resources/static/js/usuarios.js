// Call the dataTables jQuery plugin
$(document).ready(function() {

actualizarEmailDelUsuario();


});

function actualizarEmailDelUsuario(){

    document.getElementById('txt-email-usuario').outerHTML =localStorage.email;
}


async function cargarUsuarios(){


  const request = await fetch('api/usuarios', {
    method: 'GET',
    headers: getHeaders()
  });
  const usuarios = await request.json();
  let listadoHtml = ''

  for (let element of usuarios){
    let botonEliminar = '<a href="#" onclick="eliminarUsuario('+element.id+'); " class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>'


  let telefonoTexto = element.telefono == null ? '-': element.telefono;
  let usuarioHtml = '<tr><td>'+element.id+'</td><td>'+element.nombre+' '+element.apellido+'</td><td>'+element.email+'</td><td>'+telefonoTexto+'</td><td>'+botonEliminar+'</td></tr>';
  listadoHtml += usuarioHtml;
  }


  document.querySelector("#usuarios tbody").outerHTML = listadoHtml;


}
function getHeaders(){
return{
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': localStorage.token


}



}

async function eliminarCuenta(){

    let email = localStorage.email

const request = await fetch('api/usuario/'+email, {
    method: 'DELETE',
    headers: getHeaders()
  });

  window.location.href = 'login.html'

}

async function eliminarUsuario(id){

if (!confirm("¿Desea eliminar este usuario¡")){
    return;
}

const request = await fetch('api/usuarios/'+id, {
    method: 'DELETE',
    headers: getHeaders()
  });

  location.reload()

}

