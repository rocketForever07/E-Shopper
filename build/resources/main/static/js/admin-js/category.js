$(document).ready(function () {
    let dataCategory = {};

    $("#new-category").on("click", function () {
        dataCategory = {};
        $('#input-category-name').val("");
        $('#input-category-desc').val("");
    })

    $(".edit-category").on("click", function () {
        console.log("hi");
        let id = $(this).data("category");
        console.log(id);
        axios.get("/api/category/detail/" + id).then(function(res){//res là dữ liệu trả về khi gọi api(xem ở admin api)
            if(res.data.success) {
                dataCategory.id = res.data.data.id;
                $("#input-category-name").val(res.data.data.name);
                $("#input-category-desc").val(res.data.data.short_desc);
            }else {
                console.log("haizzz");
            }
        }, function(err){
        })
    });

    $(".btn-save-category").on("click", function () {
        console.log("đây là hàm save");
        if($("#input-category-name").val() === "" || $("#input-category-desc").val() === "") {
            swal(
                'Error',
                'You need to fill all values',
                'error'
            );
            return;
        }

        dataCategory.name = $('#input-category-name').val();
        dataCategory.short_desc = $('#input-category-desc').val();
        let linkPost = "/api/category/create";
        if(dataCategory.id) {
            console.log("update");
            linkPost = "/api/category/update/" + dataCategory.id;
        }

        console.log(dataCategory);
        console.log(linkPost);

        axios.post(linkPost, dataCategory).then(function(res){
            if(res.data.success) {
                swal(
                    'Good job!',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
                });
            } else {
                swal(
                    'Error',
                    res.data.message,
                    'error'
                );
            }
        }, function(err){
            swal(
                'Error',
                'Some error when saving category',
                'error'
            );
        })
    });

    $(".delete-category").on("click",function () {
        dataCategory.id=$(this).data("category");
    });
    $(".btn-confirm-delete").on("click",function () {
        let link="/api/category/delete/"+dataCategory.id;

        console.log(link);
        axios.delete(link).then(function (res) {
            if(res.data.success){
                swal(
                    'Good job!',
                    res.data.message,
                    'success'
                ).then(function () {
                    location.reload();
                });
            }else {
                swal(
                    'Error',
                    res.data.message,
                    'error'
                );
            }
        },function (error) {
            swal(
                'Error',
                "some error when delete category",
                'error'
            );
        });
    });

});