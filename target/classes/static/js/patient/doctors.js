$(document).ready(function(){
    $("#specialization").change(function(){
        $.ajax({
            'url': window.location.origin + "/patient/specializations/" + $("#specialization :selected").val(),
            'dataType': "json",
            'success': function (jsonData) {
                $("#doctorTableBody").empty()
                showReceived(jsonData)

            }
        })
    })

});


function showReceived(jsonData){
    Object.keys(jsonData).forEach(function (key) {

    if(jsonData[key].rating===0.00){

        $("#doctorTableBody").append($(`<tr>
                                            <td>${jsonData[key].firstName}</td>
                                            <td>${jsonData[key].lastName}</td>
                                            <td>${jsonData[key].phoneNumber}</td>
                                            <td>${jsonData[key].email}</td>
                                            <td>not rated yet</td>

                                            <td>
                                                <a href=${window.location.origin}/patient/schedule/${jsonData[key].id}>Schedule</a>
                                            </td>
                                        </tr>`))
                                        }else{
                                                $("#doctorTableBody").append($(`<tr>
                                                                                    <td>${jsonData[key].firstName}</td>
                                                                                    <td>${jsonData[key].lastName}</td>
                                                                                    <td>${jsonData[key].phoneNumber}</td>
                                                                                    <td>${jsonData[key].email}</td>
                                                                                    <td>${jsonData[key].rating}</td>
                                                                                    <td>
                                                                                        <a href=${window.location.origin}/patient/schedule/${jsonData[key].id}>Schedule</a>
                                                                                    </td>
                                                                                </tr>`))
                                        }
    })
}