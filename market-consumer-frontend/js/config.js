var AppConfig = {
    REST_API_BASE_URL: "http://localhost:7001/cryptocurrency/api",
    WS_URL: "http://localhost:9800/changes",
    I18N_CONFIG: {
        lng: "en",
        resGetPath: "resources/__ns__-__lng__.json",
        fallbackLng: "en"
    },
    TOASTR_CONFIG : {
        debug: false,
        timeOut: 3000,
        extendedTimeOut: 1000,
        fadeIn: 300,
        fadeOut: 300,
        positionClass: 'toast-top-center'
    }
};