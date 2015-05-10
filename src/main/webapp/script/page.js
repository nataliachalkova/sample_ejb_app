$(document).ready(function () {
    $("#bdate").datepicker({
        dateFormat: 'dd.mm.yy'
    });

    //handlers for buttons

    var submitBtn = $('#submit');
    var onSumbitClick = function () {
        var formData = {
            id: $('#id').val(),
            name: $('#name').val(),
            surname: $('#surname').val(),
            patronymic: $('#patronymic').val(),
            dateOfBirth: $('#bdate').val()
        };

        $('#status').html("");

        $.ajax({
            url: "/SampleEjbApp/api/person/save",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(formData),
            success: function (data, textStatus, jqXHR) {
                if (data == "false") {
                    $('#status').html("Save error");
                }
                else {
                    $('#status').html("Saved successfully");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#status').html("Save error");
            }
        });

    };
    submitBtn.on('click', onSumbitClick);


    var loadBtn = $('#load');
    var onLoadClick = function () {
        $('#status').html("");

        var personId = $('#personId').val();
        $.ajax({
            url: "/SampleEjbApp/api/person/get/" + personId,
            type: "GET",
            success: function (data, textStatus, jqXHR) {
                //console.log(data);
                if (data) {
                    $('#name').val(data.name);
                    $('#surname').val(data.surname);
                    $('#patronymic').val(data.patronymic);
                    $('#bdate').val(data.dateOfBirth);
                    $('#id').val(data.id);

                    $('#status').html("Load successfully");

                } else {
                    onClearClick();
                    $('#status').html("Load error");
                }

            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#status').html("Load error");
            }
        });

    };
    loadBtn.on('click', onLoadClick);


    var clearBtn = $('#clear');
    var onClearClick = function () {
        $('#name').val("");
        $('#surname').val("");
        $('#patronymic').val("");
        $('#bdate').val("");
        $('#id').val(-1);

        $('#status').html("");
    }
    clearBtn.on('click', onClearClick);

});