function saveUser() {
   console.log("saving User")

   const requestForSaveUser = $.ajax('http://localhost:8888/api/save', {
      method: 'POST',
      contentType: 'application/json',
      data: JSON.stringify({
         username: document.getElementById('usernameSave').value,
         email: document.getElementById('emailSave').value,
         password: document.getElementById('passwordSave').value,
         phone: document.getElementById('phoneSave').value,
         firstName: document.getElementById('first_nameSave').value,
         lastName: document.getElementById('last_nameSave').value,
         age: document.getElementById('ageSave').value,
         address: {
            cityIndex: document.getElementById('cityIndexSave').value,
            country: document.getElementById('countrySave').value,
            city: document.getElementById('citySave').value,
            street: document.getElementById('streetSave').value,
            house: document.getElementById('houseSave').value,
         },
         birthday: document.getElementById('birthdaySave').value,
         gender: document.getElementById('genderSave').value
      })
   })

   requestForSaveUser.done(userDto => {
      console.log(userDto)
      const requestForLogin = $.ajax("http://localhost:8888/login", {
         method: 'POST',
         data: {
            j_username: userDto.username,
            j_password: userDto.password
         }
      })

      requestForLogin.done(() => location.reload())
   })
}
