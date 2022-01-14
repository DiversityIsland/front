async function updateFavItems() {
    let response = await fetch('/api/favorites/popularItem');
    let items = await response.json();

    for (let i = 0; i < items.length; i++) {
        document.getElementById("items").innerHTML +=
            '                   <div class="card" style="widtclassNamerem; margin-right: 15px; margin-top: 20px;">\n' +
            '                       <img src='+items[i].images[0]+' class="card-img-top" alt="popular_item" style=" height: 180px;">' +
            '                           <div class="card-body">\n' +
            '                                <h5 class="card-title">'+items[i].name+'</h5>' +
            '                                <p class="card-text">'+items[i].description+'</p>' +
            '                                <div class="d-flex">\n' +
            '                                    <a href="#" class="btn btn-danger" onclick=addCartItem('+items[i].id+','+items[i].shopId+'); style="width: 150px;">В корзину</a>' +
            '                                    <a href="/product/'+items[i].id+'" class="btn btn-primary" style="margin-left: 10px;">Смотреть</a>' +
            '                                </div>' +
            '                           </div>' +
            '                   </div>';
    }
}
updateFavItems();

async function updateFavShops() {
    let response = await fetch('/api/favorites/popularShop');
    let shops = await response.json();
    for (let i = 0; i < shops.length; i++) {
        document.getElementById("shops").innerHTML +=
            '                   <div class="card" style="width: 18rem; margin-right: 15px; margin-top: 20px; overflow: hidden;">\n' +
            '                       <img src='+shops[i].logo+' class="card-img-top" alt="popular_shop" style=" height: 180px;">' +
            '                           <div class="card-body">\n' +
            '                                <h5 class="card-title">'+shops[i].name+'</h5>' +
            '                                <p class="card-text">'+shops[i].description+'</p>' +
            '                                <div class="d-flex">\n' +
            '                                    <a href="#" class="btn btn-danger">Перейти в магазин</a>' +
            '                                </div>' +
            '                           </div>' +
            '                   </div>';
    }
}
updateFavShops();