function signin() {
   const requestForLogin = $.ajax("http://localhost:8888/login", {
      method: 'POST',
      data: {
         j_username: document.getElementById('username_input').value,
         j_password: document.getElementById('password_input').value,
         code: document.getElementById('verification_code_input').value
      }
   })

   requestForLogin.done(() => location.reload())
}
