$(document).ready(function() {

cargarUsuario();
});

async function cargarUsuario(){

    let email = localStorage.email
const request = await fetch('api/perfil/'+email, {
    method: 'GET',
    headers: getHeaders()
  });

const usuarios = await request.json();
console.log(usuarios);
    for (let element of usuarios){


      document.getElementById('input-usuario').value = element.id;
      document.getElementById('input-contrasena').value = "nuevaContraseña";
      document.getElementById('input-nombre').value = element.nombre;
      document.getElementById('input-apellidos').value = element.apellido;
      document.getElementById('input-email').value = element.email;
      document.getElementById('input-telefono').value = element.telefono;

    }
}

async function actualizarUsuario(){

 let datos = {}

     datos.nombre = document.getElementById('input-nombre').value;
     datos.apellido = document.getElementById('input-apellidos').value;
     datos.telefono = document.getElementById('input-telefono').value;
     datos.password = document.getElementById('input-contrasena').value;
     datos.email = document.getElementById('input-email').value;

    const request = await fetch('api/actualizar', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });

  alert("Usuario Actualizado");
  location.reload();


}


function getHeaders(){
return{
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': localStorage.token


}
}