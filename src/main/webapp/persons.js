jQuery(document).ready(function($) {

    $('#addbtn').on('click', function () {
        event.preventDefault();
        var returnArray = {};
        var formArray = $('#personform').serializeArray();
        for (var i = 0; i < formArray.length; i++) {
            returnArray[formArray[i]['name']] = formArray[i]['value'];
        }
        console.info(JSON.stringify(returnArray));
        var postResult;
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(returnArray),
            type: 'POST',
            url: '/persons',
            success: function (data) {
                console.log('Submission was successful.');
                console.log(data);
                $("#postResultDiv").html("<p> Добавлен пользователь с ID " +
                    + data.id//["ID"]
                    + ", фамилией " + data.surname
                    + ", именем " + data.name
                    + ", отчеством " + data.patronymic
                    + ", датой рождения " + data.dateBirth);
                console.log('name = ' + data["name"]);
            },
            error: function (data) {
                console.log('An error occurred.');
                console.log(data);
            }

        })

    });
});
