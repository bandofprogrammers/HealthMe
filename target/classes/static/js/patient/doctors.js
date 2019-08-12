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
                                                                        <span class="btn glyphicon glyphicon-envelope" data-toggle="modal"
                                                                              data-target="#askQuestion"></span>
                                                                    </td>
                                                                    <!-- Modal -->
                                                                    <div class="modal fade" id="askQuestion" role="dialog">
                                                                        <div class="modal-dialog">

                                                                            <!-- Modal content-->
                                                                            <div class="modal-content">
                                                                                <div class="modal-header">
                                                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                                    <h4 class="modal-title">Ask a question to your doctor</h4>
                                                                                </div>
                                                                                <div class="modal-body">
                                                                                    <form action="#" th:action="@{/patient/sendMessage}"
                                                                                          th:object="${message}" method="post">
                                                                                        <div class="form-group">
                                                                                            <label for="topic">Topic: </label><br>
                                                                                            <input type="text" th:field="*{topic}" id="topic"/>
                                                                                        </div>
                                                                                        <div class="form-group">
                                                                                            <label for="content">Message:</label><br>
                                                                                            <textarea placeholder="Enter message here..." th:field="*{content}"
                                                                                                      id="content" cols="50" rows="5"/></label>
                                                                                        </div>
                                                                                        <div class="form-group">
                                                                                            <input type="hidden" th:value="${message.senderId}" th:attr="name='senderId'"/>
                                                                                        </div>
                                                                                        <div class=" form-group">
                                                                                            <input type="hidden" th:value="${internist.id}" th:attr="name='receiverId'"/>
                                                                                        </div>
                                                                                        <div class=" form-group">
                                                                                            <input type="submit" value="Submit"/>
                                                                                        </div>
                                                                                    </form>
                                                                                </div>
                                                                                <div class="modal-footer">
                                                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close
                                                                                    </button>
                                                                                </div>
                                                                            </div>

                                                                        </div>
                                                                    </div>

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