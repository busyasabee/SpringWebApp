jQuery(document).ready(function ($) {

    $('#addbtn').on('click', function () {
        event.preventDefault();
        var returnArray = {};
        var formArray = $('#personform').serializeArray();
        for (var i = 0; i < formArray.length; i++) {
            returnArray[formArray[i]['name']] = formArray[i]['value'];
        }

        var id = $('#id').val();
        console.log("form data" + returnArray);

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(returnArray),
            type: 'POST',
            url: '/persons',
            success: function (data) {

                if (data.hasOwnProperty("id_error")){
                    $("#resultDiv").html(" <p> Пользователь с таким ID уже существует</p>");
                } else {
                    $("#resultDiv").html("  <p> Успешно добавлен пользователь с ID = " + data.person_id + " </p>");
                }
            },
            error: function (data) {
                console.log('An error occurred');
                console.log(data);
            }

        })

    });
    $('#getbtn').on('click', function () {
        event.preventDefault();

        var id = $('#id').val();

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: {},
            type: 'GET',
            url: '/persons/' + id,
            success: function (data) {
                console.log(data);
                if (data.hasOwnProperty("id_error")){
                    $("#resultDiv").html(" <p> Нет пользователя с таким ID </p>");
                } else {
                    $("#resultDiv").html("  <p> Успешно получен пользователь с ID " +
                        + id
                        + ", фамилией " + data.lastName
                        + ", именем " + data.firstName
                        + ", отчеством " + data.middleName
                        + ", датой рождения " + data.birthDate + " </p>");
                }

            },
            error: function (data) {
                console.log('An error occurred');
                console.log(data);
            }

        })
    });

    $('#updatebtn').on('click', function () {
        event.preventDefault();
        var returnArray = {};
        var formArray = $('#personform').serializeArray();
        for (var i = 0; i < formArray.length; i++) {
            returnArray[formArray[i]['name']] = formArray[i]['value'];
        }

        var id = $('#id').val();

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(returnArray),
            type: 'PUT',
            url: '/persons/' + id,
            success: function (data) {
                if (data.hasOwnProperty("id_error")){
                    $("#resultDiv").html(" <p> Нет пользователя с таким ID </p>");
                } else {
                    $("#resultDiv").html("<p> Успешно обновлён пользователь с ID = " + id  + "</p>");
                }
            },
            error: function (data) {
                console.log('An error occurred');
                console.log(data);
            }

        })

    });

    $('#deletebtn').on('click', function () {
        event.preventDefault();

        var id = $('#id').val();

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: {},
            type: 'DELETE',
            url: '/persons/' + id,
            success: function (data) {
                if (data.hasOwnProperty("id_error")){
                    $("#resultDiv").html(" <p> Нет пользователя с таким ID </p>");
                } else {
                    $("#resultDiv").html("<p> Успешно удалён пользователь с ID = " + id + "</p>");
                }

            },
            error: function (data) {
                console.log('An error occurred');
                console.log(data);
            }

        })
    });

    $('#fill_btn').on('click', function () {
        event.preventDefault();

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: {},
            type: 'GET',
            url: '/persons/fill',
            success: function (data) {
                console.log("Fill request successful");
                console.log(data);
                var tbody = $('#info_table #table_data');
                tbody.empty();
                $.each(data, function (key, person) {
                    var comment = "";
                    if (person.comment !== null){
                        comment = person.comment;
                    }
                    tbody.append("<tr> <td> <input type='checkbox'></td>" +
                        "<td>" + person.personId + "</td>" +
                        "<td>" + person.lastName + "</td>" +
                        "<td>" + person.firstName + "</td>" +
                        "<td>" + person.middleName + "</td>" +
                        "<td>" + person.birthDate + "</td>" +
                        "<td>" + comment + "</td>"
                    )
                })

            },
            error: function (data) {
                console.log('An error occurred');
                console.log(data);
            }

        })
    });
});
