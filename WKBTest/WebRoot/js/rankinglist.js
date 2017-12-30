window.onload=function(){
	let button = document.querySelectorAll('.nav3>span');
	let table = document.querySelectorAll('.table');
	button.forEach(function(ele,index){
		ele.onmousedown=function(){
			button.forEach(function(els,indes){
				table[indes].style.display='none';
				els.style.color='#5b6795';
			})
			table[index].style.display='block';
			ele.style.color='#444';
		}
	})
}