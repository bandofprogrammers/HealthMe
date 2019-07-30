$(document).ready(function(){
   $('#date_picker').datepicker({
       format: "yyyy-mm-dd",
       daysOfWeekDisabled: [0,6]
    }).datepicker("setDate", 'now');

    $('#date_picker').on('changeDate', function () {
        console.log($('#date_picker').datepicker('getFormattedDate'))
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