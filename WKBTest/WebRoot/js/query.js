window.onload=function(){
	let spans = document.querySelectorAll('.right>span');
	let uls = document.querySelectorAll('.con-bot>ul');
	spans.forEach(function(ele,index){
		ele.onmousedown=function(){
			spans.forEach(function(ele,indes){
				uls[indes].style.display='none';
				ele.style.cssText=`font-size:12px;color: #9b9b9b;`;
			})
			uls[index].style.display='block';
			ele.style.cssText=`font-size:16px;color:#1aad19`;
		}
	})
}