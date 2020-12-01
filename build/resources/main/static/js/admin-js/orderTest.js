$(document).ready(function () {
    let dataOrder={}

    $(".edit-order").on("click",function () {
        dataOrder.id=$(this).data("id");
        let link = "/api/order/detail/"+dataOrder.id;

        axios.get(link).then(function (res) {
            if(res){
                dataOrder.orderStatus=res.data.data.orderStatus;

                console.log(dataOrder);
                $(".input-order-status").val(dataOrder.orderStatus);
            }
        },function (error) {

        });

    });

    function getStatus(){
        return dataOrder.orderStatus;
    }

    $(".btn-save-product").on("click",function () {
        let linkPort="/api/order/update";

        dataOrder.orderStatus=$(".input-order-status").val();
        console.log(dataOrder);
        axios.put(linkPort,dataOrder).then(function (res) {

            if(res.data.success){
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
        },function (error) {
            swal(
                'Fail',
                error,
                'error'
            )
        });
    })

    let option={
        importCss:false,
        b

    }


    $('.printOrder').click(function () {
        $('table').printThis({

        });
    })

});