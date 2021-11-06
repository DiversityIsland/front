async function update() {
    let response = await fetch('/api/category/');
    let categories = await response.json();

    for (let i = 0; i < categories.length; i++) {
        document.getElementById("categories").innerHTML +=
            '<li class="nav-item"><a id='+categories[i].id+' href="#" class="nav-link link-dark sideMenu" aria-current="page">'+categories[i].name+'</a></li>';
    }


    /**
     * обработчик
     * обработчик на все кнопки в навбар кроме "популярное"
     */
    $('.sideMenu').on('click', function () {
        //переключение стилей кнопок навбара и контейнера с контентом
        toggleClassesContent($(this));
        toggleClassesNavBar($(this));
        //перезапись категории в заголовок
        let category = $(this).html();
        dinamic_head.html(category);
        //перезапись динамического контента, товары по категории
        searchByCategory($(this));
    })

    /**
     * обработчик
     * обработчик на кнопке "популярное" в навбар
     */
    $('#popular_show_btn').on('click', function () {
        popular_show_content.addClass('show');
        dinamic_show_content.removeClass('show');
        dinamic_inner_content.html("");

        //toggleClassesNavBar($(this));
        $('.active').removeClass('active').addClass('link-dark');
        $(this).removeClass('link-dark');
        $(this).addClass('active');
    })
}
update();


async function addCartItem(itemId, shopId) {
    // let quant = document.getElementById("itemQuant").value;
    let url = new URL("/api/cart/add")
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json; charset=utf-8",
        },
        body: JSON.stringify({
            quantity: 1,
            item: {
                id: itemId
            },
            shop: {
                id: shopId
            },
        })
    })
}


async function updateFav() {
    let response = await fetch('/api/favorites');
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
updateFav();

