async function updateUser() {
    let url = new URL("http://localhost:8888/api/user")
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json; charset=utf-8",
        },

        body: JSON.stringify({
            username: document.getElementById(  'usernameSave').value,
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
            gender: document.getElementById('genderSave').value,
        })
    })
    let userData = await response.json();
    return userData;
}