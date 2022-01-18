import {ModeratorApi} from "./ModeratorApi.js";

const api = new ModeratorApi();

// ============================ items =================================
function createItemsContent(arr) {
    const container = document.createElement("div");
    let html = ``;
    if(arr.length > 0) {
        arr.forEach(item => {
            html += `
            <div class="row" id="itemRow${item.id}">
                <div class="col-sm-6">
                    <div class="card" id="itemCard${item.id}">
                        <div class="row card-body">
                            <div class="col-sm-6">
                                <h5 class="card-title"><b>Наименование товара: </b>${item.name}</h5>
                                <p class="card-text" id="cardText"><b>Описание товара: </b>${item.description}<br>
                                    <b>Категории товара: </b>${item.categories}<br>
                                </p>
                                <button data-itemid="${item.id}" data-action="accept" class="btn btn-success">Одобрить</a>
                                <button data-itemid="${item.id}" data-action="decline" class="btn btn-danger">Отклонить</button>
                            </div>
                            <img class="col-sm-6 text-right" src="${item.images[0]}" alt="https://iproger.ru/content/news/46014.jpg"/>
                        </div>
                    </div>
                </div>
            </div>`
        })
    } else {
        html += `
            <div class="row">
                <div class="col-sm-6">
                    <div class="card" id="emptyItemList">
                        <div class="row card-body">
                            <div class="col-sm-6">
                                <h5 class="card-title"><b>Все товары отмодерированы!</h5>
                                <p class="card-text" id="cardText"><b>Больше товаров нет, но есть замечательный кот!<br></p>
                            </div>
                            <img class="col-sm-6 text-right" src="https://w-dog.ru/wallpapers/5/19/361416555427750/kot-kotenok-seryj-pushistyj-lapy-kogotki.jpg" alt="https://iproger.ru/content/news/46014.jpg"/>
                        </div>
                    </div>
                </div>
            </div>
            <br>`
    }
    container.insertAdjacentHTML("afterbegin", html);
    itemButtonsHandler(container);
    updateItemsAmount();
    return container;
}

function itemButtonsHandler(itemsContainer) {
    const page = document.getElementById("itemsCards");
    // Установка обработчиков на кнопку одобрения
    itemsContainer.querySelectorAll('[data-action="accept"]').forEach(el => {
        el.addEventListener("click", () => {
            api.getUnmoderatedItem(el.getAttribute('data-itemid'))
                .then(item => {
                    item.moderated = true;
                    item.moderateAccept = true;
                    return api.updateItem(item);
                })
                .then(() => api.getUnmoderatedItems())
                .then(res => {
                    page.innerHTML = '';
                    page.insertAdjacentElement('afterbegin', createItemsContent(res));
                })
        })
    })

    // Установка обработчиков на кнопку отклонения
    itemsContainer.querySelectorAll('[data-action="decline"]').forEach(el => {
        const id = el.getAttribute('data-itemid');
        const container = itemsContainer.querySelector(`#itemCard${id}`);
        const form = document.createElement("form");
        el.addEventListener("click", () => {
            if(container.querySelector(`#declineItem${id}`)) {
                return;
            }
            form.setAttribute("id", `declineItem${id}`)
            form.insertAdjacentHTML('afterbegin',`
                <input type="text" class="form-control" id="rejectedReasonTo${id}" name="rejectedReasonTo${id}" 
                placeholder="Введите причину отказа" style="width: 200px; height: 150px;" min="5">
                <button type="submit" class="btn btn-primary" id="rejectReason${id}">Отправить</button>
            `)
            form.addEventListener('submit', event => {
                event.preventDefault();
                api.getUnmoderatedItem(id)
                    .then(item => {
                        item.moderated = true;
                        item.moderateAccept = false;
                        item.moderatedRejectReason = form.elements[`rejectedReasonTo${id}`].value;
                        return api.updateItem(item)
                    })
                    .then(() => api.getUnmoderatedItems())
                    .then(res => {
                        page.innerHTML = '';
                        page.insertAdjacentElement('afterbegin', createItemsContent(res));
                    })
            })
        })
        container.insertAdjacentElement('beforeend',form);
    })
}

function updateItemsAmount() {
    api.getUnmoderatedItemsCount()
        .then(res => document.getElementById('v-pills-items-tab').textContent = `Товары ${res}`);
}


// ============================ shops =================================

