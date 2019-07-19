$(document).ready(function(){
    $("#specialization").change(function(){
        $.ajax({
            'url': window.location.origin + "/patient/specializations/" + $("#specialization :selected").val(),
            'dataType': "json",
            'success': function (jsonData) {
                $("#doctorList").empty()
                showReceived(jsonData)
                console.log(jsonData)
            }
        });
    })

})

function showReceived(jsonData){
    $("#doctorList").append($(`<p>${jsonData[0].firstName}</p>`))
}