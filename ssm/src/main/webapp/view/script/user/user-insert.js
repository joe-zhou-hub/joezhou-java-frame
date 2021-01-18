const APP_NAME = "/ssm";

$(() => {
    $("#submit-btn").click(() => submitInsertForm());
});

function submitInsertForm() {
    $.ajax({
        "url": `${APP_NAME}/api/user/insert`,
        "type": "post",
        "data": $("#insert-form").serialize(),
        "success": response => {
            alert(response["msg"]);
            if (response["status"] === 200) {
                location.href = `${APP_NAME}/view/html/user/user.html`;
            }
        }
    });
}