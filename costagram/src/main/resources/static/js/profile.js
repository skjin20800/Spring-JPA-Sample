document.querySelector("#subscribeBtn").onclick = (e) => {
	e.preventDefault();
	document.querySelector(".modal-follow").style.display = "flex";

	let userId = $("#userId").val();


	$.ajax({
		url: `/user/${userId}/follow`,
	}).done((res) => {
		$("#follow_list").empty();
		res.data.forEach((u) => {

			let item = makeSubscribeInfo(u);

			$("#follow_list").append(item);
		});
	})
		.fail(error => {
			alert("오류" + error);
		});

};





function closeFollow() {
	document.querySelector(".modal-follow").style.display = "none";
}
document.querySelector(".modal-follow").addEventListener("click", (e) => {
	if (e.target.tagName !== "BUTTON") {
		document.querySelector(".modal-follow").style.display = "none";
	}
});
function popup(obj) {
	console.log(obj);
	document.querySelector(obj).style.display = "flex";
}
function closePopup(obj) {
	console.log(2);
	document.querySelector(obj).style.display = "none";
}
document.querySelector(".modal-info").addEventListener("click", (e) => {
	if (e.target.tagName === "DIV") {
		document.querySelector(".modal-info").style.display = "none";
	}
});
document.querySelector(".modal-image").addEventListener("click", (e) => {
	if (e.target.tagName === "DIV") {
		document.querySelector(".modal-image").style.display = "none";
	}
});



function makeSubscribeInfo(u) {
	let item = `<div class="follower__item" id="follow-${u.userId}">`;
	item += `<div class="follower__img"><img src="/images/profile.jpeg" alt="">`;
	item += `</div>`;
	item += `<div class="follower__text">`;
	item += `<h2>${u.username}</h2>`;
	item += `</div>`;	
	item += `<div class="follower__btn" id="div-F${u.userId}">`;
	if(!u.equalState){
		item += `<button class="cta blue" id="btn-UF${u.userId}" onclick="postUnFollow(${u.userId})">구독취소</button>`;
	}else{
		item += `<button class="cta" id="btn-F${u.userId}" onclick="postFollow(${u.userId})">구독하기</button>`;
	}
	item += `</div>`;
	item += `</div>`;

	return item;
}


function postUnFollow(id){
      $.ajax({
		type: "DELETE",
		url: "/follow/"+id,
		dataType:"json"
	}).done((res)=>{
		console.log(res);
		if(res.statusCode === 1){
			var cardGrid = $("#div-F"+id);
			var newDiv = document.createElement("div");
			var cardDetail = `<button class="cta" id="btn-F${id}" onclick="postFollow(${id})">구독하기</button>`;
			newDiv.innerHTML = cardDetail;
			cardGrid.append(newDiv);
			$("#btn-UF"+id).remove();
		}else{
		}
	});
}


function postFollow(id){
      $.ajax({
		type: "POST",
		url: "/follow/"+id,
		dataType:"json"
	}).done((res)=>{
		if(res.statusCode === 1){
			var cardGrid = $("#div-F"+id);
			var newDiv = document.createElement("div");
			var cardDetail = `<button class="cta blue" id="btn-UF${id}" onclick="postUnFollow(${id})">구독취소</button>`;
			newDiv.innerHTML = cardDetail;
			cardGrid.append(newDiv);
			$("#btn-F"+id).remove();
		}else{
			
		}
	});
	
}

function mainFollow(userId){
	let text = $(`#followBox button`).text();
	
	if(text === "구독취소"){
		$.ajax({
			type: "DELETE",
			url: "/follow/"+userId,
			dataType: "json"
		}).done(res=>{
			alert("취소완료");
			$(`#followBox button`).text("구독하기");
			$(`#followBox button`).toggleClass("blue");
		});
	}else{
		$.ajax({
			type: "POST",
			url: "/follow/"+userId,
			dataType: "json"
		}).done(res=>{
			alert("구독완료");
			$(`#followBox button`).text("구독취소");
			$(`#followBox button`).addClass("blue");
		});
	}
}







