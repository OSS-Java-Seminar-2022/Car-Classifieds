function hideToast(li){
    li.classList.add("hidden");
    let toastNotifications = document.getElementsByClassName("toast-notification");
    let count = 0;
    for(let i = 0; i < toastNotifications.length; i++){
        if(!toastNotifications[i].classList.contains("hidden")){
            count++;
        }
        toastNotifications[i].style.top = (i * 70) + "px";
    }
    if(count === 0){
        document.getElementById("toast").classList.add("hidden");
    }
}
function showToast(textToDisplay) {
    let toast = document.getElementById("toastList");
    let li = document.createElement("li");
    li.classList.add("toast-notification", "flex", "items-center", "py-2", "px-4");
    let svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
    svg.setAttribute("class", "inline-block h-4 w-4 mr-2 text-white");
    svg.setAttribute("fill", "none");
    svg.setAttribute("stroke-linecap", "round");
    svg.setAttribute("stroke-linejoin", "round");
    svg.setAttribute("stroke-width", "2");
    svg.setAttribute("stroke", "currentColor");
    svg.setAttribute("viewBox", "0 0 24 24");
    let path = document.createElementNS("http://www.w3.org/2000/svg", "path");
    path.setAttribute("d", "M5 13l4 4L19 7");
    svg.appendChild(path);
    li.appendChild(svg);
    let text = document.createTextNode(textToDisplay);
    let div = document.createElement("div");
    div.appendChild(text);
    li.appendChild(div);
    li.addEventListener("click", () => {
    hideToast(li);
    });
    setTimeout(() => {
        hideToast(li);
    }, 8000);
    toast.appendChild(li);
    document.getElementById("toast").classList.remove("hidden");
}
function hideAllToasts() {
    let toastNotifications = document.getElementsByClassName("toast-notification");
    for(let i = 0; i < toastNotifications.length; i++){
        hideToast(toastNotifications[i]);
    }
}
document.getElementById("hideAllToasts").addEventListener("click", hideAllToasts);