class ModeratorApi {
    constructor() {
        this._url = 'http://localhost';
        this._head = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Referer': null,
        }
    }

    _checkResponse(res) {
        if(res.ok) {
            return res.json();
        }
        return Promise.reject(res.status);
    }

    // =========================== Запросы на товары ===========================
    getUnmoderatedItems() {
        return fetch(this._url + `/moderate/items`)
            .then(res => this._checkResponse(res))
    }

    getUnmoderatedItem(id) {
        return fetch(this._url + `/moderate/items/getOneUnmoderatedItem/${id}`)
            .then(res => this._checkResponse(res))
    }

    getUnmoderatedItemsCount() {
        return fetch(this._url + `/moderate/items/getUnmoderatedItemsCount`)
            .then(res => this._checkResponse(res))
    }

    updateItem(item) {
        return fetch(this._url + `/moderate/items/editItem`, {
            method: 'PUT',
            headers: this._head,
            body: JSON.stringify(item)
        })
            .then(res => this._checkResponse(res))
    }


    // =========================== Запросы на магазины ===========================
    getUnmoderatedShops(){
        return fetch( this._url + `/moderate/shops`)
            .then(res => this._checkResponse(res))
    }

    getUnmoderatedShop(id){
        return fetch(this._url + `/moderate/shops/getOneUnmoderatedShop/${id}`)
            .then(res => this._checkResponse(res))
    }

    getUnmoderatedShopsCount(){
        return fetch(this._url + `/moderate/shops/getUnmoderatedShopsCount`)
            .then(res => this._checkResponse(res))
    }

    updateShop(shop) {
        return fetch(this._url + `/moderate/shops/editShop`, {
            method: 'PUT',
            headers: this._head,
            body: JSON.stringify(shop)
        })
            .then(res => this._checkResponse(res))
    }


    // =========================== Запросы на отзывы ===========================
    getUnmoderatedReviews(){
        return fetch(this._url + `/moderate/reviews`)
            .then(res => this._checkResponse(res))
    }

    getUnmoderatedReview(id){
        return fetch(this._url + `/moderate/reviews/getOneUnmoderatedReview/${id}`)
            .then(res => this._checkResponse(res))
    }

    getUnmoderatedReviewsCount(){
        return fetch(this._url + `/moderate/reviews/getUnmoderatedReviewsCount`)
            .then(res => this._checkResponse(res))
    }

    updateReview(review) {
        return fetch(this._url + `/moderate/reviews/editReview`, {
            method: 'PUT',
            headers: this._head,
            body: JSON.stringify(review)
        })
            .then(res => this._checkResponse(res))
    }

}

export {ModeratorApi};