function createShopsContent (arr) {
    const container = document.createElement("div");
    let html = ``;
    if(arr.length > 0) {
        arr.forEach(shop => {
            html += `
            <div class="row" id="shopRow${shop.id}">
                <div class="col-sm-6">
                    <div class="card" id="shopCard${shop.id}">
                        <div class="row card-body">
                            <div class="col-sm-6">
                            <h5 class="card-title"><b>Наименование магазина: </b>${shop.name}</h5>
                            <p class="card-text" id="cardText"><b>Описание магазина: </b>${shop.description}
                                <br>
                                <b>Email: </b> ${shop.email}
                                <br>
                                <b>Номер телефона: </b> ${shop.phone}
                            </p>
                            <button data-shopid="${shop.id}" data-action="accept" class="btn btn-success">Одобрить</a>
                            <button data-shopid="${shop.id}" data-action="decline" class="btn btn-danger">Отклонить</button>
                            </div>
                        <img class="col-sm-6 text-right" src="${shop.logo}" alt="https://iproger.ru/content/news/46014.jpg"/>
                        </div>
                    </div>
                </div>
            </div>
            <br>`
        })
    } else {
        html += `
            <div class="row">
                <div class="col-sm-6">
                    <div class="card" id="emptyItemList">
                        <div class="row card-body">
                            <div class="col-sm-6">
                                <h5 class="card-title"><b>Все магазины отмодерированы!</h5>
                                <p class="card-text" id="cardText"><b>Больше магазинов нет, но есть замечательный кот!<br></p>
                            </div>
                            <img class="col-sm-6 text-right" src="https://w-dog.ru/wallpapers/5/19/361416555427750/kot-kotenok-seryj-pushistyj-lapy-kogotki.jpg" alt="https://iproger.ru/content/news/46014.jpg"/>
                        </div>
                    </div>
                </div>
            </div>
            <br>`
    }
    container.insertAdjacentHTML("afterbegin", html);
    shopButtonsHandler(container);
    updateShopsAmount();
    return container;
}

function shopButtonsHandler(tabsContainer) {
    // Установка обработчиков на кнопку одобрения
    const page = document.getElementById("shopsCards");
    tabsContainer.querySelectorAll('[data-action="accept"]').forEach(el => {
        el.addEventListener("click", () => {
            api.getUnmoderatedShop(el.getAttribute('data-shopid'))
                .then(item => {
                    item.moderated = true;
                    item.moderateAccept = true;
                    return api.updateShop(item);
                })
                .then(() => api.getUnmoderatedShops())
                .then(res => {
                    page.innerHTML = '';
                    page.insertAdjacentElement("afterbegin", createShopsContent(res));
                })
        })
    })

    // Установка обработчиков на кнопку отклонения
    tabsContainer.querySelectorAll('[data-action="decline"]').forEach(el => {
        const id = el.getAttribute('data-shopid');
        const container = tabsContainer.querySelector(`#shopCard${id}`);
        const form = document.createElement("form");
        el.addEventListener("click", () => {
            if(container.querySelector(`#declineItem${id}`)) {
                return;
            }
            form.setAttribute("id", `declineItem${id}`)
            form.insertAdjacentHTML('afterbegin',`
                <input type="text" class="form-control" id="rejectedReasonTo${id}" name="rejectedReasonTo${id}" 
                placeholder="Введите причину отказа" style="width: 200px; height: 150px;" min="5">
                <button type="submit" class="btn btn-primary" id="rejectReason${id}">Отправить</button>
            `)
            form.addEventListener('submit', event => {
                event.preventDefault();
                api.getUnmoderatedShop(id)
                    .then(item => {
                        item.moderated = true;
                        item.moderateAccept = false;
                        item.moderatedRejectReason = form.elements[`rejectedReasonTo${id}`].value;
                        return api.updateShop(item);
                    })
                    .then(() => api.getUnmoderatedShops())
                    .then(res => {
                        page.innerHTML = '';
                        page.insertAdjacentElement("afterbegin", createShopsContent(res));
                    })
            })
        })
        container.insertAdjacentElement('beforeend',form);
    })
}

function updateShopsAmount() {
    api.getUnmoderatedShopsCount()
        .then(res => document.getElementById('v-pills-shops-tab').textContent = `Магазины ${res}`);
}



// ============================ reviews =================================

