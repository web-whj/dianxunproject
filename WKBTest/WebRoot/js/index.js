window.onload=function(){
	let times = document.querySelector('.top-bo>p>span');
	let now = 8;
	setInterval(move,1000);
	function move(){
		now--;
		if(now < 0){
			now =8;
		}
		times.innerText=now;
	}
}