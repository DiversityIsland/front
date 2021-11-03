async function favoriteButtonItem(itemId) {
    let itemExists = await fetch("/api/favorites/existsItem/" + itemId).then(res => res.text());
    let buttonDiv = document.getElementById("favoriteButton"+itemId);
    let buttonDivId = "favoriteButtonItem" + itemId;
    if (itemExists === 'true') {
        buttonDiv.innerHTML = `<button type="button"  id=${buttonDivId} class="btn btn-info" >В избранное</button>`;
    } else if(itemExists === 'false'){
        buttonDiv.innerHTML = `<button type="button"  id=${buttonDivId} class="btn btn-secondary">В избранное</button>`;
    }
    let functionItem = "#favoriteButtonItem" + itemId;

    $(functionItem).click(function () {
        addItemToFavorites(itemId);
        if ($(this).hasClass('btn btn-info')) {
            deleteFavoriteItem(itemId)
            $(this).removeClass('btn btn-info');
            $(this).addClass('btn btn-secondary');
        } else {
            $(this).removeClass('btn btn-secondary');
            $(this).addClass('btn btn-info');
        }
    });
}


async function addItemToFavorites(itemId) {

    let url = new URL("http://localhost:8888/api/favorites/items/add/"+itemId)
    const response = await fetch(url, {
        headers: { "Content-Type": "application/json; charset=utf-8" },
        method: 'PATCH',
        body: null
    })
}

async function deleteFavoriteItem(id){
    let url = new URL("http://localhost:8888/api/favorites/items/delete/"+id);

    const response = await fetch(url, {
        headers: { "Content-Type": "application/json; charset=utf-8" },
        method: 'DELETE'
    })
}