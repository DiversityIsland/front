function signin() {
   const requestForLogin = $.ajax("/api/auth", {
      method: 'POST',
      data: {
         username: document.getElementById('username_input').value,
         password: document.getElementById('password_input').value,
      }
   })
   requestForLogin.done(() => location.reload())
}