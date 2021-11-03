$(document).ready(function () {
    updateShops("");
    updateUsers("");
    updateItems("");
    updateCountries("");
    updateCities("");
    updateAddresses("");
    updateCategories("");

    $("#shop-search-form").on("submit", function(event) {
        event.preventDefault()
        updateShops($("#shop-search").val());
    })

    $("#user-search-form").on("submit", function(event) {
        event.preventDefault()
        updateUsers($("#user-search").val());
    })

    $("#item-search-form").on("submit", function(event) {
        event.preventDefault()
        updateItems($("#item-search").val());
    })

    $("#country-search-form").on("submit", function(event) {
        event.preventDefault()
        updateCountries($("#country-search").val());
    })

    $("#city-search-form").on("submit", function(event) {
        event.preventDefault()
        updateCities($("#city-search").val());
    })

    $("#address-search-form").on("submit", function(event) {
        event.preventDefault()
        updateAddresses($("#address-search").val());
    })

    $("#category-search-form").on("submit", function(event) {
        event.preventDefault()
        updateCategories($("#category-search").val());
    })



    //add new shop
    $('.shopInsertModal .shopInsertButton').on('click', function (event) {
        let shop = {
            name:$(".shopInsertModal #name").val(),
            email:$(".shopInsertModal #email").val(),
            phone:$(".shopInsertModal #phone").val(),
            description:$(".shopInsertModal #description").val(),
            location:$(".shopInsertModal #ins_shop_location").val(),
            username:$(".shopInsertModal #ins_shop_username").val(),
            logo:$("#ins-shop-logo-path").val(),
            logoarray:$("#ins-shop-logo-array").val()
        }
        console.log(shop)

        fetch("api/shop/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(shop)
        }) .then(() => {$('.shopInsertModal #shopInsertModal').modal('hide');$('input').val(''); updateShops("")})
    })

    //save shop
    $('.shopEditModal .shopSaveButton').on('click', function (event) {
        let shop = {
            id:$(".shopEditModal #id").val(),
            name:$(".shopEditModal #name").val(),
            email:$(".shopEditModal #email").val(),
            phone:$(".shopEditModal #phone").val(),
            description:$(".shopEditModal #description").val(),
            location:$(".shopEditModal #edit_shop_location").val(),
            username:$(".shopEditModal #edit_shop_username").val(),
            logo:$("#edit-shop-logo-path").val(),
            logoarray:$("#edit-shop-logo-array").val()
        }

        console.log(shop)
        fetch("api/shop/save", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(shop)
        }) .then(() => {$('.shopEditModal #shopEditModal').modal('hide'); $('input').val('');
            updateShops("");})
    })


    //add new item
    $('.itemInsertModal .itemInsertButton').on('click', function (event) {
        let item = {
            name:$(".itemInsertModal #name").val(),
            price:$(".itemInsertModal #price").val(),
            count:$(".itemInsertModal #count").val(),
            categories:getCategory("#ins_item_categories"),
            description:$(".itemInsertModal #description").val(),
            discount:$(".itemInsertModal #discount").val(),
            shopId:$(".itemInsertModal #ins_item_shops").val(),
            images:getImages(1),
            imagesArray:getImagesArray(1),
            reviews:getReviews
        }

        console.log(item)

        fetch("api/item/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(item)
        }) .then(() => {$('.itemInsertModal #itemInsertModal').modal('hide');$('input').val('');ins_item_logo_count=0; updateItems("")})
    })

    //save item
    $('.itemEditModal .itemSaveButton').on('click', function (event) {
        let item = {
            id:$(".itemEditModal #id").val(),
            name:$(".itemEditModal #name").val(),
            price:$(".itemEditModal #price").val(),
            count:$(".itemEditModal #count").val(),
            categories:getCategory("#edit_item_categories"),
            description:$(".itemEditModal #description").val(),
            discount:$(".itemEditModal #discount").val(),
            shopId:$(".itemEditModal #edit_item_shops").val(),
            images:getImages(2),
            imagesArray:getImagesArray(2),
            reviews:getReviews
        }

        console.log(item)
        fetch("api/item/save", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(item)
        }) .then(() => {$('.itemEditModal #itemEditModal').modal('hide');
            $("#edit-item-logo-count").val(0)
            $('input').val('');
            updateItems("");})
    })


    //add new address
    $('.addressInsertModal .addressInsertButton').on('click', function (event) {
        let address = {
            city:$(".addressInsertModal #ins_address_cities").val(),
            cityIndex: $(".addressInsertModal #index").val(),
            street: $(".addressInsertModal #street").val(),
            house: $(".addressInsertModal #house").val(),
        }

        console.log(address)

        fetch("api/address/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(address)
        }) .then(() => {$('.addressInsertModal #addressInsertModal').modal('hide');$('input').val(''); updateAddresses("")})
    })

    //save address
    $('.addressEditModal .addressSaveButton').on('click', function (event) {
        let address = {
            id: $(".addressEditModal #id").val(),
            city:$(".addressEditModal #edit_address_cities").val(),
            cityIndex: $(".addressEditModal #index").val(),
            street: $(".addressEditModal #street").val(),
            house: $(".addressEditModal #house").val(),
        }

        fetch("api/address/save", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(address)
        }) .then(() => {$('.addressEditModal #addressEditModal').modal('hide'); $('input').val('');
            updateAddresses(""); updateUsers("")})
    })

    //add new city
    $('.cityInsertModal .cityInsertButton').on('click', function (event) {
        let city = {
            name: $(".cityInsertModal #name").val(),
            country:$(".cityInsertModal #ins_city_countries").val()
        }

        console.log(city)

        fetch("api/city/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(city)
        }) .then(() => {$('.cityInsertModal #cityInsertModal').modal('hide');$('input').val(''); updateCities("")})
    })

    //save city
    $('.cityEditModal .citySaveButton').on('click', function (event) {
        let city = {
            id: $(".cityEditModal #id").val(),
            name: $(".cityEditModal #name").val(),
            country:$(".cityEditModal #edit_city_countries").val()
        }

        fetch("api/city/save", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(city)
        }) .then(() => {$('.cityEditModal #cityEditModal').modal('hide'); $('input').val('');
            updateCities(""); updateAddresses("");})
    })


    //add new country
    $('.countryInsertModal .countryInsertButton').on('click', function (event) {
        let country = {
            name: $(".countryInsertModal #name").val(),
        }

        fetch("api/country/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(country)
        }) .then(() => {$('.countryInsertModal #countryInsertModal').modal('hide');$('input').val(''); updateCountries("")})
    })

    //save country
    $('.countryEditModal .countrySaveButton').on('click', function (event) {
        let country = {
            id: $(".countryEditModal #id").val(),
            name: $(".countryEditModal #name").val()
        }

        fetch("api/country/save", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(country)
        }) .then(() => {$('.countryEditModal #countryEditModal').modal('hide'); $('input').val('');
            updateCountries(""); updateCities(""); updateAddresses(""); updateShops("")})
    })

    //add new category
    $('.categoryInsertModal .catInsertButton').on('click', function (event) {
        let category = {
            name: $(".categoryInsertModal #name").val(),
        }

        fetch("api/category/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(category)
        }) .then(() => {$('.categoryInsertModal #catInsertModal').modal('hide');$('input').val(''); updateCategories("")})
    })

    //save category
    $('.categoryEditModal .catSaveButton').on('click', function (event) {
        let category = {
            id: $(".categoryEditModal #id").val(),
            name: $(".categoryEditModal #name").val()
        }

        fetch("api/category/save", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(category)
        }) .then(() => {$('.categoryEditModal #catEditModal').modal('hide'); $('input').val(''); updateCategories(""); updateItems("")})
    })

    //add new user
    $('.userInsertModal .userInsertButton').on('click', function (event) {
        let user = {
            email: $(".userInsertModal #email").val(),
            username: $(".userInsertModal #username").val(),
            password: $(".userInsertModal #password").val(),
            gender: $(".userInsertModal #ins_user_gender").val(),
            birthday: $(".userInsertModal #birthday").val(),

            phone: $(".userInsertModal #phone").val(),
            firstName: $(".userInsertModal #firstName").val(),
            lastName: $(".userInsertModal #lastName").val(),
            roles:getRole("#ins_user_roles")
        }

        console.log(user)

        fetch("api/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(user)
        }) .then(() => {$('.userInsertModal #userInsertModal').modal('hide');$('input').val(''); updateUsers("")})
    })

    //save user
    $('.userEditModal .userSaveButton').on('click', function (event) {
        let user = {
            id: $(".userEditModal #id").val(),
            email: $(".userEditModal #email").val(),
            username: $(".userEditModal #username").val(),
            password: $(".userEditModal #password").val(),
            gender: $(".userEditModal #edit_user_gender").val(),
            birthday: $(".userEditModal #birthday").val(),

            phone: $(".userEditModal #phone").val(),
            firstName: $(".userEditModal #firstName").val(),
            lastName: $(".userEditModal #lastName").val(),
            roles:getRole("#edit_user_roles")
        }

        console.log(user)

        fetch("api/save-user", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(user)
        }) .then(() => {$('.userEditModal #userEditM').modal('hide'); $('input').val(''); updateUsers("")})
    })

    $('.logout').on('click', function (event) {
        logout();
    })
});


