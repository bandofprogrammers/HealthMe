$(document).ready(function(){
    $("#specialization").change(function(){
        $.ajax({
            'url': window.location.origin + "/patient/specializations/" + $("#specialization :selected").val(),
            'dataType': "json",
            'success': function (jsonData) {
                $("#doctorTableBody").empty()
                showReceived(jsonData)
                console.log(jsonData)
            }
        });
    })

})

function showReceived(jsonData){

    Object.keys(jsonData).forEach(function (key) {
        $("#doctorTableBody").append($(`<tr>
                                            <td>${jsonData[key].firstName}</td>
                                            <td>${jsonData[key].lastName}</td>
                                            <td>${jsonData[key].phoneNumber}</td>
                                            <td>${jsonData[key].email}</td>
                                        </tr>`))
    })

}