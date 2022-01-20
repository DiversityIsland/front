import {moderatorButtonHandler} from "./moderator.js";

const moderatorButton = document.querySelector("#moderator-button");

moderatorButton.addEventListener("click", event => {
    event.preventDefault();
    moderatorButtonHandler();
})
