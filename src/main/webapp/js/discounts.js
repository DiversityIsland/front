async function getDiscounts() {
    const response = await fetch("/api/discounts")
    const data = await response.json();
    if(document.getElementById("discounts")) {
        data.forEach(discount=> insertDiscountCard(discount))
    }
}
getDiscounts();

async function insertDiscountCard(discount) {
    let d = {
        id: discount.id,
        minOrder: discount.minOrder,
        percentage: discount.percentage,
        fixedDiscount: discount.fixedDiscount,
        shopId: discount.shopId,
        userId: discount.userId
    }

    let shop = await getShop(d.shopId);
    let s = {
        name: shop.name,
        description: shop.description,
        logo: shop.logo
    }

    let disc;
    if (d.fixedDiscount === 0) {
        disc = d.percentage + "% скидка";
    } else {
        disc = "Скидка в " + d.fixedDiscount + " рублей";
    }
    document.querySelector('#discounts').insertAdjacentHTML('beforeend', `
        <div class="card border-light m-3" id="discountCard${d.id}" style="max-width: 20rem;">
            <div class="card-header">
                <img class="card-img-top img-fluid p-1" src="${s.logo}" alt="Card image cap">
            </div>
            <div class="card-body border-light">
                <h5 class="card-title">${s.name}</h5>
                <p class="card-text">${disc} при покупке на сумму свыше ${d.minOrder} рублей</p>
            </div>
        </div>
        `);
}

async function getShop(id) {
    const response = await fetch("http://localhost:8888/api/shop/"+id)
    const data = await response.json();
    return data;
}
async function getShopNames() {
    const response = await fetch("http://localhost:8888/api/discounts/get_owned_shops");
    const data = await response.json();
    console.log(data)
    return data;
}

async function createDiscount() {
    let selectedShop = document.getElementById('shopsModal').value
    let username = document.getElementById('usernameModal').value
    let url = new URL("http://localhost:8888/api/discounts/add")
    url.searchParams.append('shopname', selectedShop);
    url.searchParams.append('username', username);

    const response = await fetch(url, {
        method: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json; charset=utf-8",
        },

        body: JSON.stringify({
            minOrder: document.getElementById('orderModal').value,
            percentage: document.getElementById('percentageModal').value,
            fixedDiscount: document.getElementById('fixedDiscModal').value
        })
    })
}

async function getShopOptions() {
    let shops = await getShopNames();
    let count = 0;
    let shopName;
    for(let i =0; i<shops.length; i++) {
        count++;
        shopName = shops[i];
        $(select_options).append(`
        <option id="shop${count}" value="${shopName}">${shopName}</option>`)
    }
}
const discount_modal = $('#modalDiscount');
const discount_open_modal_btn = $('#addDiscountButton');
const discount_form = $('#discount_form');
const select_options = $('#shopsModal');
const create_discount_button = $('#create_discount_button');
const percentage_check = $('#checkPercentage');
const fixed_check = $('#checkFixed');
const percentage_input = $('#percentageModal');
const fixed_input = $('#fixedDiscModal');

$(document).ready(function () {
    $(discount_open_modal_btn).on('click', function(e) {
        e.preventDefault();
        discount_modal.modal('show');
    })

    $(percentage_check).click(function () {
        $(fixed_check).prop('checked', false);
        $(percentage_check).prop('checked', true);
        $(fixed_input).prop('disabled', true);
        $(percentage_input).prop('disabled', false);
    })
    $(fixed_check).click(function () {
        $(percentage_check).prop('checked', false);
        $(fixed_check).prop('checked', true);
        $(percentage_input).prop('disabled', true);
        $(fixed_input).prop('disabled', false);
    })

    $(discount_modal).on('show.bs.modal', function (e) {
        getShopOptions()
    })
    $(create_discount_button).on('click', function (e) {
        discount_modal.modal('hide');
        createDiscount();
        location.reload();
    })
})