function updateShops(search_txt) {
    console.log("updateShops search_txt=" + search_txt);
    let shopTableBody = $("#shop_table_body")

    shopTableBody.children().remove();

    fetch("/api/admin/allshops")
        .then((response) => {
            response.json().then(data => data.forEach(function (item, i, data) {
                if ((search_txt == "") || (item.name.toLowerCase().includes(search_txt.toLowerCase()))
                    || (item.description.toLowerCase().includes(search_txt.toLowerCase()))) {
                    let TableRow = createTableShop(item);
                    shopTableBody.append(TableRow);
                }
            }));
        }).catch(error => {
        console.log(error);
    });
}

function createTableShop(shop) {

    return `<tr id="shop_table_row">

            <td>${shop.id}</td>
            <td>${shop.name}</td>
            <td>${shop.email}</td>
            <td>${shop.phone}</td>
            <td>${shop.location}</td>
            <td>${shop.rating}</td>
            <td>${shop.username}</td>
            
            <td>
            <a href="/api/admin/shop/${shop.id}/allitems" class="btn btn-info shopItemsButton">Список товаров</a>
            </td>
            
            <td>
            <a href="/api/shop/${shop.id}" class="btn btn-info shopEditButton">Редактировать</a>
            </td>
            <td>
            <a  href="/api/shop/${shop.id}" class="btn btn-danger shopDeleteButton">Удалить</a>
            </td>
        </tr>`;
}

function updateUsers(search_txt) {
    console.log("updateUsers search_txt="+ search_txt);
    let userTableBody = $("#user_table_body")

    userTableBody.children().remove();

    fetch("/api/admin/allusers")
        .then((response) => {
            response.json().then(data => data.forEach(function (item, i, data) {
                if ((search_txt == "") || (item.firstName.toLowerCase().includes(search_txt.toLowerCase()))
                    || (item.username.toLowerCase().includes(search_txt.toLowerCase()))
                    || (item.lastName.toLowerCase().includes(search_txt.toLowerCase()))
                ) {
                    let TableRow = createTableUser(item);
                    userTableBody.append(TableRow);
                }
            }));
        }).catch(error => {
        console.log(error);
    });
}

