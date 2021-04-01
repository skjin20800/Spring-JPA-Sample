$("#profileUpdate").on("click", (e)=>{
	e.preventDefault(); //form태그 action안타게 막아버리는것

  	let data = {
		username: $("#username").val(),
		name: $("#name").val(),
		website: $("#website").val(),
		bio: $("#bio").val(),
		email: $("#email").val(),
		gender: $("#gender").val(),
		phone: $("#phone").val(),
	};
      
      $.ajax({
		type: "PUT",
		url: "/user/profileUpdate",
		data: JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType:"json"
	}).done((res)=>{
		console.log(res);
		if(res.statusCode === 1){
			alert("회원정보 수정에 성공하였습니다.");
			 location.reload();
		}else{
			alert("회원정보 수정에 실패하였습니다.");
		}
	});

	
});






