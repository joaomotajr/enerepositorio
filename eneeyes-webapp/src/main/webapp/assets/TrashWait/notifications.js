function Notifications() {
    "use strict";

    // tooltip demo
    $('.tooltip-demo').tooltip({
        selector: "[data-toggle=tooltip]",
        container: "body"
    })

    // popover demo  - Modificado 
    $("[data-toggle=popover]")
        .popover()
};