function createTableUser(user) {
    let userRole = "";
    user.roles.forEach(x => userRole += " " + x.name)

    return `<tr id="user_table_row">
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>${user.phone}</td>
            <td>${user.age}</td>
            <td>${user.gender}</td>
            <td>${userRole}</td>
            
            <td>
            <a href="/api/user/${user.id}" class="btn btn-info userEditButton">Edit</a>
            </td>
            <td>
            <a  href="/api/${user.id}" class="btn btn-danger userDeleteButton">Delete</a>
            </td>
        </tr>`;
}

function updateItems(search_txt) {
    console.log("updateItems search_txt="+ search_txt);
    let itemTableBody = $("#item_table_body")

    itemTableBody.children().remove();

    fetch("/api/admin/allitems")
        .then((response) => {
            response.json().then(data => data.forEach(function (item, i, data) {
                if ((search_txt == "") || (item.name.toLowerCase().includes(search_txt.toLowerCase()))
                    || (item.description.toLowerCase().includes(search_txt.toLowerCase()))
                ) {
                    let TableRow = createTableItem(item);
                    itemTableBody.append(TableRow);
                }
            }));
        }).catch(error => {
        console.log(error);
    });
}

function createTableItem(item) {

    return `<tr id="item_table_row">
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.price}</td>
            <td>${item.count}</td>
            <td>${item.rating}</td>
            <td>${item.categories}</td>
            <td>${item.description}</td>
            <td>${item.moderated}</td>
            <td>${item.moderateAccept}</td>
            <td>${item.moderatedRejectReason}</td>
                     
            <td>
            <a href="/api/item/${item.id}" class="btn btn-info itemEditButton">Edit</a>
            </td>
            <td>
            <a  href="/api/item/${item.id}" class="btn btn-danger itemDeleteButton">Delete</a>
            </td>
        </tr>`;
}

function createTableItemShop(item) {

    return `<tr id="item_table_row">
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.price}</td>
            <td>${item.count}</td>
            <td>${item.rating}</td>
            <td>${item.categories}</td>
            <td>${item.moderated}</td>
            <td>${item.moderateAccept}</td>
            <td>${item.moderatedRejectReason}</td>                     
        </tr>`;
}

function updateCountries(search_txt) {
    console.log("updateCountries search_txt=" + search_txt);
    let countryTableBody = $("#country_table_body")

    countryTableBody.children().remove();

    fetch("/api/admin/allcountries")
        .then((response) => {
            response.json().then(data => data.forEach(function (item, i, data) {
                if ((search_txt == "") || (item.name.toLowerCase().includes(search_txt.toLowerCase()))) {
                    let TableRow = createTableCountry(item);
                    countryTableBody.append(TableRow);
                }
            }));
        }).catch(error => {
        console.log(error);
    });
}

function createTableCountry(country) {

    return `<tr id="country_table_row">
            <td>${country.id}</td>
            <td>${country.name}</td>
                     
            <td>
            <a href="/api/country/${country.id}" class="btn btn-info countryEditButton">Edit</a>
            </td>
            <td>
            <a  href="/api/country/${country.id}" class="btn btn-danger countryDeleteButton">Delete</a>
            </td>
        </tr>`;
}

function updateCities(search_txt) {
    console.log("updateCities search_txt="+search_txt);
    let cityTableBody = $("#city_table_body")

    cityTableBody.children().remove();

    fetch("/api/admin/allcities")
        .then((response) => {
            response.json().then(data => data.forEach(function (item, i, data) {
                if ((search_txt == "") || (item.name.toLowerCase().includes(search_txt.toLowerCase()))) {
                    let TableRow = createTableCity(item);
                    cityTableBody.append(TableRow);
                }
            }));
        }).catch(error => {
        console.log(error);
    });
}

function createTableCity(city) {

    return `<tr id="city_table_row">
            <td>${city.id}</td>
            <td>${city.name}</td>
            <td>${city.country}</td>
                     
            <td>
            <a href="/api/city/${city.id}" class="btn btn-info cityEditButton">Edit</a>
            </td>
            <td>
            <a  href="/api/city/${city.id}" class="btn btn-danger cityDeleteButton">Delete</a>
            </td>
        </tr>`;
}

function updateAddresses(search_txt) {
    console.log("updateAddresses search_txt="+ search_txt);
    let addressTableBody = $("#address_table_body")

    addressTableBody.children().remove();

    fetch("/api/admin/alladdresses")
        .then((response) => {
            response.json().then(data => data.forEach(function (item, i, data) {
                if ((search_txt == "") || (item.city.toLowerCase().includes(search_txt.toLowerCase()))
                    || (item.street.toLowerCase().includes(search_txt.toLowerCase()))
                    || (item.country.toLowerCase().includes(search_txt.toLowerCase()))
                ) {
                    let TableRow = createTableAddress(item);
                    addressTableBody.append(TableRow);
                }
            }));
        }).catch(error => {
        console.log(error);
    });
}

function createTableAddress(address) {

    return `<tr id="address_table_row">
            <td>${address.id}</td>
            <td>${address.cityIndex}</td>
            <td>${address.country}</td>
            <td>${address.city}</td>
            <td>${address.street}</td>
            <td>${address.house}</td>       
                     
            <td>
            <a href="/api/address/${address.id}" class="btn btn-info addressEditButton">Edit</a>
            </td>
 
             <td>
            <a href="/api/address/${address.id}" class="btn btn-danger addressDeleteButton">Delete</a>
            </td>           

        </tr>`;
}

