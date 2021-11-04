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

async function updateFav() {
    let response = await fetch('/api/favorites');
    let categories = await response.json();

    for (let i = 0; i < categories.length; i++) {
        document.getElementById("items").innerHTML +=
            '123';
    }
}
updateFav();

