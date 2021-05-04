$(document).ready(function ($) {
    fetchAndRenderSomeProducts(10);
});


function fetchAndRenderSomeProducts(number_products) {
    fetch("/api/products/first/"+ number_products).then(response => response.json()).then(data => fillSomeProducts(data));
    $('#headerForSomeProductsView').text('Актуальные предложения')
}

/**
 * function that fills main page with products
 * @param data - products list
 */
function fillSomeProducts(data) {
    let prodsView = document.getElementById('someProductsView');
    prodsView.innerHTML = ''
    if (data !== 'error') {
        let item = ``;
        for (let key = 0; key < data.length; key++) {
            item += `
            <div class="col-2">
                <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm productView">
                    <div class="col-auto d-none d-lg-block productImg">
                        <img class="bd-placeholder-img" src="/uploads/images/products/0.jpg">
                    </div>
                    <div id="rate${data[key].id}"></div>
                    <div class="col p-4 d-flex flex-column position-static">
                        <p class="card-text mb-auto productName">${data[key].product}</p>
                        <a class="btn btn-sm btn-outline-light producthref" href="/products/${data[key].id}" role="button">Подробнее &raquo;</a>
                    </div>
                </div>
            </div>`;
            if ((key + 1) % 5 == 0) {
                $(prodsView).append(`<div class="row">` + item);
                item = ``;
            } else if ((key + 1) == data.length) {
                $(prodsView).append(`<div class="row">` + item);
            }
            $(function () {
                if (data[key].rating !== null) {
                    $(`#rate${data[key].id}`).rateYo({
                        rating: data[key].rating,
                        readOnly: true
                    });
                } else {
                    $(`#rate${data[key].id}`).rateYo({
                        rating: 0,
                        readOnly: true
                    });
                }
            });
        }
    } else {
        prodsView.innerHTML = 'Ожидайте новые продукты'
    }
}