function updateCategories(search_txt) {
    console.log("updateCategories search_txt" +search_txt);
    let categoryTableBody = $("#category_table_body")

    categoryTableBody.children().remove();

    fetch("/api/admin/allcategories")
        .then((response) => {
            response.json().then(data => data.forEach(function (item, i, data) {
                if ((search_txt == "") || (item.name.toLowerCase().includes(search_txt.toLowerCase()))) {
                    let TableRow = createTableCategory(item);
                    categoryTableBody.append(TableRow);
                }
            }));
        }).catch(error => {
        console.log(error);
    });
}

function createTableCategory(category) {

    return `<tr id="category_table_row">
            <td>${category.id}</td>
            <td>${category.name}</td>

            <td>
            <a href="/api/category/${category.id}" class="btn btn-info categoryEditButton">Edit</a>
            </td>
            <td>
            <a href="/api/category/${category.id}" class="btn btn-danger categoryDeleteButton">Delete</a>
            </td>
        </tr>`;
}

function logout() {
    document.location.replace("/logout");
}

function getRole(address) {
    let data = [];
    $(address).find("option:selected").each(function () {
        data.push({id: $(this).val(),name: $(this).attr("name")
            /* , authority: $(this).attr("name")*/
        })
    });
    return data;
}

function getCountry(address) {
    let data = [];
    $(address).find("option:selected").each(function () {
        return $(this).attr("name");
    });
}

function getCategory(address) {
    let data = [];
    $(address).find("option:selected").each(function () {
        data.push($(this).attr("name"))
    });
    return data;
}

function base64toBlob(base64Data, contentType) {
    contentType = contentType || '';
    var sliceSize = 1024;
    var byteCharacters = atob(base64Data);
    var bytesLength = byteCharacters.length;
    var slicesCount = Math.ceil(bytesLength / sliceSize);
    var byteArrays = new Array(slicesCount);

    for (var sliceIndex = 0; sliceIndex < slicesCount; ++sliceIndex) {
        var begin = sliceIndex * sliceSize;
        var end = Math.min(begin + sliceSize, bytesLength);

        var bytes = new Array(end - begin);
        for (var offset = begin, i = 0 ; offset < end; ++i, ++offset) {
            bytes[i] = byteCharacters[offset].charCodeAt(0);
        }
        byteArrays[sliceIndex] = new Uint8Array(bytes);
    }
    return new Blob(byteArrays, { type: contentType });
}

function uploadFile()
{
    let dataArray = new FormData();
    dataArray.append('file', file_input.files[0]);
    console.log(file_input.files)
    const reader = new FileReader();
    reader.readAsDataURL(file_input.files[0])

    reader.addEventListener("load", ()=> {
        localStorage.setItem("recent-image", reader.result);
        var str = reader.result;
        str = str.replace("data:image/jpeg;base64,","")
        var bytes = base64toBlob(str);
        $("#ins-shop-logo-array").val(bytes)
    });

    var tmppath = URL.createObjectURL(file_input.files[0]);
    $('.shopInsertModal #ins-shop-logo').attr('src', tmppath);
    $("#ins-shop-logo-path").val(tmppath)
}

function uploadFile1()
{

    let dataArray = new FormData();
    dataArray.append('file', file_input.files[0]);
    console.log(file_input.files)
    const reader = new FileReader();
    reader.readAsDataURL(file_input.files[0])

    reader.addEventListener("load", ()=> {
        localStorage.setItem("recent-image", reader.result);
        var str = reader.result;
        str = str.replace("data:image/jpeg;base64,","")
        var bytes = base64toBlob(str);
        $("#edit-shop-logo-array").val(bytes)
    });

    var tmppath = URL.createObjectURL(file_input.files[0]);

    $('.shopEditModal #edit-shop-logo').attr('src', tmppath);
    $("#edit-shop-logo-path").val(tmppath)
}

function uploadFile_ins_item()
{

    let dataArray = new FormData();
    dataArray.append('file', file_input.files[0]);
    console.log(file_input.files)
    const reader = new FileReader();
    reader.readAsDataURL(file_input.files[0])

    var str =""

    reader.addEventListener("load", ()=> {
        localStorage.setItem("recent-image", reader.result);
        str = reader.result;
    });

    str = str.replace("data:image/jpeg;base64,","")
    console.log(file_input.files)

    var bytes = base64toBlob(str);
    if (ins_item_logo_count > 5) ins_item_logo_count = 0;


    console.log(ins_item_logo_count)

    if (ins_item_logo_count == 0) {
        $("#ins-item-logo-array0").val(bytes)

        var tmppath = URL.createObjectURL(file_input.files[0]);
        $("#ins-item-logo0").attr('src', tmppath);
        $("#ins-item-logo-path0").val(tmppath)
    } else if (ins_item_logo_count == 1) {

        $("#ins-item-logo-array1").val(bytes)

        var tmppath = URL.createObjectURL(file_input.files[0]);
        $("#ins-item-logo1").attr('src', tmppath);
        $("#ins-item-logo-path1").val(tmppath)
    }
    else if (ins_item_logo_count == 2) {

        $("#ins-item-logo-array2").val(bytes)

        var tmppath = URL.createObjectURL(file_input.files[0]);
        $("#ins-item-logo2").attr('src', tmppath);
        $("#ins-item-logo-path2").val(tmppath)
    }
    else if (ins_item_logo_count == 3) {

        $("#ins-item-logo-array3").val(bytes)

        var tmppath = URL.createObjectURL(file_input.files[0]);
        $("#ins-item-logo3").attr('src', tmppath);
        $("#ins-item-logo-path3").val(tmppath)
    }
    else if (ins_item_logo_count == 4) {

        $("#ins-item-logo-array4").val(bytes)

        var tmppath = URL.createObjectURL(file_input.files[0]);
        $("#ins-item-logo4").attr('src', tmppath);
        $("#ins-item-logo-path4").val(tmppath)
    }
    else if (ins_item_logo_count == 5) {

        $("#ins-item-logo-array5").val(bytes)

        var tmppath = URL.createObjectURL(file_input.files[0]);
        $("#ins-item-logo5").attr('src', tmppath);
        $("#ins-item-logo-path5").val(tmppath)
    }

    ins_item_logo_count++;

}

