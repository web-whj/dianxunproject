window.onload=function(){
	var scroll = document.querySelectorAll('.scroll')[0];
    var bar = document.querySelectorAll('.bar');
    var mask = document.querySelectorAll('.mask');
    var ptxt = document.querySelectorAll('.plan-left-bot');
    var barleft = 0;
    console.log(scroll,bar,mask);
    bar.forEach(function(ele,index){
    	console.log(ele);
    
	    ele.onmousedown = function(event){
	      var event = event || window.event;
	      var leftVal = event.clientX - this.offsetLeft;
	      var that = this;
	       // 拖动一定写到 down 里面才可以
	      document.onmousemove = function(event){
	        var event = event || window.event;
	        barleft = event.clientX - leftVal;     
	        if(barleft < 0){
	          barleft = 0;
	        }
	        else if(barleft > scroll.offsetWidth - ele.offsetWidth){
	          barleft = scroll.offsetWidth - ele.offsetWidth;
	        }
	        mask[index].style.width = barleft +'px' ;
	        that.style.left = barleft + "px";
	        if(index == 0){
	       		ptxt[index].innerHTML = parseInt(barleft/(scroll.offsetWidth-ele.offsetWidth) * 100) + "Mbps";
	        }
	        else if(index == 1){
	        	ptxt[index].innerHTML = parseInt(barleft/(scroll.offsetWidth-ele.offsetWidth) * 2000) + " GB";
	        }
	        else if(index ==2){
	        	ptxt[index].innerHTML = parseInt(barleft/(scroll.offsetWidth-ele.offsetWidth) * 24) + "小时";
	        }
	        //ptxt[index].innerHTML = parseInt(barleft/(scroll.offsetWidth-ele.offsetWidth) * 100) + "Mbps";
	        // ptxt[1].innerHTML = parseInt(barleft/(scroll.offsetWidth-ele.offsetWidth) * 100) + "Mbps";
	        // ptxt[0].innerHTML = parseInt(barleft/(scroll.offsetWidth-ele.offsetWidth) * 100) + "Mbps";
	 		
	        //防止选择内容--当拖动鼠标过快时候，弹起鼠标，bar也会移动，修复bug
	        window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
	      }
	 
	    }
	    document.onmouseup = function(){
	      document.onmousemove = null; //弹起鼠标不做任何操作
	    }
    })
}