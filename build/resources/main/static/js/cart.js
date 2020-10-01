$(document).ready(function() {

    $(".cart_quantity_input").change(function () {
        dataCartProduct = {};
        dataCartProduct.id = $(this).data("id");
        dataCartProduct.amount = $(this).val();

        var linkPost = "/api/cart-product/update";

        axios.post(linkPost, dataCartProduct).then(function(res){
            if(res.data.success) {
                location.reload();
            } else {
                swal(
                    'Fail',
                    res.data.message,
                    'error'
                ).then(function() {
                    // location.reload();
                });
            }
        }, function(err){
            swal(
                'Error',
                'Fail',
                'error'
            );
        });
    });


    $(".cart_quantity_delete").on("click",function(){
        let pdInfo = $(this).data("id");

        var linkGet = "/api/cart-product/delete/"+pdInfo;
        axios.get(linkGet).then(function(res){
            if(res.data.success) {
                swal(
                    'Success',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
                });
            } else {
                swal(
                    'Fail',
                    res.data.message,
                    'error'
                );
            }
        }, function(err){
            swal(
                'Error',
                'Fail',
                'error'
            );
        });
    })


});