function uploadFile_edit_item()
{

    let dataArray = new FormData();
    dataArray.append('file', file_input.files[0]);
    console.log(file_input.files)
    const reader = new FileReader();
    reader.readAsDataURL(file_input.files[0])

    var str =""

    reader.addEventListener("load", ()=> {
        localStorage.setItem("recent-image", reader.result);
        str = reader.result;
    });

    str = str.replace("data:image/jpeg;base64,","")
    console.log(file_input.files)

    var bytes = base64toBlob(str);
    if (edit_item_logo_count > 5) edit_item_logo_count = 0;


    console.log(ins_item_logo_count)

    if (edit_item_logo_count == 0) {
        $("#edit-item-logo-array0").val(bytes)

        var tmppath = URL.createObjectURL(file_input.files[0]);
        $("#edit-item-logo0").attr('src', tmppath);
        $("#edit-item-logo-path0").val(tmppath)
    } else if (edit_item_logo_count == 1) {

        $("#edit-item-logo-array1").val(bytes)

        var tmppath = URL.createObjectURL(file_input.files[0]);
        $("#edit-item-logo1").attr('src', tmppath);
        $("#edit-item-logo-path1").val(tmppath)
    }
    else if (edit_item_logo_count == 2) {

        $("#edit-item-logo-array2").val(bytes)

        var tmppath = URL.createObjectURL(file_input.files[0]);
        $("#edit-item-logo2").attr('src', tmppath);
        $("#edit-item-logo-path2").val(tmppath)
    }
    else if (edit_item_logo_count == 3) {

        $("#edit-item-logo-array3").val(bytes)

        var tmppath = URL.createObjectURL(file_input.files[0]);
        $("#edit-item-logo3").attr('src', tmppath);
        $("#edit-item-logo-path3").val(tmppath)
    }
    else if (edit_item_logo_count == 4) {

        $("#edit-item-logo-array4").val(bytes)

        var tmppath = URL.createObjectURL(file_input.files[0]);
        $("#edit-item-logo4").attr('src', tmppath);
        $("#edit-item-logo-path4").val(tmppath)
    }
    else if (edit_item_logo_count == 5) {

        $("#edit-item-logo-array5").val(bytes)

        var tmppath = URL.createObjectURL(file_input.files[0]);
        $("#edit-item-logo5").attr('src', tmppath);
        $("#edit-item-logo-path5").val(tmppath)
    }
    edit_item_logo_count++;
    $('.itemEditModal #edit-item-logo-count').val(edit_item_logo_count);
}

var ins_item_logo_count=0;
var edit_item_logo_count=0;

function getImages(num) {

    let data = [];

    if (num ==1 ) {
        for (let i = 0; i < 6; i++) {
            var str = "#ins-item-logo-path" + i.toString();
            console.log(str);
            if ($(str).val() != "") {
                data.push($(str).val())
            }
        }

    } else if (num == 2) {
        for (let i = 0; i < 6; i++) {
            var str = "#edit-item-logo-path" + i.toString();
            console.log(str);
            if ($(str).val() != "") {
                data.push($(str).val())
            }
        }
    }
    console.log(data);
    return data;
}
function getReviews() {
    let data = [];
    return data;
}

function getImagesArray(num) {

    let data = [];

    if (num ==1 ) {
        for (let i = 0; i < 6; i++) {
            var str = "#ins-item-logo-array" + i.toString();
            console.log(str);
            if ($(str).val() != "") {
                data.push($(str).val())
            }
        }

    } else if (num == 2) {
        for (let i = 0; i < 6; i++) {
            var str = "#edit-item-logo-array" + i.toString();
            console.log(str);
            data.push($(str).val())
        }
    }
    console.log(data);
    return data;
}


