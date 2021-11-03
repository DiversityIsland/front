/**
 * контейнер куда генерируется контент
 * @type {*|Window.jQuery|HTMLElement}
 */
const content_inner = $('#content_container');
/**
 *
 */
const main_title = $('#main_title');
/**
 * кнопка популярных товаров в Navar
 * @type {*|Window.jQuery|HTMLElement}
 */
const popular_items_btn = $('#popular_product_btn');
/**
 * кнопка все товары в Navar
 * @type {*|Window.jQuery|HTMLElement}
 */
const all_items_btn = $('#all_product_btn');
/**
 * кнопка описание магазина в Navar
 * @type {*|Window.jQuery|HTMLElement}
 */
const shop_description_btn = $('#market_description_btn');
/**
 * select категорий
 * @type {*|Window.jQuery|HTMLElement}
 */
const search_category_select = $('#search_select');

/**
 * инпут и кнопа поиска
 * @type {*|Window.jQuery|HTMLElement}
 */
const search_input = $('#search_input');
const search_btn = $('#search_btn');

$(document).ready(function(){
    /**
     * Id shop на странице
     * @type {*|jQuery}
     */
    const SHOP_ID = $('#shop_id').attr("value");

    /**
     * генерируем популярные товары при загрузке страницы
     */
    getPopularItems(SHOP_ID);

    /**
     * обработчик на кнопке популярных товаров
     */
    $(popular_items_btn).on('click', function (e) {
        e.preventDefault();
        getPopularItems(SHOP_ID);
        toggleNavBar($(this));
        main_title.html('Популярные товары:');
    })

    /**
     * Обработчик на кнопке все товары
     */
    $(all_items_btn).on('click', function (e) {
        e.preventDefault();
        getAllItems(SHOP_ID);
        toggleNavBar($(this));
        main_title.html('Все товары:');
    })
    /**
     * обработчик на кнопке описание магазина
     */
    $(shop_description_btn).on('click', function (e) {
        e.preventDefault();
        getShopDescription(SHOP_ID);
        toggleNavBar($(this));
        main_title.html('Описание магазина:');
    })

    /**
     * обработчик селекта (категории)
     */
    $(search_category_select).on('change', function(e) {
        let category = $('#search_select option:selected').text();
        if(category === "Все категории") {
            getAllItems(SHOP_ID);
        } else {
            const CATEGORY_ID = $('#search_select option:selected').val();
            getItemsByCategory(SHOP_ID, CATEGORY_ID);
        }

        main_title.html(category);
    })

    /**
     * обработчик кнопки найти
     */
    $(search_btn).on('click', function (e) {
        let itemName = $(search_input).val();
        main_title.html("");
        main_title.html("Поиск по:" + itemName);
        searchItemsByName(SHOP_ID, itemName);
    })

});

/**
 * Переключение стилей кнопок в Navbar панели
 * @param e
 */
function toggleNavBar(e) {
    $('.active').removeClass('active').addClass('link-dark');
    $(e).addClass('active').removeClass('link-dark');
}

/**
 * Метод генерирует товары по категориям
 */
async function getItemsByCategory(shopId, categoryId) {
    let url = "/showcase/" + shopId + "/category/" + categoryId;
    let response = await fetch(url);
    if(response.ok) {
        let json = await response.json();
        let insert_content = "";
        for(let i = 0; i < json.length; i++) {
            insert_content += '<div class="card" style="width: 18rem; margin-right: 15px; margin-top: 20px;">\n' +
                '                        <img src="' + json[i].images[0] + '" class="card-img-top" style="height: 180px;" alt="...">\n' +
                '                        <div class="card-body">\n' +
                '                            <h5 class="card-title">' + json[i].name + '</h5>\n' +
                '                            <p class="card-text">' + json[i].description + '</p>\n' +
                '                            <div class="d-flex">' +
                '                                    <a href="#" class="btn btn-danger" style="width: 150px;">В корзину</a>' +
                '                                    <a href="/product/' + json[i].id + '" class="btn btn-primary" style="margin-left: 10px;">Смотреть</a>' +
                '                            </div>' +
                '                        </div>\n' +
                '               </div>'
        }
        $('#content_container').html("");
        $('#content_container').html(insert_content);
    }
}
/**
 * Метод генерирует товары по их наименованию (поиск товара)
 */
