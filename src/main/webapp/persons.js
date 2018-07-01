jQuery(document).ready(function ($) {

    $('#addBtn').on('click', function () {
        event.preventDefault();
        var returnArray = {};
        var formArray = $('#personForm').serializeArray();
        for (var i = 0; i < formArray.length; i++) {
            returnArray[formArray[i]['name']] = formArray[i]['value'];
        }

        var id = $('#personId').val();

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(returnArray),
            type: 'POST',
            url: '/persons',
            success: function (data) {
                var personId = data.personId;
                $("#resultDiv").html("  <p> Успешно добавлен пользователь с ID = " + personId + " </p>");

            },
            error: function (data) {
                $("#resultDiv").html(" <p> Пользователь с таким ID уже существует</p>");
            }

        })

    });
    $('#getBtn').on('click', function () {
        event.preventDefault();

        var id = $('#personId').val();

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: {},
            type: 'GET',
            url: '/persons/' + id,
            success: function (data) {
                var person = data.person;
                $("#resultDiv").html("<p> Успешно получен пользователь с ID " +
                    + person.personId
                    + ", фамилией " + person.lastName
                    + ", именем " + person.firstName
                    + ", отчеством " + person.middleName
                    + ", датой рождения " + person.birthDate + " </p>");

            },
            error: function (data) {
                var message = data.responseJSON.message;
                $("#resultDiv").html(" <p>" + message +  "</p>");

            }

        })
    });

    $('#updateBtn').on('click', function () {
        event.preventDefault();
        var returnArray = {};
        var formArray = $('#personForm').serializeArray();
        for (var i = 0; i < formArray.length; i++) {
            returnArray[formArray[i]['name']] = formArray[i]['value'];
        }

        var id = $('#personId').val();

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(returnArray),
            type: 'PUT',
            url: '/persons/' + id,
            success: function (data) {
                $("#resultDiv").html("<p> Успешно обновлён пользователь с ID = " + id  + "</p>");
            },
            error: function (data) {
                $("#resultDiv").html(" <p> Нет пользователя с таким ID </p>");
            }

        })

    });

    $('#deleteBtn').on('click', function () {
        event.preventDefault();

        var id = $('#personId').val();

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: {},
            type: 'DELETE',
            url: '/persons/' + id,
            success: function (data) {
                $("#resultDiv").html("<p> Успешно удалён пользователь с ID = " + id  + "</p>");

            },
            error: function (data) {
                console.log('An error occurred');
                console.log(data);
                $("#resultDiv").html(" <p> Нет пользователя с таким ID </p>");
            }

        })
    });

    $('#fillBtn').on('click', function () {
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
                var tbody = $('#infoTable #tableData');
                tbody.empty();
                $.each(data, function (key, person) {
                    var comment = "";
                    if (person.comment !== null){
                        comment = person.comment;
                    }
                    tbody.append("<tr> <td> <input type='checkbox'></td>" +
                        "<td class='tdId'>" + person.personId + "</td>" +
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

    $('#handleBtn').on('click', function () {
        event.preventDefault();
        var trs = $("tr:has(input:checked) td.tdId");
        console.log("trs " + trs);

        var ids = [];
        trs.each(function(){
            ids.push($(this).html());
        });

        var dataObj = {};
        dataObj["ids[]"] = ids;

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: dataObj,
            type: 'GET',
            url: '/persons/handle',
            success: function (data) {
                var results = data.ids;
                $("#table_div").empty();
                results.forEach(function(item, i, arr) {
                    if (item.status == "good"){
                        $("#table_div").append("<p> Успешно обработан пользователь с ID = " + item.id  + "</p>");
                    } else {
                        $("#table_div").append("<p> Не удалось обработать пользователя с ID = " + item.id  + "</p>");
                    }
                });
            },
            error: function (data) {
                console.log('An error occurred');
                console.log(data);
            }

        })

    });
});
