$(document).ready(function(){
   $('#date_picker').datepicker({
       format: "yyyy-mm-dd",
       daysOfWeekDisabled: [0,6]
    }).datepicker("setDate", 'now');

    $('#date_picker').on('changeDate', function () {
        getAvailableHoursForDoctorAndDate($('#doctorId').val(),$('#date_picker').datepicker('getFormattedDate'))
    })


    $('.register-for-hour-button').click(function(){
        registerForHour($('#doctorId').val(),$(this).val());
    })
})


function registerForHour(doctorId, hourId){
    $.ajax({
           type: "POST",
           url: window.location.origin + "/patient/registerforhour",
           data: {
                         'doctorId': doctorId,
                         'hourId': hourId
                     },
           success: function(){
               location.reload();
           }
           });
}

function getAvailableHoursForDoctorAndDate(doctorId,date){

    $.ajax({
        'url': window.location.origin + "/patient/availableterms/" + doctorId + "/" + date,
        'dataType': "json",
        'success': function (jsonData) {

            $("#available_hours").empty()

            var availableHours=jsonData["hours"];

            Object.keys(availableHours).forEach(function (key) {

            console.log(availableHours[key])
                var hourButton = $(`<button type="button" class="btn btn-primary btn-md btn-block register-for-hour-button"
                                     value="${key}">${availableHours[key].hour}</button>`);
                $("#available_hours").append(hourButton)

                hourButton.click(function(){registerForHour($('#doctorId').val(),$(this).val())})
            })
        }
    })
}

