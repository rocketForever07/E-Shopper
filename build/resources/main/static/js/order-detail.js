$(document).ready(function () {
    $("#cancel").on("click", function () {
        let order = {};
        let linkPort = "/api/order/update";

        order.id = $(this).data("id");
        order.orderStatus = 3;

        // console.log(linkPort);
        // console.log(order);

        axios.put(linkPort, order).then(function (res) {

            if (res.data.success) {
                swal(
                    'Canceled Successfully',
                    res.data.message,
                    'success'
                ).then(function () {
                    location.reload();
                });
            } else {
                swal(
                    'Fail',
                    res.data.message,
                    'error'
                );
            }
        }, function (error) {
            swal(
                'Fail',
                error,
                'error'
            )
        });
    });
})
