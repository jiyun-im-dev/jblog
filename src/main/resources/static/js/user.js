// userObject 객체 선언
let userObject = {
    // init() 함수 선언
    init: function () {
        let _this = this;
        // #btn-save 버튼에 click 이벤트가 발생하면 insertUser() 함수 호출
        $("#btn-save").on("click", () => {
            _this.insertUser();
        });
        $("#btn-update").on("click", () => {
            _this.updateUser();
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
            let status = response["status"];
            if (status == 200) {
                let message = response["data"];
                alert(message);
                location = "/";
            } else {
                let warn = "";
                let errors = response["data"];
                if (errors.password != null) warn = warn + errors.password + "\n";
                if (errors.username != null) warn = warn + errors.username + "\n";
                if (errors.email != null) warn = warn + errors.email;
                alert(warn);
            }
        }).fail(function (error) {
            alert("에러 발생: " + error)
        });
    },

    updateUser: function () {
        alert("회원 정보 수정 요청");
        let user = {
            id: $("#id").val(),
            username: $("#username").val(),
            password: $("#password").val()
        }
        $.ajax({
            type: "PUT",
            url: "/user",
            data: JSON.stringify(user),
            contentType: "application/json; charset=utf-8",
        }).done(function (response) {
            let message = response["data"];
            alert(message);
            location = "/";
        }).fail(function (error) {
            let message = error["data"];
            alert("문제 발생: " + message);
        });
    },

}

// userObject 객체의 init() 함수 호출
userObject.init();