$(document).ready(function () {
    CKEDITOR.replace( 'editor', {
        filebrowserUploadUrl: '/api/upload/upload-image',
        height: 652
    } );

    CKEDITOR.replace( 'editorImage', {
        filebrowserUploadUrl: '/api/upload/upload-image',
        height: 200
    } );


    CKEDITOR.replace( 'editorTitle', {
        filebrowserUploadUrl: '/api/upload/upload-image',
        height: 200
    } );


    CKEDITOR.on('dialogDefinition', function (e) {
        let dialogName = e.data.name;
        let dialogDefinition = e.data.definition;

        switch (dialogName) {
            case 'image':
                // dialogDefinition.removeContents('info');
                dialogDefinition.removeContents('Link');
                dialogDefinition.removeContents('advanced');
                break;
        }
    });

    $('#btn_save').click(function () {

        let dataBlog={};

        dataBlog.content=CKEDITOR.instances.editor.getData();

        axios.post("/api/blog/create",dataBlog).then(function (res) {
            if(res.data.success){
                swal(
                    'Good job!',
                    res.data.message,
                    'success'
                );
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
                'Some error when saving blog',
                'error'
            );
        })


        alert(CKEDITOR.instances.editor.getData());


    });

    $('#btn_cancel').click(function () {
        $("cke_dialog").hide();
        CKEDITOR.instances.editor.setData('Hello ...');
    });

    $('#btn_cancel').click(function () {
        $("cke_dialog").hide();
        CKEDITOR.instances.editor.setData('Hello ...');
    });

});