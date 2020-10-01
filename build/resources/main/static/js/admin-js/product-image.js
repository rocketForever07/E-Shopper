$(document).ready(function() {

    let dataProductImage = {};
    dataProductImage.productId=vm.productId;

    // function readURL(input) {
    //     if (input.files && input.files[0]) {
    //         let reader = new FileReader();
    //         reader.onload = function(e) {
    //             $('#preview-product-img').attr('src', e.target.result);
    //         }
    //         reader.readAsDataURL(input.files[0]);
    //
    //     }
    // }

    $("#change-image").change(function() {
        let formData = new FormData();
        formData.append('file', $("#change-image")[0].files[0]);

        axios.post("/api/upload/upload-image", formData).then(function(res){
            if(res.data.success) {
                $('.image').attr('src', res.data.link);
            }
        }, function(err){

        });
    });

    $(".edit-image").on("click",function () {
        dataProductImage.id=$(this).data("id");

        let link="/api/product-image/detail/"+dataProductImage.id;
        axios(link).then(function (res) {
            debugger;
            if(res){
                $('.image').attr('src', res.data.data.link);
                console.log(res.data.data.link);
            }else{

            }
        },function (error) {

        });

    });

    $(".new-image").on("click",function () {
        dataProductImage.id=null;
    })

    $(".btn-save-image").on("click",function () {
        dataProductImage.link=$('.image').attr('src');

        console.log(dataProductImage);

        let link="/api/product-image/create";


        if(dataProductImage.id){
            link="/api/product-image/update/"+dataProductImage.id;
        }

        console.log(link);
        console.log(dataProductImage);
        axios.post(link,dataProductImage).then(function (res) {
            if(res){
                swal(
                    'Good job!',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
                });
            }else{
                swal(
                    'Error',
                    res.data.message,
                    'error'
                );
            }
        },function (error) {
            swal(
                'Error',
                "some error",
                'error'
            );
        })
    })
    $(".delete-product-image").on("click", function () {
        alert("hi");
        let productImageId = $(this).data("id");
        console.log(productImageId);

        let linkPort = "/api/product-image/delete/"+productImageId;

        axios.delete(linkPort).then(function (res) {
            if(res.data.success){
                swal(
                    'Good job!',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
                });
            }else{
                swal(
                    'Error',
                    'fail',
                    'error'
                );
            }
        },function(err) {
            swal(
                'Error',
                'Some error when delete product-image',
                'error'
            );
        })
    });

});