async function searchItemsByName(shopId, itemName) {
    let url = "/showcase/" + shopId + "/search/" + itemName;
    let response = await fetch(url);
    if(response.ok) {
        let json = await response.json();
        let insert_content = "";
        for(let i = 0; i < json.length; i++) {
            insert_content += '<div class="card" style="width: 18rem; margin-right: 15px; margin-top: 20px;">\n' +
                '                        <img src="' + json[i].images[0] + '" class="card-img-top" style="height: 180px;" alt="...">\n' +
                '                        <div class="card-body">\n' +
                '                            <h5 class="card-title">' + json[i].name + '</h5>\n' +
                '                            <p class="card-text">' + json[i].description + '</p>\n' +
                '                            <div class="d-flex">' +
                '                                    <a href="#" class="btn btn-danger" style="width: 150px;">В корзину</a>' +
                '                                    <a href="/product/' + json[i].id + '" class="btn btn-primary" style="margin-left: 10px;">Смотреть</a>' +
                '                            </div>' +
                '                        </div>\n' +
                '               </div>'
        }
        $('#content_container').html("");
        $('#content_container').html(insert_content);
    }
}
/**
 * Метод генерирует все товары магазина
 * @param id
 * @returns {Promise<void>}
 */
async function getAllItems(id) {
    let url = "/showcase/" + id + "/items";
    let response = await fetch(url);
    if(response.ok) {
        let json = await response.json();
        let insert_content = "";
        for(let i = 0; i < json.length; i++) {
            insert_content += '<div class="card" style="width: 18rem; margin-right: 15px; margin-top: 20px;">\n' +
                '                        <img src="' + json[i].images[0] + '" class="card-img-top" style="height: 180px;" alt="...">\n' +
                '                        <div class="card-body">\n' +
                '                            <h5 class="card-title">' + json[i].name + '</h5>\n' +
                '                            <p class="card-text">' + json[i].description + '</p>\n' +
                '                            <div class="d-flex">' +
                '                                    <a href="#" class="btn btn-danger" style="width: 150px;">В корзину</a>' +
                '                                    <a href="/product/' + json[i].id + '" class="btn btn-primary" style="margin-left: 10px;">Смотреть</a>' +
                '                            </div>' +
                '                        </div>\n' +
                '               </div>'
        }
        $('#content_container').html("");
        $('#content_container').html(insert_content);
    }
}

/**
 * Метод генерирует популярные итемы магазина
 * @param id
 * @returns {Promise<void>}
 */
async function getPopularItems(id) {
    let url = "/showcase/" + id + "/popular";
    let response = await fetch(url);
    if(response.ok) {
        let json = await response.json();
        let insert_content = "";
        for(let i = 0; i < json.length; i++) {
            insert_content += '<div class="card" style="width: 18rem; margin-right: 15px; margin-top: 20px;">' +
                '                        <img src="' + json[i].images[0] + '" class="card-img-top" style="height: 180px;"  alt="...">' +
                '                        <div class="card-body">\n' +
                '                            <h5 class="card-title">' + json[i].name + '</h5>' +
                '                            <p class="card-text">' + json[i].description + '</p>' +
                '                            <div class="d-flex">' +
                '                                    <a href="#" class="btn btn-danger" style="width: 150px;">В корзину</a>' +
                '                                    <a href="/product/' + json[i].id + '" class="btn btn-primary" style="margin-left: 10px;">Смотреть</a>' +
                '                            </div>' +
                '                        </div>' +
                '               </div>'
        }
        content_inner.html("");
        content_inner.html(insert_content);
    }
}

/**
 * Метод генерирует описание магазина
 * @param id
 * @returns {Promise<void>}
 */
async function getShopDescription(id) {
    let url = "/showcase/" + id + "/description";
    let response = await fetch(url);
    if(response.ok) {
        let json = await response.json();
        let insert_content = '<div><img src="' + json.logo + '" class="shadow" alt="shop_logo" width="350px" height="180px"><p><h3>' + json.name + '</h3></p> <p><b>Email: </b>' + json.email + '</p> <p><b>Телефон: </b>' + json.phone + '</p> <p><b>Город: </b>' + json.location + '</p> <p><b>Описание: </b>' + json.description + '</p></div>';

        $('#content_container').html("");
        $('#content_container').html(insert_content);
    }
}