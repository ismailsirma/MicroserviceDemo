class DashboardViewModel {
    constructor(){
        // domain related observables
        this.symbol = ko.observable();
        this.windowSize = ko.observable(25);
        this.monitoring = ko.observable(false);

        this.data = {
            labels: ko.observableArray([]),
            datasets: [
                {
                    label: [],
                    backgroundColor: "rgba(220,220,220,0.2)",
                    borderColor: "rgba(220,220,220,1)",
                    pointColor: "rgba(220,220,220,1)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(220,220,220,1)",
                    data: ko.observableArray([])
                }
            ]
        } ;

        this.changeLng= this.changeLng.bind(this);
        this.i18n= this.i18n.bind(this);
        this.translate= this.translate.bind(this);
        this.start= this.start.bind(this);
        this.stop= this.stop.bind(this);
    }
    connect =  () => {
        this.socket = new SockJS(
            "http://localhost:9800/changes");
        this.stompClient = Stomp.over(this.socket);
        this.stompClient.debug = () => {}
        this.stompClient.connect({}, (frame) => {
            toastr.success("WebSocket connection is ready!");
            this.data.datasets[0].label.push(this.symbol());
            this.stompClient.subscribe(
                "/topic/changes",
                (msg) =>{
                    if (!this.monitoring()) return;
                   let trade = JSON.parse(msg.body);
                   this.data.datasets[0].data.push(trade.p);
                    let now = new Date().toTimeString();
                    now= now.replace(/.*(\d{2}:\d{2}:\d{2}).*/,'$1');
                    this.data.labels.push(now);
                    if (this.data.datasets[0].data().length>this.windowSize()){
                        let sliceIndex= this.data.datasets[0].data().length
                            - this.windowSize();
                        this.data.datasets[0].data(
                            this.data.datasets[0].data.slice(sliceIndex)
                        );
                        this.data.labels(this.data.labels.slice(sliceIndex));
                    }
                });
        });
        this.enableTradingView();

    }
    // i18n
    changeLng(lng){
        i18n.setLng(lng,() => {
            this.i18n();
        });
    };

    i18n(){
        $(document).i18n();
    };

    translate(word){
        return i18n.t(word);
    };

    // starts monitoring
    start(){
        this.monitoring(true);
        toastr.success(i18n.t("messageMonitoringStarted"), "", AppConfig.TOASTR_CONFIG);
    };

    // stops monitoring
    stop(){
        this.monitoring(false);
        toastr.warning(i18n.t("messageMonitoringStoped"), "", AppConfig.TOASTR_CONFIG);
    };
    // trading view
    enableTradingView = () => {
        new TradingView.widget({
            'container_id': 'dcl',
            'width': 800,
            'height': 600,
            'symbol': 'BINANCE:BTCUSDT',
            'interval': 'D',
            'timezone': 'Etc/UTC',
            'theme': 'Light',
            'style': '1',
            'locale': 'tr',
            'toolbar_bg': '#f1f3f6',
            'enable_publishing': false,
            'withdateranges': true,
            'hide_side_toolbar': false,
            'allow_symbol_change': true,
            'watchlist': [
                'BINANCE:BTCUSDT',
                'BINANCE:ETHBTC',
                'BINANCE:LTCBTC',
                'BINANCE:XRPBTC',
                'BINANCE:BCCBTC',
                'BINANCE:DASHBTC',
                'BINANCE:ADABTC',
                'BINANCE:NEOBTC',
                'BINANCE:EOSBTC',
                'BINANCE:LTCUSDT',
                'BINANCE:XRPUSDT',
                'BINANCE:EOSUSD',
                'BINANCE:NEOUSDT',
                'BINANCE:ADAUSDT',
                'BINANCE:XLMUSD',
                'BINANCE:XLMBTC'
            ],
            'details': true,
            'hideideas': false,
            'studies': [
                'MACD@tv-basicstudies'
            ],
            'show_popup_button': false
        });
    };

};

var dashBoardViewModel= new DashboardViewModel();

$( () => {
    i18n.init(AppConfig.I18N_CONFIG,(t) => {
        $(document).i18n();
        ko.applyBindings(dashBoardViewModel);
        dashBoardViewModel.connect();
    });
});