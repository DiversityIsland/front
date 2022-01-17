class ModeratorApi {
    constructor() {
        this._url = 'http://localhost:8888/moderator/api';
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
        return fetch(this._url + `/items/getUnmoderatedItems`)
            .then(res => this._checkResponse(res))
    }

    getUnmoderatedItem(id) {
        return fetch(this._url + `/items/getOneUnmoderatedItem/${id}`)
            .then(res => this._checkResponse(res))
    }

    getUnmoderatedItemsCount() {
        return fetch(this._url + `/items/getUnmoderatedItemsCount`)
            .then(res => this._checkResponse(res))
    }

    updateItem(item) {
        return fetch(this._url + `/items/editItem`, {
            method: 'PUT',
            headers: this._head,
            body: JSON.stringify(item)
        })
            .then(res => this._checkResponse(res))
    }


    // =========================== Запросы на магазины ===========================
    getUnmoderatedShops(){
        return fetch(this._url + `/shops/getUnmoderatedShops`)
            .then(res => this._checkResponse(res))
    }

    getUnmoderatedShop(id){
        return fetch(this._url + `/shops/getOneUnmoderatedShop/${id}`)
            .then(res => this._checkResponse(res))
    }

    getUnmoderatedShopsCount(){
        return fetch(this._url + `/shops/getUnmoderatedShopsCount`)
            .then(res => this._checkResponse(res))
    }

    updateShop(shop) {
        console.log(shop)
        return fetch(this._url + `/shops/editShop`, {
            method: 'PUT',
            headers: this._head,
            body: JSON.stringify(shop)
        })
            .then(res => this._checkResponse(res))
    }


    // =========================== Запросы на отзывы ===========================
    getUnmoderatedReviews(){
        return fetch(this._url + `/reviews/getUnmoderatedReviews`)
            .then(res => this._checkResponse(res))
    }

    getUnmoderatedReview(id){
        return fetch(this._url + `/reviews/getOneUnmoderatedReview/${id}`)
            .then(res => this._checkResponse(res))
    }

    getUnmoderatedReviewsCount(){
        return fetch(this._url + `/reviews/getUnmoderatedReviewsCount`)
            .then(res => this._checkResponse(res))
    }

    updateReview(review) {
        return fetch(this._url + `/reviews/editReview`, {
            method: 'PUT',
            headers: this._head,
            body: JSON.stringify(review)
        })
            .then(res => this._checkResponse(res))
    }


}

export {ModeratorApi};

