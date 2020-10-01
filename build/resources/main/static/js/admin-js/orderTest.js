$(document).ready(function () {
    let dataOrder={}


    console.log($("#exampleModalLong").data("status"));

    $("#exampleModalLong").data("status",2);
    console.log($("#exampleModalLong").data("status"));


    $(".edit-order").on("click",function () {
        dataOrder.id=$(this).data("id");
        let link = "/api/order/detail/"+dataOrder.id;

        axios.get(link).then(function (res) {
            if(res){
                dataOrder.orderStatus=res.data.data.orderStatus;
            }
        },function (error) {

        });

    });

    function getStatus(){
        return dataOrder.orderStatus;
    }

    $(".btn-save-product").on("click",function () {
        let linkPort="/api/order/update";
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


});