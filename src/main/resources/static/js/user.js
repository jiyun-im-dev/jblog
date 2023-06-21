// userObject 객체 선언
let userObject = {
    // init() 함수 선언
    init: function () {
        let _this = this;

        // #btn-save 버튼에 click 이벤트가 발생하면 insertUser() 함수 호출
        $("#btn-save").on("click", () => {
            _this.insertUser();
        });
    },

    insertUser: function () {
        alert("회원가입 요청됨");
        // 사용자가 입력한 값 추출
        let user = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }
        // Ajax를 이용한 비동기 호출
        $.ajax({
            type: "POST", // 요청 방식
            url: "/auth/insertUser", // 요청 경로
            data: JSON.stringify(user), // user 객체를 JSON 형식으로 변환
            contentType: "application/json; charset=utf-8"
        }).done(function (response) {
            console.log(response);
            location = "/";
        }).fail(function (error) {
            alert("에러 발생: " + error)
        });
    },
}

// userObject 객체의 init() 함수 호출
userObject.init();