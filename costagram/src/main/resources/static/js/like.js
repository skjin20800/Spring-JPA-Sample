function clickBtn() {
  let _buttonI = event.target;

  if (_buttonI.classList.contains("far")) {
    _buttonI.classList.add("fas");
    _buttonI.classList.add("active");
    _buttonI.classList.remove("far");
  } else {
    _buttonI.classList.remove("fas");
    _buttonI.classList.remove("active");
    _buttonI.classList.add("far");
  }
}

function addComment(postId, username) {
  // value : 댓글을 쓸 게시글
  let commentInput = event.path[1].children[0];
  let commentList = event.path[2].children[3];

  // 유저 아이디 필요하면 매개변수로 받아와서 넣으면 됨.
  let _data = {
    postId: postId,
    commemtText: commentInput.value,
  };
  if (_data.commemtText === "" || _data.commemtText === null) {
    alert("댓글을 작성해주세요!");
    return;
  }
  let content = `
  <div class="sl__item__contents__comment">
    <p>
      <b>${username} :</b>
      ${_data.commemtText}
    </p>
    <button onClick="deleteComment(1)"><i class="fas fa-times"></i></button>
  </div>
  `;
  commentList.insertAdjacentHTML("afterbegin", content);
  commentInput.value = "";
}

function deleteComment(commentId) {
  alert("test");
  // 삭제 fetch 작성하면됨.
}