//
// let dataOrder={};
//
// loadData=function (object) {
//
//     $("#exampleModalLong").show();
//     dataOrder.id=object;
//
//
//     console.log(dataOrder);
//
// }
//
// update=function () {
//     dataOrder.orderStatus=$("#input-order-status").val();
//     let linkPort="/api/order/update";
//     axios.put(linkPort,dataOrder).then(function (res) {
//
//         if(res.data.success){
//             swal(
//                 'Success',
//                 res.data.message,
//                 'success'
//             ).then(function() {
//                 location.reload();
//             });
//         } else {
//             swal(
//                 'Fail',
//                 res.data.message,
//                 'error'
//             );
//         }
//     },function (error) {
//         swal(
//             'Fail',
//             error,
//             'error'
//         )
//     });
//
// }
//
//
//
