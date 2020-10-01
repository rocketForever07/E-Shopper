$(document).ready(function() {

    function readURL(input) {
        if (input.files && input.files[0]) {
            let reader = new FileReader();
            reader.onload = function (e) {
                $('.user-avatar').attr('src', e.target.result);

            }

            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#input-avatar").change(function (e) {
        readURL(this);
        let formData=new FormData;
        formData.append('file',$("#input-avatar")[0].files[0]);
        axios.post("/api/upload/upload-image", formData).then(function (res) {
            // Nprogress.done();
            console.log(res.data);
            if(res.data.success) {
                $("#avatar").val(res.data.link);
            }
        }), function (err) {

        };
    });

});