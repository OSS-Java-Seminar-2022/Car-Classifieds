myToursVisible();

function elementVisibility(name, visible) {
    Array.prototype.forEach.call(document.getElementsByClassName(name), function (e) {
        if (visible)
            e.style.display = "block";
        else
            e.style.display = "none";
    });
}

function myToursVisible() {
    elementVisibility("my_tours", true);
    elementVisibility("marketplace", false);
    elementVisibility("profile", false);
}

function marketplaceVisible() {
    elementVisibility("my_tours", false);
    elementVisibility("marketplace", true);
    elementVisibility("profile", false);
}

function profileVisible() {
    elementVisibility("my_tours", false);
    elementVisibility("marketplace", false);
    elementVisibility("profile", true);
}

function tabClick(page) {
    if (page === "my_tours") {
        myToursVisible();
    } else if (page === "marketplace") {
        marketplaceVisible();
    } else if (page === "profile") {
        profileVisible();
    } else if (page === "logout") {
        //promptLogout();
        window.location.href = "http://localhost:8080/logout"
    }
}