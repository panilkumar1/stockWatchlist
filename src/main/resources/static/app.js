var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#watchlist").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/watchlist', function (watchlist) {
            showWatchlist(JSON.parse(watchlist.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function addStock() {
    stompClient.send("/app/stocks/add", {}, $("#symbol").val());
}

function removeStock(symbol) {
    stompClient.send("/app/stocks/remove", {}, symbol);
    $('#R-' + symbol).remove();
}

function showWatchlist(message) {
    message.stocks.forEach(updateStockPrice);
}
function updateStockPrice(item) {
    if ( $('#' + item.symbol).length ) {
        $('#' + item.symbol).html(item.price);
    } else {
        $("#watchlist").append("<tr id='R-" + item.symbol + "'><td>" + item.symbol + "</td><td id='" + item.symbol + "'>" + item.price + "</td><td><button class=\"btn btn-default\" type=\"submit\" onclick='removeStock(\"" + item.symbol + "\")'>Remove</button></td></tr>");
    }
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#addSymbol" ).click(function() { addStock(); });
    connect();
});

