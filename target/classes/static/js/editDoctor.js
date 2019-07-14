$(document).ready(function(){
    $("#resetPasswordBtn").click(function(){
        var url= window.location.origin+"/admin/resetpassword/doctor/"+$("#resetPasswordBtn").attr("value")
        document.location.href=url
    })

})