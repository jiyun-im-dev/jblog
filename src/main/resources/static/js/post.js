// postObject 객체 선언
let postObject = {
    // init() 함수 선언
    init: function () {
        let _this = this;

        // #btn-insert 버튼에 click 이벤트가 발생하면 insertPost() 함수 호출
        $("#btn-insert").on("click", () => {
            _this.insertPost();
        });
    },

    insertPost: function () {
        alert("포스트 등록 요청됨");
        // 사용자가 입력한 값을 추출해서 포스트 객체 생성
        let post = {
            title: $("#title").val(), // id가 title인 input의 값을 가져옴
            content: $("#content").val()
        }
        // Ajax를 이용한 비동기 호출
        $.ajax({
            type: "POST", // 요청 방식
            url: "/post/insertPost", // 요청 경로
            data: JSON.stringify(post), // post 객체를 JSON 형식으로 변환
            contentType: "application/json; charset=utf-8"
        }).done(function (response) {
            let message = response["data"]; // response 파라미터의 data 키에 해당하는 값?
            alert(message);
            location = "/";
        }).fail(function (error) {
            let message = error["data"];
            alert("에러 발생: " + error)
        });
    },

    updatePost: function () {
        alert("포스트 수정 요청됨");
        let post = {
            id: $("#id").val(),
            title: $("#title").val(),
            content: $("#content").val()
        }

        $.ajax({
            type: URL,
            url: "/post",
            data: JSON.stringify(post),
            contentType: "application/json; charset=utf-8"
        }).done(function (response) {
            let message = response["data"];
            alert(message);
            location = "/";
        }).fail(function (error) {
            let message = error["data"];
            alert("문제 발생 " + message);
        });
    },
}

// postObject 객체의 init() 함수 호출
postObject.init();