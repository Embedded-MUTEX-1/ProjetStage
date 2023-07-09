$(function(){
    $(".questions").on("submit", function (event) {
        event.preventDefault()

        var dataForm = $(".questions").serializeArray()
        var id = $(".questions").attr('id')
        sendResult(id, dataForm)
    })
});
function sendResult(id, dataForm) {
    var reponses = {}

    dataForm.forEach(function (item) {
        reponses[item.name] = {
            reponse: item.value,
            ttype: "qcm" // A remplir automatiquement
        }
    })

    var data = {
        passageTestId : id,
        reponseCandidats : reponses
    }
    console.log(JSON.stringify(data))

    var request = $.ajax({
        url: window.location.origin + '/api/verifytest',
        method: 'POST',
        data: JSON.stringify(data),
        contentType: 'application/json'
    });
    request.done(function () {
        console.log("success");
        window.location.replace(window.location.origin + "/TestDone");
    })
    request.fail(function () {
        console.log("fail");
        window.location.replace(window.location.origin + "/TestToDo");
    })
}