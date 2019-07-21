$(document).ready(function(){
    $('#date_picker').datetimepicker({
       format: 'YYYY-MM-DD',
       inline: true,
       daysOfWeekDisabled: [0,6]
    });
})