document.addEventListener('click', function (event) {
    event.preventDefault()


    if ($(event.target).hasClass('insItemLogoButton')) {
        file_input = document.createElement('input');
        file_input.addEventListener("change", uploadFile_ins_item, false);
        file_input.type = 'file';
        file_input.click();
    }

    if ($(event.target).hasClass('editItemLogoButton')) {
        edit_item_logo_count =  $("#edit-item-logo-count").val()
        console.log("count pics "+edit_item_logo_count);

        file_input = document.createElement('input');
        file_input.addEventListener("change", uploadFile_edit_item, false);
        file_input.type = 'file';
        file_input.click();
    }


    if ($(event.target).hasClass('insShopLogoButton')) {
        file_input = document.createElement('input');
        file_input.addEventListener("change", uploadFile, false);
        file_input.type = 'file';
        file_input.click();
    }

    if ($(event.target).hasClass('editShopLogoButton')) {
        file_input = document.createElement('input');
        file_input.addEventListener("change", uploadFile1, false);
        file_input.type = 'file';
        file_input.click();
    }

    //open shopItemListModal modal form
    if ($(event.target).hasClass('shopItemsButton')) {
        let itemShopTableBody = $("#item_by_shop_table_body")
        itemShopTableBody.children().remove();

        let href = $(event.target).attr("href");
        console.log(href);


        fetch(href)
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    let TableRow = createTableItemShop(item);
                    itemShopTableBody.append(TableRow);
                    console.log(TableRow);
                }));
            }).catch(error => {
            console.log(error);
        });


        $(".shopItemListModal #shopItemListModal").modal();
    }

    //open shopInsertModal modal form
    if ($(event.target).hasClass('addNewShopBtn')) {
        $(".shopInsertModal #ins_shop_location").children().remove();
        $(".shopInsertModal #ins_shop_username").children().remove();

        fetch("/api/admin/allcountries")
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    $("#ins_shop_location")
                        .append("<option name=\""+item.name+"\" value=\""+item.name+"\" "
                            +" label=\""+item.name+"\" "
                            +">"+item.name+"</option>");
                }));
            }).catch(error => {
            console.log(error);
        });

        fetch("/api/admin/allusers")
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    $("#ins_shop_username")
                        .append("<option name=\""+item.username+"\" value=\""+item.username+"\" "
                            +" label=\""+item.username+"\" "
                            +">"+item.username+"</option>");
                }));
            }).catch(error => {
            console.log(error);
        });

        let shop_logo = "http://www.placehold.it/200x150/EFEFEF/AAAAAA&text=no+image"
        $('.shopInsertModal #ins-shop-logo').attr('src', shop_logo);

        $(".shopInsertModal #shopInsertModal").modal();
    }

    //open shopEdit modal form
    if ($(event.target).hasClass('shopEditButton')) {

        $(".shopEditModal #edit_shop_location").children().remove();
        $(".shopEditModal #edit_shop_username").children().remove();

        fetch("/api/admin/allcountries")
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    $("#edit_shop_location")
                        .append("<option name=\""+item.name+"\" value=\""+item.name+"\" "
                            +" label=\""+item.name+"\" "
                            +">"+item.name+"</option>");
                }));
            }).catch(error => {
            console.log(error);
        });

        let href = $(event.target).attr("href");
        console.log(href);

        $.get(href, function (shop) {
            $('.shopEditModal #id').val(shop.id);
            $('.shopEditModal #name').val(shop.name);
            $('.shopEditModal #email').val(shop.email);
            $('.shopEditModal #phone').val(shop.phone);
            $('.shopEditModal #description').val(shop.description);
            $('.shopEditModal #edit_shop_location').val(shop.location);

            fetch("/api/admin/allusers")
                .then((response) => {
                    response.json().then(data => data.forEach(function (item, i, data) {
                        if (item.username == shop.username) {
                            $("#edit_shop_username")
                                .append("<option name=\"" + item.username + "\" value=\"" + item.username + "\" "
                                    + " label=\"" + item.username + "\" "
                                    + " selected=true "
                                    + ">" + item.username + "</option>");
                        } else {
                            $("#edit_shop_username")
                                .append("<option name=\"" + item.username + "\" value=\"" + item.username + "\" "
                                    + " label=\"" + item.username + "\" "
                                    + ">" + item.username + "</option>");
                        }

                    }));
                }).catch(error => {
                console.log(error);
            });

            let shop_logo = "http://www.placehold.it/200x150/EFEFEF/AAAAAA&text=no+image"
            if (shop.logo != '') shop_logo = shop.logo


            $('.shopEditModal #edit-shop-logo').attr('src', shop_logo);
            console.log(shop.logo);
        });

        $(".shopEditModal #shopEditModal").modal();
    }


    //open itemInsertModal modal form
    if ($(event.target).hasClass('addNewItemBtn')) {
        $(".itemInsertModal #ins_item_categories").children().remove();
        $(".itemInsertModal #ins_item_shops").children().remove();

        fetch("/api/admin/allcategories")
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    $("#ins_item_categories")
                        .append("<option name=\""+item.name+"\" value=\""+item.name+"\" "
                            +" label=\""+item.name+"\" "
                            +">"+item.name+"</option>");
                }));
            }).catch(error => {
            console.log(error);
        });

        fetch("/api/admin/allshops")
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    $("#ins_item_shops")
                        .append("<option name=\""+item.name+"\" value=\""+item.id+"\" "
                            +" label=\""+item.name+"\" "
                            +">"+item.name+"</option>");
                }));
            }).catch(error => {
            console.log(error);
        });

        let item_image = "http://www.placehold.it/200x150/EFEFEF/AAAAAA&text=no+image"
        $('.itemInsertModal #ins-item-logo0').attr('src', item_image);

        $(".itemInsertModal #itemInsertModal").modal();
    }

    //open itemEdit modal form
    if ($(event.target).hasClass('itemEditButton')) {
        $(".itemEditModal #edit_item_categories").children().remove();
        $(".itemEditModal #edit_item_shops").children().remove();

        fetch("/api/admin/allcategories")
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    $("#edit_item_categories")
                        .append("<option name=\""+item.name+"\" value=\""+item.name+"\" "
                            +" label=\""+item.name+"\" "
                            +">"+item.name+"</option>");
                }));
            }).catch(error => {
            console.log(error);
        });


        let href = $(event.target).attr("href");
        console.log(href);

        $.get(href, function (item) {
            $('.itemEditModal #id').val(item.id);
            $('.itemEditModal #name').val(item.name);
            $('.itemEditModal #price').val(item.price);
            $('.itemEditModal #count').val(item.count);
            $('.itemEditModal #edit_item_categories').val(item.categories);
            $('.itemEditModal #description').val(item.description);
            $('.itemEditModal #discount').val(item.discount);

            fetch("/api/admin/allshops")
                .then((response) => {
                    response.json().then(data => data.forEach(function (shop, i, data) {
                        if (item.shopId == shop.id) {
                            $("#edit_item_shops")
                                .append("<option name=\""+shop.name+"\" value=\""+shop.id+"\" "
                                    +"selected=true"
                                    +" label=\""+shop.name+"\" "
                                    +">"+shop.name+"</option>");

                            console.log("<option name=\""+shop.name+"\" value=\""+shop.id+"\" "
                                +"selected=true"
                                +" label=\""+shop.name+"\" "
                                +">"+shop.name+"</option>")
                        } else {
                            $("#edit_item_shops")
                                .append("<option name=\""+shop.name+"\" value=\""+shop.id+"\" "
                                    +" label=\""+shop.name+"\" "
                                    +">"+shop.name+"</option>");
                        }

                    }));
                }).catch(error => {
                console.log(error);
            });

            $("#edit-item-logo0").attr('src', '')
            $("#edit-item-logo1").attr('src', '')
            $("#edit-item-logo2").attr('src', '')
            $("#edit-item-logo3").attr('src', '')
            $("#edit-item-logo4").attr('src', '')
            $("#edit-item-logo5").attr('src', '')
            $("#edit-item-logo-path0").attr('src', '')
            $("#edit-item-logo-path1").attr('src', '')
            $("#edit-item-logo-path2").attr('src', '')
            $("#edit-item-logo-path3").attr('src', '')
            $("#edit-item-logo-path4").attr('src', '')
            $("#edit-item-logo-path5").attr('src', '')

            var images = item.images;

            $('.itemEditModal #edit-item-logo-count').val(images.length);

            for (let i = 0; i < images.length; i++) {
                if (images[i] != '') {
                    item_image = images[i];
                    if (i == 0) {
                        $("#edit-item-logo0").attr('src', item_image)
                        $("#edit-item-logo-path0").val(item_image)
                    }
                    else if (i == 1) {
                        $("#edit-item-logo1").attr('src', item_image);
                        $("#edit-item-logo-path1").val(item_image)
                    }
                    else if (i == 2) {
                        $("#edit-item-logo2").attr('src', item_image)
                        $("#edit-item-logo-path2").val(item_image)
                    }
                    else if (i == 3) {
                        $("#edit-item-logo3").attr('src', item_image)
                        $("#edit-item-logo-path3").val(item_image)
                    }
                    else if (i == 4) {
                        $("#edit-item-logo4").attr('src', item_image)
                        $("#edit-item-logo-path4").val(item_image)
                    }
                    else if (i == 5) {
                        $("#edit-item-logo5").attr('src', item_image)
                        $("#edit-item-logo-path5").val(item_image)
                    }
                    console.log(item_image);
                }
            }
        });

        $(".itemEditModal #itemEditModal").modal();
    }

    //open addressInsertModal modal form
    if ($(event.target).hasClass('addNewAddressBtn')) {
        $(".addressInsertModal #ins_address_cities").children().remove();

        fetch("/api/admin/allcities")
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    $("#ins_address_cities")
                        .append("<option name=\""+item.name+"\" value=\""+item.name+"\" "
                            +" label=\""+item.name+"\" "
                            +">"+item.name+"</option>");
                }));
            }).catch(error => {
            console.log(error);
        });


        $(".addressInsertModal #addressInsertModal").modal();
    }

    //open addressEdit modal form
    if ($(event.target).hasClass('addressEditButton')) {
        $(".addressEditModal #edit_address_cities").children().remove();


        let href = $(event.target).attr("href");
        console.log(href);

        $.get(href, function (address) {
            $('.addressEditModal #id').val(address.id);

            fetch("/api/admin/allcities")
                .then((response) => {
                    response.json().then(data => data.forEach(function (item, i, data) {

                        if (address.city == item.name) {
                            $("#edit_address_cities")
                                .append("<option name=\""+item.name+"\" value=\""+item.name+"\" "
                                    + " selected=true"
                                    +" label=\""+item.name+"\" "
                                    +">"+item.name+"</option>");
                        } else {
                            $("#edit_address_cities")
                                .append("<option name=\""+item.name+"\" value=\""+item.name+"\" "
                                    +" label=\""+item.name+"\" "
                                    +">"+item.name+"</option>");
                        }

                    }));
                }).catch(error => {
                console.log(error);
            });

            $('.addressEditModal #index').val(address.cityIndex);
            $('.addressEditModal #street').val(address.street);
            $('.addressEditModal #house').val(address.house);
        });

        $(".addressEditModal #addressEditModal").modal();
    }


    //open cityInsertModal modal form
    if ($(event.target).hasClass('addNewCityBtn')) {
        $(".cityInsertModal #ins_city_countries").children().remove();

        fetch("/api/admin/allcountries")
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    $("#ins_city_countries")
                        .append("<option name=\""+item.name+"\" value=\""+item.name+"\" "
                            +" label=\""+item.name+"\" "
                            +">"+item.name+"</option>");
                }));
            }).catch(error => {
            console.log(error);
        });


        $(".cityInsertModal #cityInsertModal").modal();
    }

    //open cityEdit modal form
    if ($(event.target).hasClass('cityEditButton')) {

        $(".cityEditModal #edit_city_countries").children().remove();

        let href = $(event.target).attr("href");
        console.log(href);

        $.get(href, function (city) {
            $('.cityEditModal #id').val(city.id);
            $('.cityEditModal #name').val(city.name);

            fetch("/api/admin/allcountries")
                .then((response) => {
                    response.json().then(data => data.forEach(function (item, i, data) {
                        if (city.country == item.name) {
                            $("#edit_city_countries")
                                .append("<option name=\""+item.name+"\" value=\""+item.name+"\" "
                                    +" selected=true"
                                    +" label=\""+item.name+"\" "
                                    +">"+item.name+"</option>");
                        } else {
                            $("#edit_city_countries")
                                .append("<option name=\""+item.name+"\" value=\""+item.name+"\" "
                                    +" label=\""+item.name+"\" "
                                    +">"+item.name+"</option>");
                        }

                    }));
                }).catch(error => {
                console.log(error);
            });
        });

        $(".cityEditModal #cityEditModal").modal();
    }


    //open countryInsertModal modal form
    if ($(event.target).hasClass('addNewCountryBtn')) {
        $(".countryInsertModal #countryInsertModal").modal();
    }

    //open countryEdit modal form
    if ($(event.target).hasClass('countryEditButton')) {
        let href = $(event.target).attr("href");
        console.log(href);

        $.get(href, function (country) {
            $('.countryEditModal #id').val(country.id);
            $('.countryEditModal #name').val(country.name);
        });

        $(".countryEditModal #countryEditModal").modal();
    }

    //open categoryInsertModal modal form
    if ($(event.target).hasClass('addNewCatBtn')) {
        $(".categoryInsertModal #catInsertModal").modal();
    }

    //open categoryEdit modal form
    if ($(event.target).hasClass('categoryEditButton')) {
        let href = $(event.target).attr("href");
        console.log(href);

        $.get(href, function (category) {
            $('.categoryEditModal #id').val(category.id);
            $('.categoryEditModal #name').val(category.name);
        });

        $(".categoryEditModal #catEditModal").modal();
    }

    //open insertUser modal form
    if ($(event.target).hasClass('addNewUserBtn')) {
        $(".userInsertModal #ins_user_roles").children().remove();

        fetch("/api/admin/allroles")
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    $("#ins_user_roles")
                        .append("<option name=\""+item.name+"\" value=\""+item.id+"\" "
                            +" label=\""+item.name+"\" "
                            +">"+item.name+"</option>");
                }));
            }).catch(error => {
            console.log(error);
        });

        $(".userInsertModal #userInsertModal").modal();
    }

    //open editUser modal form
    if ($(event.target).hasClass('userEditButton')) {
        $(".userEditModal #edit_user_roles").children().remove();
        $(".userEditModal #edit_user_gender").children().remove();

        fetch("/api/admin/allroles")
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    $("#edit_user_roles")
                        .append("<option name=\""+item.name+"\" value=\""+item.id+"\" "
                            +" label=\""+item.name+"\" "
                            +">"+item.name+"</option>");
                }));
            }).catch(error => {
            console.log(error);
        });

        let href = $(event.target).attr("href");
        console.log(href);

        $.get(href, function (user) {

            console.log(user.id);
            $('.userEditModal #id').val(user.id);
            $('.userEditModal #email').val(user.email);
            $('.userEditModal #username').val(user.username);
            $('.userEditModal #password').val(user.password);

            const gender = {};
            gender[0] = "MALE";
            gender[1] = "FEMALE";
            gender[2] = "UNKNOWN";

            for (let i = 0; i < 3; i++) {
                if (user.gender == gender[i]) {
                    $("#edit_user_gender")
                        .append("<option name=\""+gender[i]+"\" value=\""+gender[i]+"\" "
                            + " selected=true"+
                            +" label=\""+gender[i]+"\" "
                            +">"+gender[i]+"</option>");
                } else {
                    $("#edit_user_gender")
                        .append("<option name=\""+gender[i]+"\" value=\""+gender[i]+"\" "
                            +" label=\""+gender[i]+"\" "
                            +">"+gender[i]+"</option>");
                }
            }

            $('.userEditModal #birthday').val(user.birthday);
            $('.userEditModal #phone').val(user.phone);
            $('.userEditModal #firstName').val(user.firstName);
            $('.userEditModal #lastName').val(user.lastName);


            const user_roles = user.roles;

            $('.userEditModal #edit_user_roles option').each(function (index, item) {
                var option = $(item);
                var a = 0;
                for (let i = 0; i < user_roles.length; i++) {
                    if (option.val() == user_roles[i].id) {
                        a = 1;
                    }
                }

                if (a == 1) {
                    console.log('option selected ' + option.val());
                    option.prop('selected', true);
                } else {
                    console.log('option not selected ' + option.val());
                    option.prop('selected', false);
                }
            });

        });


        $(".userEditModal #userEditM").modal();
    }

    //delete address
    if ($(event.target).hasClass('addressDeleteButton')) {
        let href = $(event.target).attr("href");

        fetch(href, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        }).then((response) => { if (response.ok)
        {updateAddresses(""); updateUsers("");} else {alert('Невозможно удалить адрес')}
        });

    }

    //delete city
    if ($(event.target).hasClass('cityDeleteButton')) {
        let href = $(event.target).attr("href");

        fetch(href, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        }).then((response) => {if (response.ok)
        {updateCities(""); updateAddresses("");} else {alert('Невозможно удалить город')}
        });

    }

    //delete country
    if ($(event.target).hasClass('countryDeleteButton')) {
        let href = $(event.target).attr("href");

        fetch(href, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        }).then((response) => {if (response.ok)
        {updateCountries(""); updateCities("");} else {alert('Невозможно удалить страну')}
        });

    }

    //delete item
    if ($(event.target).hasClass('itemDeleteButton')) {
        let href = $(event.target).attr("href");

        fetch(href, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        }).then((response) => {if (response.ok)
        {updateItems("")} else {alert('Невозможно удалить товар')}
        });

    }

    //delete category
    if ($(event.target).hasClass('categoryDeleteButton')) {
        let href = $(event.target).attr("href");

        fetch(href, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        }).then((response) => {if (response.ok)
        {updateCategories(""); updateItems("")} else {alert('Невозможно удалить товарную категорию')}
        });

    }

    //delete shop
    if ($(event.target).hasClass('shopDeleteButton')) {
        let href = $(event.target).attr("href");

        fetch(href, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        }).then((response) => {if (response.ok)
        {updateShops(""); updateItems("")} else {alert('Невозможно удалить магазин')}
        });
    }

    //delete user
    if ($(event.target).hasClass('userDeleteButton')) {
        let href = $(event.target).attr("href");

        fetch(href, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        }).then((response) => {if (response.ok)
        {updateUsers(""); updateShops(""); updateAddresses("");updateItems("")}  else {alert('Невозможно удалить пользователя')}
        });

    }

});