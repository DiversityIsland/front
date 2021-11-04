async function update() {
    let response = await fetch('/api/category/');
    let categories = await response.json();

    for (let i = 0; i < categories.length; i++) {
        document.getElementById("categories").innerHTML +=
            '<li class="nav-item"><a id='+categories[i].id+' href="#" class="nav-link link-dark sideMenu" aria-current="page">'+categories[i].name+'</a></li>';
    }

    $('.sideMenu').on('click', function () {
        //переключение стилей кнопок навбара и контейнера с контентом
        toggleClassesContent($(this));
        toggleClassesNavBar($(this));
        //перезапись категории в заголовок
        let category = $(this).html();
        $('#dinamic_head').html(category);
        //перезапись динамического контента, товары по категории
        searchByCategory($(this));
    })
}
update();