function createReviewsContent (arr) {
    const container = document.createElement("div");
    let html = ``;
    if(arr.length > 0) {
        arr.forEach(review => {
            html += `
            <div class="row" id="reviewRow${review.id}">
                <div class="col-sm-6">
                    <div class="card" id="reviewCard${review.id}">
                        <div class="row card-body">
                            <div class="col-sm-6">
                                <h5 class="card-title"><b>Отзыв к: </b>${review.itemName} <b>из</b> ${review.shopName}</h5>
                                <p class="card-text" id="cardText">Текст отзыва: <b>${review.text}</b> </p>
                                <p>Отзыв оставил: ${review.userFirstName} ${review.userLastName}</p>
                                <p>Рейтинг отзыва: ${review.rating}</p>
                                <button data-reviewid="${review.id}" data-action="accept" class="btn btn-success">Одобрить</a>
                                <button data-reviewid="${review.id}" data-action="decline" class="btn btn-danger">Отклонить</button>
                            </div> 
                        </div>
                    </div>
                </div>
            </div>
            <br>`
        })
    } else {
        html += `
            <div class="row">
                <div class="col-sm-6">
                    <div class="card" id="emptyItemList">
                        <div class="row card-body">
                            <div class="col-sm-6">
                                <h5 class="card-title"><b>Все озывы отмодерированы!</h5>
                                <p class="card-text" id="cardText"><b>Больше озывов нет, но есть замечательный кот!<br></p>
                            </div>
                            <img class="col-sm-6 text-right" src="https://w-dog.ru/wallpapers/5/19/361416555427750/kot-kotenok-seryj-pushistyj-lapy-kogotki.jpg" alt="https://iproger.ru/content/news/46014.jpg"/>
                        </div>
                    </div>
                </div>
            </div>
            <br>`
    }
    container.insertAdjacentHTML("afterbegin", html);
    reviewButtonsHandler(container);
    updateReviewsAmount();
    return container;
}

function reviewButtonsHandler( tabsContainer) {
    // Установка обработчиков на кнопку одобрения
    const page = document.getElementById("reviewsCards");
    tabsContainer.querySelectorAll('[data-action="accept"]').forEach(el => {
        el.addEventListener("click", () => {
            api.getUnmoderatedReview(el.getAttribute('data-reviewid'))
                .then(item => {
                    item.moderated = true;
                    item.moderateAccept = true;
                    return api.updateReview(item);
                })
                .then(() => api.getUnmoderatedReviews())
                .then(res => {
                    page.innerHTML = '';
                    page.insertAdjacentElement("afterbegin", createReviewsContent(res));
                })
        })
    })

    // Установка обработчиков на кнопку отклонения
    tabsContainer.querySelectorAll('[data-action="decline"]').forEach(el => {
        const id = el.getAttribute('data-reviewid');
        const container = tabsContainer.querySelector(`#reviewCard${id}`);
        const form = document.createElement("form");
        el.addEventListener("click", () => {
            if(container.querySelector(`#declineItem${id}`)) {
                return;
            }
            form.setAttribute("id", `declineItem${id}`)
            form.insertAdjacentHTML('afterbegin',`
                <input type="text" class="form-control" id="rejectedReasonTo${id}" name="rejectedReasonTo${id}" 
                placeholder="Введите причину отказа" style="width: 200px; height: 150px;" min="5">
                <button type="submit" class="btn btn-primary" id="rejectReason${id}">Отправить</button>
            `)
            form.addEventListener('submit', event => {
                event.preventDefault();
                api.getUnmoderatedReview(id)
                    .then(item => {
                        item.moderated = true;
                        item.moderateAccept = false;
                        item.moderatedRejectReason = form.elements[`rejectedReasonTo${id}`].value;
                        return api.updateReview(item)
                    })
                    .then(() => api.getUnmoderatedReviews())
                    .then(res => {
                        page.innerHTML = '';
                        page.insertAdjacentElement("afterbegin", createReviewsContent(res));
                    })
            })
        })
        container.insertAdjacentElement('beforeend',form);
    })
}

function updateReviewsAmount() {
    api.getUnmoderatedReviewsCount()
        .then(res => document.getElementById('v-pills-reviews-tab').textContent = `Отзывы ${res}`);
}


function setAllUnmoderatedAmount() {
    updateItemsAmount();
    updateShopsAmount();
    updateReviewsAmount();
}


export {
    createItemsContent, updateItemsAmount,
    createShopsContent, updateShopsAmount,
    createReviewsContent, updateReviewsAmount,
    setAllUnmoderatedAmount
}