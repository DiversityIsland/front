function signin() {
   const requestForLogin = $.ajax("/api/auth", {
      method: 'POST',
      headers: {
         "Accept": "application/json",
         "Content-Type": "application/json; charset=utf-8",
      },
      data: {
         username: document.getElementById('username_input').value,
         password: document.getElementById('password_input').value,
      }
   })
   requestForLogin.done(() => location.reload())
}

