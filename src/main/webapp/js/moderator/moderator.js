import {ModeratorApi} from "./ModeratorApi.js";
import {
    createItemsContent, itemButtonsHandler,
    createShopsContent, shopButtonsHandler,
    createReviewsContent, reviewButtonsHandler
} from "./moderatorUtiles.js";

const itemButton = document.getElementById("v-pills-items-tab")
const shopButton = document.getElementById("v-pills-shops-tab")
const reviewButton = document.getElementById("v-pills-reviews-tab")
const itemsContainer = document.getElementById("itemsCards")
const shopsContainer = document.getElementById("shopsCards")
const reviewsContainer = document.getElementById("reviewsCards")

const api = new ModeratorApi();
api.getUnmoderatedItems()
    .then(res => {
        itemsContainer.insertAdjacentElement("afterbegin", createItemsContent(res))
    })

itemButton.addEventListener("click", () => {
    api.getUnmoderatedItems()
        .then(res => {
            itemsContainer.innerHTML = "";
            itemsContainer.insertAdjacentElement("afterbegin", createItemsContent(res));
        })
})

shopButton.addEventListener("click", () => {
    api.getUnmoderatedShops()
        .then(res => {
            shopsContainer.innerHTML = "";
            shopsContainer.insertAdjacentElement("afterbegin", createShopsContent(res));
        })
})

reviewButton.addEventListener("click", () => {
    api.getUnmoderatedReviews()
        .then(res => {
            reviewsContainer.innerHTML = "";
            reviewsContainer.insertAdjacentElement("afterbegin", createReviewsContent(res));
        })
})