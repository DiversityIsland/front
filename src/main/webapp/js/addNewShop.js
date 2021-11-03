function saveShop() {
    console.log("adding shop")

    $.ajax('http://localhost:8888/api/shop/add', {
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            name: document.getElementById('nameAddShopSave').value,
            email: document.getElementById('emailAddShopSave').value,
            phone: document.getElementById('phoneAddShopSave').value,
            description: document.getElementById('descriptionAddShopSave').value,
            location: document.getElementById('locationAddShopSave').value,
            logo: document.getElementById('logoAddShopSave').value,
            logoarray: document.getElementById('logoarrayAddShopSave').value
        })
    })
}
